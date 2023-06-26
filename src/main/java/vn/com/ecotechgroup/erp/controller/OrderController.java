package vn.com.ecotechgroup.erp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import vn.com.ecotechgroup.erp.entity.Customer;
import vn.com.ecotechgroup.erp.entity.Order;
import vn.com.ecotechgroup.erp.entity.OrderProduct;
import vn.com.ecotechgroup.erp.entity.PaymentType;
import vn.com.ecotechgroup.erp.entity.Product;
import vn.com.ecotechgroup.erp.repository.CustomerRepository;
import vn.com.ecotechgroup.erp.repository.OrderProductRepository;
import vn.com.ecotechgroup.erp.repository.OrderRepository;
import vn.com.ecotechgroup.erp.repository.PaymentTypeRepository;
import vn.com.ecotechgroup.erp.repository.ProductRepository;
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

	private OrderService orderService;
	
	@Autowired
	public OrderController(OrderService orderService) {
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
	public String showOrder(@PathVariable("id") int id, Model model) {
		Optional<Order> orderObj = Optional.ofNullable(orderService.getOne(id));
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
		Optional<Order> orderObj = Optional.ofNullable(orderService.getOne(id));
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
		try {
			orderService.delete(id);
		} catch (DataIntegrityViolationException e) {
			model.addAttribute("error", "Vui lòng xóa các dữ liệu có liên kết với dữ liệu này trước !");
			return "error";
		}
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

	@PostMapping(value = NEW_PATH, params = "addProduct")
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
			model.addAttribute("isNew", true);
			orderService.getInformation(model);
			return RETURN_PAGE;
		}
	}

	@PostMapping(value = NEW_PATH, params = "productIndex")
	public String removeRow(Model model,
			@ModelAttribute("newOrder") Order order,
			@ModelAttribute("productIndex") Integer productIndex) {
		System.out.println("productIndex");
		System.out.println(productIndex);
		orderService.removeProduct(order, productIndex);
		// for reset
		model.addAttribute("productIndex", Integer.valueOf(0));
		model.addAttribute("isNew", true);

		orderService.getInformation(model);
		return RETURN_PAGE;
	}

	@PostMapping(value = NEW_PATH, params = "save")
	public String addRow(Model model,
			@ModelAttribute("newOrder") Order newOrder, SessionStatus session) {
		orderService.save(newOrder);
		session.setComplete();
		model.addAttribute("isList", true);
		return showOrderList(model, default_page, default_page_size, null);
	}
	
	@Autowired
	ProductRepository pRep; 
	
	@Autowired
	CustomerRepository cRep; 
	
	@Autowired
	PaymentTypeRepository ptRep; 
	
	
//	@GetMapping(value = "/test")
//	public String testFun(Model model) {
//		// create order
//		Order order = new Order();
//		
//		// get product
//		
//		Product pd = pRep.getReferenceById(1);
//		
//		// get customer
//		
//		Customer c = cRep.getReferenceById(1);
//		
//		// get paymentType
//		PaymentType pt = ptRep.getReferenceById(1);
//		
//		// create orderproduct
//		
//		OrderProduct op = new OrderProduct();
//		op.setOrder(order);
//		op.setProduct(pd);
//		op.setPrice(2);
//		op.setQuantity(2);
//		// add to order
//		order.getOrderProduct().add(op);
//		order.setCustomer(c);
//		order.setPaymentType(pt);
//		Or.save(order);
//		
//		return showOrderList(model, default_page, default_page_size, null); 
//	}
}
