package vn.com.ecotechgroup.erp.controller.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import vn.com.ecotechgroup.erp.entity.Order;
import vn.com.ecotechgroup.erp.entity.Product;
import vn.com.ecotechgroup.erp.entity.User;
import vn.com.ecotechgroup.erp.repository.CustomerRepository;
import vn.com.ecotechgroup.erp.service.OrderService;
import vn.com.ecotechgroup.erp.service.ProductService;

@Controller
@RequestMapping("order")
@SessionAttributes(value = { "newOrder", "order" })
public class OrderControllerUser {

	private final String RETURN_PAGE = "page/order";
	private final String SHOW_PATH = "/{id}/show";
	private final String NEW_PATH = "/new-order";
	private final String UPDATE_PATH = "/{id}";
	private final String ADD_PATH = "/{id}";
	private final String DELETE_PATH = "/delete/{id}";
	private final String NAME_ATTRIBUTE = "order";

	private OrderService orderService;
	private ProductService productService;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Autowired
	public OrderControllerUser(OrderService orderService) {
		this.orderService = orderService;
	}

	@ModelAttribute(name = "newOrder")
	public Order newOrder() {
		return new Order();

	}

	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}

	private int default_page = 0;
	private int default_page_size = 50;

	@GetMapping("/{pageNumber}/{pageSize}")
	public String showOrderList(Model model,
			@Valid @PathVariable("pageNumber") @Min(0) Integer pageNumber,
			@Valid @PathVariable("pageSize") @Min(0) Integer pageSize,
			@RequestParam(name = "search", required = false) String searchTerm,
			@AuthenticationPrincipal User user) {
		Page<Order> orderList;

		if (searchTerm == null) {
			orderList = orderService.getListPageUser(
					PageRequest.of(pageNumber, pageSize,
							Sort.by("createAt").descending()),
					(Long) user.getId(), null);
		} else {
			searchTerm = searchTerm.toLowerCase();
			orderList = orderService.getListPageUser(
					PageRequest.of(pageNumber, pageSize,
							Sort.by("createAt").descending()),
					(Long) user.getId(), searchTerm);
		}
		model.addAttribute(NAME_ATTRIBUTE, orderList);
		model.addAttribute("isList", true);
		return RETURN_PAGE;
	}

	@GetMapping(SHOW_PATH)
	public String showOrder(@PathVariable("id") Long id, Model model,
			@AuthenticationPrincipal User user) {
		Optional<Order> orderObj = Optional.of(orderService.getOne(id));
		if (orderObj.isPresent()) {
			model.addAttribute("id", id);
			model.addAttribute(NAME_ATTRIBUTE, orderObj.get());
			model.addAttribute("isDetail", true);
			return RETURN_PAGE;
		} else {
			return showOrderList(model, default_page, default_page_size, null,
					user);
		}
	}

	@GetMapping(ADD_PATH)
	public String getOrder(@PathVariable("id") Long id, Model model,
			@AuthenticationPrincipal User user) {
		Optional<Order> orderObj = Optional.ofNullable(orderService.getOne(id));
		if (orderObj.isPresent()) {
			model.addAttribute(NAME_ATTRIBUTE, orderObj.get());

			// for create
			model.addAttribute("id", id);
			model.addAttribute("product", Integer.valueOf(0));
			model.addAttribute("price", Integer.valueOf(0));
			model.addAttribute("quantity", Integer.valueOf(0));

			// for delete
			model.addAttribute("productIndex", Integer.valueOf(0));

			model.addAttribute("isUpdate", true);
			orderService.getInformationForUser(model, user);
			return RETURN_PAGE;
		} else {
			return showOrderList(model, default_page, default_page_size, null,
					user);
		}
	}

	@PostMapping(value = UPDATE_PATH, params = "addProduct")
	public String updateOrderAddProduct(Model model,
			@ModelAttribute("order") Order order,
			@ModelAttribute("product") long productId,
			@ModelAttribute("price") Integer price,
			@ModelAttribute("quantity") Integer quantity,
			@AuthenticationPrincipal User user) {
		orderService.getInformationForUser(model, user);
		model.addAttribute("id", order.getId());
		if (productId == 0) {

			model.addAttribute("isUpdate", true);
			return RETURN_PAGE;
		} else {

			Product productFull = productService.getOne(productId);
			Order updateOrder = orderService.getOne(order.getId());

			orderService.addProduct(updateOrder, productFull, price, quantity);
			updateOrder = orderService.getOne(order.getId());

			model.addAttribute("order", updateOrder);
			model.addAttribute("isUpdate", true);
			return RETURN_PAGE;
		}
	}

	@PostMapping(value = UPDATE_PATH, params = "productIndex")
	public String updateOrderRemoveProduct(Model model,
			@ModelAttribute("order") Order order,
			@ModelAttribute("productIndex") int productIndex,
			@AuthenticationPrincipal User user) {

		long id = order.getId();
		orderService.removeProduct(order, productIndex);
		Order updateOrder = orderService.getOne(id);
		// for reset
		model.addAttribute("id", id);
		model.addAttribute("order", updateOrder);
		model.addAttribute("productIndex", Integer.valueOf(0));
		model.addAttribute("isUpdate", true);

		orderService.getInformationForUser(model, user);
		return RETURN_PAGE;
	}

	@PostMapping(value = UPDATE_PATH, params = "save")
	public String updateOrder(
			@Valid @ModelAttribute(NAME_ATTRIBUTE) Order order, Errors errors,
			SessionStatus session, Model model,
			@AuthenticationPrincipal User user) {
		if (errors.hasErrors()) {
			model.addAttribute("id", order.getId());
			orderService.getInformationForUser(model, user);
			model.addAttribute("isList", true);
			return RETURN_PAGE;
		} else {
			// if confirm update user who conform
			if (order.getIsConfirm() == true) {
				orderService.confirmOrder(order.getId(), user);
			} else {
				orderService.save(order);
			}
			session.setComplete();
			return showOrderList(model, default_page, default_page_size, null,
					user);
		}
	}

	@GetMapping(DELETE_PATH)
	public String deleteOrder(@PathVariable("id") Long id, Model model,
			@AuthenticationPrincipal User user) {
		try {
			System.out.println(id);
			orderService.delete(id);
		} catch (DataIntegrityViolationException e) {
			model.addAttribute("error",
					"Vui lòng xóa các dữ liệu có liên kết với dữ liệu này trước !");
			return "error";
		}
		return showOrderList(model, default_page, default_page_size, null,
				user);
	}

	@GetMapping(NEW_PATH)
	public String showOrderForm(Model model,
			@AuthenticationPrincipal User user) {

		orderService.getInformationForUser(model, user);
		model.addAttribute("isNew", true);

		// for create
		model.addAttribute("product", new Product());
		model.addAttribute("price", Integer.valueOf(0));
		model.addAttribute("quantity", Integer.valueOf(0));

		// for delete
		model.addAttribute("productIndex", Integer.valueOf(0));

		return RETURN_PAGE;
	}

	@PostMapping(value = NEW_PATH, params = "addProduct")
	public String addRow(Model model,
			@ModelAttribute("product") Product product,
			@ModelAttribute("newOrder") Order newOrder,
			@ModelAttribute("price") Integer price,
			@ModelAttribute("quantity") Integer quantity, SessionStatus session,
			@AuthenticationPrincipal User user) {

		if (product.getId() == 0) {
			model.addAttribute("isNew", true);
			orderService.getInformationForUser(model, user);
			return RETURN_PAGE;
		} else {
			Order tmp = orderService.addProduct(newOrder, product, price,
					quantity);
			if (tmp.getId() != 0) {
				return "redirect:/order/" + tmp.getId();
			}
			model.addAttribute("isNew", true);
			orderService.getInformationForUser(model, user);
			return RETURN_PAGE;
		}
	}

//	@PostMapping(value = NEW_PATH, params = "productIndex")
//	public String removeRow(Model model,
//			@ModelAttribute("newOrder") Order order,
//			@ModelAttribute("productIndex") Integer productIndex) {
//
//		orderService.removeProduct(order, productIndex);
//		// for reset
//		model.addAttribute("productIndex", Integer.valueOf(0));
//		model.addAttribute("isNew", true);
//
//		orderService.getInformation(model);
//		return RETURN_PAGE;
//	}
//
	@PostMapping(value = NEW_PATH, params = "save")
	public String saveOrder(Model model,
			@ModelAttribute("newOrder") Order newOrder, SessionStatus session,
			@AuthenticationPrincipal User user) {
		orderService.save(newOrder);
		session.setComplete();
		model.addAttribute("isList", true);
		return showOrderList(model, default_page, default_page_size, null,
				user);
	}

//	@GetMapping(value = "/{id}/confirm")
//	public String confirmOrder(Model model,
//			@PathVariable("id") long id,
//			@AuthenticationPrincipal User user
//			) {
//		orderService.confirmOrder(id, user);
//		model.addAttribute("isList", true);
//		return showOrderList(model, default_page, default_page_size, null, user);
//	}
//	
//
}
