package vn.com.ecotechgroup.erp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import vn.com.ecotechgroup.erp.entity.Customer;
import vn.com.ecotechgroup.erp.entity.Order;
import vn.com.ecotechgroup.erp.entity.OrderProduct;
import vn.com.ecotechgroup.erp.entity.PaymentType;
import vn.com.ecotechgroup.erp.entity.Product;
import vn.com.ecotechgroup.erp.repository.OrderRepository;

@Controller
@RequestMapping("/order")
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
	
	@ModelAttribute(name = NAME_ATTRIBUTE)
	public Order order() {
		return new Order();
	}
	
	@ModelAttribute(name = "customer")
	public Customer customer() {
		return new Customer();
	}
	
	@ModelAttribute(name = "paymentType")
	public PaymentType paymentType() {
		return new PaymentType();
	}
	
	@ModelAttribute(name = "productList")
	public List<Product> productrList() {
		return new ArrayList<Product>();
	}
	
	@GetMapping
	public String showOrderList(Model model) {
		List<Order> orderList = orderRepo.findAll();
		model.addAttribute(NAME_ATTRIBUTE, orderList );
		model.addAttribute("isList", true);
		System.out.println(model);
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
			return showOrderList(model);
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
			return showOrderList(model);
		}
	}

	@PostMapping(UPDATE_PATH)
	public String updatePaymentType(@PathVariable("id") int id,
			@Valid @ModelAttribute(NAME_ATTRIBUTE) Order order, Errors errors,
			Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("isUpdate", true);
			return RETURN_PAGE;
		} else {
			orderRepo.save(order);
			return showOrderList(model);
		}
	}
	
	@GetMapping(DELETE_PATH)
	public String deletePaymentType(@PathVariable("id") int id, Model model) {
		orderRepo.deleteById(id);
		return showOrderList(model);
	}

	@GetMapping(NEW_PATH)
	public String showPaymentTypeForm(Model model) {
		model.addAttribute("isNew", true);
		return RETURN_PAGE;
	}

	@PostMapping(NEW_PATH)
	public String createPaymentType(
			@Valid @ModelAttribute(NAME_ATTRIBUTE) Order order,
			@Valid @ModelAttribute("customer") Customer customer,
			@Valid @ModelAttribute("productList") List<Product> productList, 
			@Valid @ModelAttribute("paymentType") PaymentType paymentType,
			Errors errors,
			Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("isNew", true);
			return RETURN_PAGE;
		} else {
			orderRepo.save(order);
			return showOrderList(model);
		}
	}
}
