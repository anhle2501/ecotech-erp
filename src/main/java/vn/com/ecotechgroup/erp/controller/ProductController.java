package vn.com.ecotechgroup.erp.controller;

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
import vn.com.ecotechgroup.erp.entity.Product;
import vn.com.ecotechgroup.erp.repository.ProductRepository;

@Controller
@RequestMapping("/product")
public class ProductController {

	private final String RETURN_PAGE = "page/product";
	private final String SHOW_PATH = "/{id}/show";
	private final String NEW_PATH = "/new-product";
	private final String ADD_PATH = "/{id}";
	private final String UPDATE_PATH = "/{id}";
	private final String DELETE_PATH = "/delete/{id}";
	private final String NAME_ATTRIBUTE = "product";
	
	@Autowired
	private ProductRepository productRepo;	
	
	@ModelAttribute(name = NAME_ATTRIBUTE)
	public Product product() {
		return new Product();
	}
	
	@GetMapping
	public String showProductList(Model model) {
		List<Product> paymentTypeList = productRepo.findAll();
		model.addAttribute(NAME_ATTRIBUTE, paymentTypeList );
		model.addAttribute("isList", true);
		System.out.println(model);
		return RETURN_PAGE;
	}
	
	@GetMapping(SHOW_PATH)
	public String showPaymentType(@PathVariable("id") int id, Model model) {
		Optional<Product> paymentTypeObj = productRepo.findById(id);
		if (paymentTypeObj.isPresent()) {
			model.addAttribute(NAME_ATTRIBUTE, paymentTypeObj);
			model.addAttribute("isDetail", true);
			return RETURN_PAGE;
		} else {
			return showProductList(model);
		}
	}
	
	@GetMapping(ADD_PATH)
	public String getPaymentType(@PathVariable("id") int id, Model model) {
		Optional<Product> paymentTypeObj = productRepo.findById(id);
		if (paymentTypeObj.isPresent()) {
			model.addAttribute(NAME_ATTRIBUTE, paymentTypeObj);
			model.addAttribute("isUpdate", true);
			return RETURN_PAGE;
		} else {
			return showProductList(model);
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
			return showProductList(model);
		}
	}
	
	@GetMapping(DELETE_PATH)
	public String deletePaymentType(@PathVariable("id") int id, Model model) {
		productRepo.deleteById(id);
		return showProductList(model);
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
			return showProductList(model);
		}
	}
}
