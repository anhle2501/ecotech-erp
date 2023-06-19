package vn.com.ecotechgroup.erp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import vn.com.ecotechgroup.erp.repository.OrderProductRepository;
import vn.com.ecotechgroup.erp.repository.OrderRepository;
import vn.com.ecotechgroup.erp.service.OrderService;

@Controller
@RequestMapping("/order")
@SessionAttributes("newOrder")
public class OrderController {

	private final String RETURN_PAGE = "page/order";
	private final String SHOW_PATH = "/{id}/show";
	private final String NEW_PATH = "/new-order";
	private final String ADD_PATH = "/{id}";
	private final String UPDATE_PATH = "/{id}";
	private final String DELETE_PATH = "/delete/{id}";
	private final String NAME_ATTRIBUTE = "order";

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderProductRepository orderProductRepo;

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
			@RequestParam(name = "search", required = false) String searchTerm
			) {
		Page<Order> orderList;
		if (searchTerm == null) {
			orderList = orderService.getListPage(PageRequest.of(pageNumber, pageSize), "");
		} else {
			orderList = orderService.getListPage(PageRequest.of(pageNumber, pageSize), searchTerm);
		}
		model.addAttribute(NAME_ATTRIBUTE, orderList );
		model.addAttribute("isList", true);
		return RETURN_PAGE;
	}

	@GetMapping(SHOW_PATH)
	public String showPaymentType(@PathVariable("id") int id, Model model) {
		Optional<Order> orderObj = orderRepo.findById(id);
		if (orderObj.isPresent()) {
			model.addAttribute(NAME_ATTRIBUTE, orderObj.get());
			model.addAttribute("isDetail", true);
			return RETURN_PAGE;
		} else {
			return showOrderList(model, default_page, default_page_size, null);
		}
	}

	@GetMapping(ADD_PATH)
	public String getPaymentType(@PathVariable("id") int id, Model model) {
		Optional<Order> orderObj = orderRepo.findById(id);
		if (orderObj.isPresent()) {
			model.addAttribute(NAME_ATTRIBUTE, orderObj.get());
			model.addAttribute("isUpdate", true);
			return RETURN_PAGE;
		} else {
			return showOrderList(model, default_page, default_page_size, null);
		}
	}

	@PostMapping(UPDATE_PATH)
	public String updatePaymentType(@PathVariable("id") int id,
			@Valid @ModelAttribute(NAME_ATTRIBUTE) Order order, Errors errors,
			Model model) {
		orderService.getInformation(model);
		if (errors.hasErrors()) {
			model.addAttribute("isUpdate", true);
			return RETURN_PAGE;
		} else {
			orderService.save(order);
			return showOrderList(model, default_page, default_page_size, null);
		}
	}

	@GetMapping(DELETE_PATH)
	public String deletePaymentType(@PathVariable("id") int id, Model model) {
		orderRepo.deleteById(id);
		return showOrderList(model, default_page, default_page_size, null);
	}

	@GetMapping(NEW_PATH)
	public String showOrderForm(Model model) {

		orderService.getInformation(model);
		model.addAttribute("isNew", true);

		// for create
		model.addAttribute("product", new Product());
		model.addAttribute("price", Integer.valueOf(0));
		model.addAttribute("quantity", Integer.valueOf(0));

		// for delete
		model.addAttribute("productIndex", Integer.valueOf(0));

		return RETURN_PAGE;
	}

	@PostMapping(value = "/new-order", params = "addProduct")
	public String addRow(Model model,
			@ModelAttribute("product") Product product,
			@ModelAttribute("newOrder") Order newOrder,
			@ModelAttribute("price") Integer price,
			@ModelAttribute("quantity") Integer quantity) {

		System.out.println(product.getId());
		if (product.getId() == 0) {
			model.addAttribute("isNew", true);
			orderService.getInformation(model);
			return RETURN_PAGE;
		} else {
			orderService.addProduct(newOrder, product, price, quantity);
			orderRepo.save(newOrder);
			model.addAttribute("isNew", true);
			orderService.getInformation(model);
			return RETURN_PAGE;
		}
	}

	@PostMapping(value = "/new-order", params = "productIndex")
	public String removeRow(Model model,
			@ModelAttribute("newOrder") Order order,
			@ModelAttribute("productIndex") Integer productIndex) {
		orderService.removeProduct(order, productIndex);
		orderRepo.save(order);

		// for reset
		model.addAttribute("productIndex", Integer.valueOf(0));
		model.addAttribute("isNew", true);

		orderService.getInformation(model);
		return RETURN_PAGE;
	}

	@PostMapping(value = "/new-order", params = "save")
	public String addRow(Model model,
			@ModelAttribute("newOrder") Order newOrder, SessionStatus session) {
		orderRepo.save(newOrder);
		session.setComplete();
		model.addAttribute("isList", true);
		return showOrderList(model, default_page, default_page_size, null);
	}
}
