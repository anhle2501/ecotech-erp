package vn.com.ecotechgroup.erp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import vn.com.ecotechgroup.erp.entity.Customer;
import vn.com.ecotechgroup.erp.repository.CustomerRepository;


@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerRepository customerRepo;

	@ModelAttribute(name="customer")
	public Customer customer() {
		return new Customer();
	}
	
	@GetMapping
	public String showCustomerList(Model model) {
		List<Customer> customerList = customerRepo.findAll();
		model.addAttribute("customer", customerList);
		return "page/customer";
	}
	
	@GetMapping("/{id}")
	public String showCustomer(@RequestParam int id, Model model) {
		Customer customerObj = customerRepo.getReferenceById(id);
		model.addAttribute("customer", customerObj);
		return "page/customer-detail";
	}
	
	@GetMapping("/new-customer")
	public String showCustomerForm(Model model) {
		model.addAttribute("isNewCustomer", true);
		return "page/customer";
	}
	
	@PostMapping("/new-customer")
	public String createCustomer(@Valid @ModelAttribute("customer") Customer customer, Errors errors, Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("isNewCustomer", true);
			return "page/customer";
		} else {
			customerRepo.save(customer);
			return "redirect:/customer";
		}
	}
}
