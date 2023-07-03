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

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import vn.com.ecotechgroup.erp.entity.Customer;
import vn.com.ecotechgroup.erp.entity.PaymentType;
import vn.com.ecotechgroup.erp.repository.PaymentTypeRepository;

@Controller
@RequestMapping("payment-type")
public class PaymentTypeController {

	private final String RETURN_PAGE = "page/payment-type";
	private final String SHOW_PATH = "/{id}/show";
	private final String NEW_PATH = "/new-payment-type";
	private final String ADD_PATH = "/{id}";
	private final String UPDATE_PATH = "/{id}";
	private final String DELETE_PATH = "/delete/{id}";
	private final String NAME_ATTRIBUTE = "paymentType";
	private int default_page = 0;
	private int default_page_size = 50;
	
	@Autowired
	private PaymentTypeRepository paymentTypeRepo;	
	
	@ModelAttribute(name = NAME_ATTRIBUTE)
	public PaymentType paymentType() {
		return new PaymentType();
	}
	
	@GetMapping("/{pageNumber}/{pageSize}")
	public String showPaymentTypeList(Model model,
			@Valid @PathVariable("pageNumber") @Min(0) Integer pageNumber,
			@Valid @PathVariable("pageSize") @Min(0) Integer pageSize,
			@RequestParam(name = "search", required = false) String searchTerm
			) {

		Page<PaymentType> paymentTypeList;
		if (searchTerm != null) {
			paymentTypeList = paymentTypeRepo
					.paymentTypeSearchList(
							PageRequest.of(pageNumber, pageSize), searchTerm);
		} else {
			paymentTypeList = paymentTypeRepo
					.paymentTypeSearchList(PageRequest.of(pageNumber, pageSize), "");
		}
		model.addAttribute(NAME_ATTRIBUTE, paymentTypeList );
		model.addAttribute("isList", true);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("search", searchTerm);
		return RETURN_PAGE;
	}
	
	@GetMapping(SHOW_PATH)
	public String showPaymentType(@PathVariable("id") int id, Model model) {
		Optional<PaymentType> paymentTypeObj = paymentTypeRepo.findById(id);
		if (paymentTypeObj.isPresent()) {
			model.addAttribute(NAME_ATTRIBUTE, paymentTypeObj.get());
			model.addAttribute("isDetail", true);
			return RETURN_PAGE;
		} else {
			return showPaymentTypeList(model, default_page, default_page_size, null);
		}
	}
	
	@GetMapping(ADD_PATH)
	public String getPaymentType(@PathVariable("id") int id, Model model) {
		Optional<PaymentType> paymentTypeObj = paymentTypeRepo.findById(id);
		if (paymentTypeObj.isPresent()) {
			model.addAttribute(NAME_ATTRIBUTE, paymentTypeObj.get());
			model.addAttribute("isUpdate", true);
			return RETURN_PAGE;
		} else {
			return showPaymentTypeList(model, default_page, default_page_size, null);
		}
	}

	@PostMapping(UPDATE_PATH)
	public String updatePaymentType(@PathVariable("id") int id,
			@Valid @ModelAttribute(NAME_ATTRIBUTE) PaymentType paymentType, Errors errors,
			Model model) {
		System.out.println(paymentType);
		if (errors.hasErrors()) {
			model.addAttribute("isUpdate", true);
			return RETURN_PAGE;
		} else {
			paymentTypeRepo.save(paymentType);
			return showPaymentTypeList(model, default_page, default_page_size, null);
		}
	}
	
	@GetMapping(DELETE_PATH)
	public String deletePaymentType(@PathVariable("id") int id, Model model) {
		paymentTypeRepo.deleteById(id);
		return showPaymentTypeList(model, default_page, default_page_size, null);
	}

	@GetMapping(NEW_PATH)
	public String showPaymentTypeForm(Model model) {
		model.addAttribute("isNew", true);
		return RETURN_PAGE;
	}

	@PostMapping(NEW_PATH)
	public String createPaymentType(
			@Valid @ModelAttribute(NAME_ATTRIBUTE) PaymentType paymentType, Errors errors,
			Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("isNew", true);
			return RETURN_PAGE;
		} else {
			paymentTypeRepo.save(paymentType);
			return showPaymentTypeList(model, default_page, default_page_size, null);
		}
	}
}
