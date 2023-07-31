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

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import vn.com.ecotechgroup.erp.entity.PaymentType;
import vn.com.ecotechgroup.erp.entity.Product;
import vn.com.ecotechgroup.erp.repository.ProductRepository;
import vn.com.ecotechgroup.erp.service.ProductService;

@Controller
@RequestMapping("product")
public class ProductController {

	private final String RETURN_PAGE = "page/product";
	private final String SHOW_PATH = "/{id}/show";
	private final String NEW_PATH = "/new-product";
	private final String ADD_PATH = "/{id}";
	private final String UPDATE_PATH = "/{id}";
	private final String DELETE_PATH = "/delete/{id}";
	private final String NAME_ATTRIBUTE = "product";
	private int default_page = 0;
	private int default_page_size = 50;
	
	@Autowired
	private ProductRepository productRepo;	
	
	@Autowired
	public ProductController(ProductService productService) {
	}
	
	@ModelAttribute(name = NAME_ATTRIBUTE)
	public Product product() {
		return new Product();
	}
	
	@GetMapping("/{pageNumber}/{pageSize}")
	public String showProductList(Model model, 
			@Valid @PathVariable("pageNumber") @Min(0) Integer pageNumber,
			@Valid @PathVariable("pageSize") @Min(0) Integer pageSize,
			@RequestParam(name = "search", required = false) String searchTerm
			) {
		Page<Product> productList;
		if (searchTerm != null) {
			productList = productRepo
					.productSearchList(
							PageRequest.of(pageNumber, pageSize), searchTerm);
		} else {
			productList = productRepo
					.productSearchList(PageRequest.of(pageNumber, pageSize), "");
		}
		model.addAttribute(NAME_ATTRIBUTE, productList );
		model.addAttribute("isList", true);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("search", searchTerm);
		return RETURN_PAGE;
	}
	
	@GetMapping(SHOW_PATH)
	public String showPaymentType(@PathVariable("id") long  id, Model model) {
		Optional<Product> productObj = productRepo.findById(id);
		if (productObj.isPresent()) {
			model.addAttribute(NAME_ATTRIBUTE, productObj.get());
			model.addAttribute("isDetail", true);
			return RETURN_PAGE;
		} else {
			return showProductList(model, default_page, default_page_size, null);
		}
	}
	
	@GetMapping(ADD_PATH)
	public String getPaymentType(@PathVariable("id") long  id, Model model) {
		Optional<Product> productObj = productRepo.findById(id);
		if (productObj.isPresent()) {
			model.addAttribute(NAME_ATTRIBUTE, productObj.get());
			model.addAttribute("isUpdate", true);
			return RETURN_PAGE;
		} else {
			return showProductList(model, default_page, default_page_size, null);
		}
	}

	@PostMapping(UPDATE_PATH)
	public String updatePaymentType(@PathVariable("id") int id,
			@Valid @ModelAttribute(NAME_ATTRIBUTE) Product paymentType, Errors errors,
			Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("isUpdate", true);
			return RETURN_PAGE;
		} else {
			productRepo.save(paymentType);
			return showProductList(model, default_page, default_page_size, null);
		}
	}
	
	@GetMapping(DELETE_PATH)
	public String deletePaymentType(@PathVariable("id") long  id, Model model) {
		try {
			productRepo.deleteById(id);
		} catch (DataIntegrityViolationException de) {
			model.addAttribute("error", "Vui lòng xóa dữ liệu liên quan trước khi xóa dữ liệu này !");
			return "error";
		}
		return showProductList(model, default_page, default_page_size, null);
	}

	@GetMapping(NEW_PATH)
	public String showPaymentTypeForm(Model model) {
		model.addAttribute("isNew", true);
		return RETURN_PAGE;
	}

	@PostMapping(NEW_PATH)
	public String createPaymentType(
			@Valid @ModelAttribute(NAME_ATTRIBUTE) Product paymentType, Errors errors,
			Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("isNew", true);
			return RETURN_PAGE;
		} else {
			productRepo.save(paymentType);
			return showProductList(model, default_page, default_page_size, null);
		}
	}
}
