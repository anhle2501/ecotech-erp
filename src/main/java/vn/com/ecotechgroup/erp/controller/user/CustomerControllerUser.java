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

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import vn.com.ecotechgroup.erp.entity.Customer;
import vn.com.ecotechgroup.erp.entity.User;
import vn.com.ecotechgroup.erp.repository.CustomerRepository;

@Controller
@RequestMapping("customer")
public class CustomerControllerUser {

	@Autowired
	private CustomerRepository customerRepo;

	@ModelAttribute(name = "customer")
	public Customer customer() {
		return new Customer();
	}

	private int default_page = 0;
	private int default_page_size = 50;

	@GetMapping("/{pageNumber}/{pageSize}")
	public String showCustomerList(Model model,
			@Valid @PathVariable("pageNumber") @Min(0) Integer pageNumber,
			@Valid @PathVariable("pageSize") @Min(0) Integer pageSize,
			@RequestParam(name = "search", required = false) String searchTerm,
			@AuthenticationPrincipal User user) {
		// set current user
		Page<Customer> customerList;
		if (searchTerm != null) {
			searchTerm = searchTerm.toLowerCase();
			customerList = customerRepo.customerSearchListUser(
					PageRequest.of(pageNumber, pageSize,
							Sort.by("createdDate").ascending()),
					user == null ? null : (Long) user.getId(), searchTerm);
		} else {
			customerList = customerRepo.customerSearchListUser(
					PageRequest.of(pageNumber, pageSize,
							Sort.by("createdDate").ascending()),
					user == null ? null : (Long) user.getId(), null);
		}
		model.addAttribute("customer", customerList);
		model.addAttribute("isList", true);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("search", searchTerm);
		return "page/customer";
	}

	@GetMapping("/{id}/show")
	public String showCustomer(@PathVariable("id") long id, Model model,
			@AuthenticationPrincipal User user) {
		Optional<Customer> customerObj = customerRepo.findById(id);
		if (customerObj.isPresent()) {
			model.addAttribute("customer", customerObj.get());
			model.addAttribute("isDetail", true);
			return "page/customer";
		} else {
			return showCustomerList(model, default_page, default_page_size,
					null, user);
		}
	}

	@GetMapping("/{id}")
	public String getCustomer(@PathVariable("id") long id, Model model,
			@AuthenticationPrincipal User user) {
		Optional<Customer> customerObj = customerRepo.findById(id);
		if (customerObj.isPresent()) {
			model.addAttribute("customer", customerObj.get());
			model.addAttribute("isUpdate", true);
			return "page/customer";
		} else {
			return showCustomerList(model, default_page, default_page_size,
					null, user);
		}
	}

	@PostMapping("/{id}")
	public String updateCustomer(@PathVariable("id") int id,
			@Valid @ModelAttribute("customer") Customer customer, Errors errors,
			Model model, @AuthenticationPrincipal User user) {
		if (errors.hasErrors()) {
			model.addAttribute("isUpdate", true);
			return "page/customer";
		} else {
			customerRepo.save(customer);
			return showCustomerList(model, default_page, default_page_size,
					null, user);
		}
	}

	@GetMapping("/delete/{id}")
	public String deleteCustomer(@PathVariable("id") long id, Model model,
			Error error, @AuthenticationPrincipal User user) {
		try {
			customerRepo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			System.out.println("khách hàng");
			model.addAttribute("error",
					"Vui lòng xóa các dữ liệu có liên kết với dữ liệu này trước !");
			return "error";
		}
		return showCustomerList(model, default_page, default_page_size, null,
				user);
	}

	@GetMapping("/new-customer")
	public String showCustomerForm(Model model) {
		model.addAttribute("isNew", true);
		return "page/customer";
	}

	@PostMapping("/new-customer")
	public String createCustomer(
			@Valid @ModelAttribute("customer") Customer customer, Errors errors,
			Model model, @AuthenticationPrincipal User user) {
		if (errors.hasErrors()) {
			model.addAttribute("isNew", true);
			return "page/customer";
		} else {
			customerRepo.save(customer);
			return showCustomerList(model, default_page, default_page_size,
					null, user);
		}
	}

}
