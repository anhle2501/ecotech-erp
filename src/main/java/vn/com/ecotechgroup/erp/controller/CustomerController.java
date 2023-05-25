package vn.com.ecotechgroup.erp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import vn.com.ecotechgroup.erp.entity.Customer;
import vn.com.ecotechgroup.erp.repository.CustomerRepository;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepo;

	@ModelAttribute(name = "customer")
	public Customer customer() {
		return new Customer();
	}

	@GetMapping
	public String showCustomerList(Model model) {
		List<Customer> customerList = customerRepo.findAll();
		model.addAttribute("customer", customerList);
		model.addAttribute("isList", true);
		return "page/customer";
	}

	@GetMapping("/{id}/show")
	public String showCustomer(@PathVariable("id") int id, Model model) {
		Optional<Customer> customerObj = customerRepo.findById(id);
		if (customerObj.isPresent()) {
			model.addAttribute("customer", customerObj);
			model.addAttribute("isDetail", true);
			return "page/customer";
		} else {
			return showCustomerList(model);
		}
	}
	
	@GetMapping("/{id}")
	public String getCustomer(@PathVariable("id") int id, Model model) {
		Optional<Customer> customerObj = customerRepo.findById(id);
		if (customerObj.isPresent()) {
			model.addAttribute("customer", customerObj);
			model.addAttribute("isUpdate", true);
			return "page/customer";
		} else {
			return showCustomerList(model);
		}
	}

	@PostMapping("/{id}")
	public String updateCustomer(@PathVariable("id") int id,
			@Valid @ModelAttribute("customer") Customer customer, Errors errors,
			Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("isUpdate", true);
			return "page/customer";
		} else {
			customerRepo.save(customer);
			return showCustomerList(model);
		}
	}
	
	@GetMapping("/delete/{id}")
	public String deleteCustomer(@PathVariable("id") int id, Model model) {
		customerRepo.deleteById(id);
		return showCustomerList(model);
	}

	@GetMapping("/new-customer")
	public String showCustomerForm(Model model) {
		model.addAttribute("isNew", true);
		return "page/customer";
	}

	@PostMapping("/new-customer")
	public String createCustomer(
			@Valid @ModelAttribute("customer") Customer customer, Errors errors,
			Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("isNew", true);
			return "page/customer";
		} else {
			customerRepo.save(customer);
			return showCustomerList(model);
		}
	}
}
