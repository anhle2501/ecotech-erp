package vn.com.ecotechgroup.erp.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import vn.com.ecotechgroup.erp.entity.PaymentType;
import vn.com.ecotechgroup.erp.entity.Region;
import vn.com.ecotechgroup.erp.entity.User;
import vn.com.ecotechgroup.erp.repository.CustomerRepository;
import vn.com.ecotechgroup.erp.repository.RegionRepository;
import vn.com.ecotechgroup.erp.repository.UserRepository;
import vn.com.ecotechgroup.erp.service.CustomerService;
import vn.com.ecotechgroup.erp.service.imp.CustomerServiceImp;

import static java.lang.System.out;

@Controller
@RequestMapping("admin/customer")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private CustomerService cService;

	@Autowired
	private RegionRepository regionRepository;

	@Autowired
	private UserRepository uRep;

	@Autowired
	public CustomerController(CustomerRepository customerRepo,
			CustomerServiceImp cService) {
		this.customerRepo = customerRepo;
		this.cService = cService;
	}

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
			@RequestParam(name = "search", required = false) String searchTerm) {
		Page<Customer> customerList;
		if (searchTerm != null) {
			searchTerm = searchTerm.toLowerCase();
			System.out.println(searchTerm);
			customerList = cService.getListPage(PageRequest.of(pageNumber,
					pageSize, Sort.by("createdDate").ascending()), searchTerm);
		} else {

			customerList = customerRepo.findAll(PageRequest.of(pageNumber,
					pageSize, Sort.by("createdDate").ascending()));
		}
		model.addAttribute("customer", customerList);
		model.addAttribute("isList", true);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("search", searchTerm);
		return "page/admin/customer";
	}

	@GetMapping("/{id}/show")
	public String showCustomer(@PathVariable("id") long id, Model model) {
		Optional<Customer> customerObj = customerRepo.findById(id);
		Optional<List<User>> userList = Optional.ofNullable(uRep.findAllUser());

		if (customerObj.isPresent()) {
			model.addAttribute("customer", customerObj.get());
			model.addAttribute("isDetail", true);
            userList.ifPresent(users -> model.addAttribute("userList", users));
			return "page/admin/customer";
		} else {
			return showCustomerList(model, default_page, default_page_size,
					null);
		}
	}

	@GetMapping("/{id}")
	public String getCustomer(@PathVariable("id") long id, Model model) {
		Optional<Customer> customerObj = customerRepo.findById(id);
		Optional<List<User>> userList = Optional.ofNullable(uRep.findAllUser());
		Optional<List<Region>> regionList = Optional.of(regionRepository.findAll());

		if (customerObj.isPresent()) {
			model.addAttribute("customer", customerObj.get());
			System.out.println(customerObj.get().toString());
			System.out.println(regionList.get().toString());
			model.addAttribute("isUpdate", true);
			userList.ifPresent(users -> model.addAttribute("userList", users));
			regionList.ifPresent(regions -> model.addAttribute("regionList", regions));
			return "page/admin/customer";
		} else {
			return showCustomerList(model, default_page, default_page_size,
					null);
		}
	}

	@PostMapping("/{id}")
	public String updateCustomer(@PathVariable("id") int id,
			@Valid @ModelAttribute("customer") Customer customer, Errors errors,
			Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("isUpdate", true);
			return "page/admin/customer";
		} else {
			try {
				cService.update(customer);
			} catch (DataIntegrityViolationException e) {
				model.addAttribute("error", "Tên khách hàng đã tồn tại !");
				return "error";
			}
			return showCustomerList(model, default_page, default_page_size,
					null);
		}
	}

	@GetMapping("/delete/{id}")
	public String deleteCustomer(@PathVariable("id") long id, Model model,
			Error error) {
		try {
			customerRepo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			out.println("khách hàng");
			model.addAttribute("error",
					"Vui lòng xóa các dữ liệu có liên kết với dữ liệu này trước !");
			return "error";
		}
		return showCustomerList(model, default_page, default_page_size, null);
	}

	@GetMapping("/new-customer")
	public String showCustomerForm(Model model) {
		Optional<List<User>> userList = Optional.ofNullable(uRep.findAllUser());
		model.addAttribute("isNew", true);
		userList.ifPresent(users -> model.addAttribute("userList", users));

		return "page/admin/customer";
	}

	@PostMapping("/new-customer")
	public String createCustomer(
			@Valid @ModelAttribute("customer") Customer customer, Errors errors,
			Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("isNew", true);
			return "page/admin/customer";
		} else {
			try {
				customerRepo.save(customer);
			} catch (DataIntegrityViolationException e) {
				model.addAttribute("error", "Tên khách hàng đã tồn tại !");
				return "error";
			}
			return showCustomerList(model, default_page, default_page_size,
					null);
		}
	}
}
