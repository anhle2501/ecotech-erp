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
import vn.com.ecotechgroup.erp.entity.PaymentType;
import vn.com.ecotechgroup.erp.repository.PaymentTypeRepository;

@Controller
@RequestMapping("/payment-type")
public class PaymentTypeController {

	private final String RETURN_PAGE = "page/payment-type";
	private final String SHOW_PATH = "/{id}/show";
	private final String NEW_PATH = "/new-payment-type";
	private final String ADD_PATH = "/{id}";
	private final String UPDATE_PATH = "/{id}";
	private final String DELETE_PATH = "/delete/{id}";
	private final String NAME_ATTRIBUTE = "paymentType";
	
	@Autowired
	private PaymentTypeRepository paymentTypeRepo;	
	
	@ModelAttribute(name = NAME_ATTRIBUTE)
	public PaymentType paymentType() {
		return new PaymentType();
	}
	
	@GetMapping
	public String showPaymentTypeList(Model model) {
		List<PaymentType> paymentTypeList = paymentTypeRepo.findAll();
		model.addAttribute(NAME_ATTRIBUTE, paymentTypeList );
		model.addAttribute("isList", true);
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
			return showPaymentTypeList(model);
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
			return showPaymentTypeList(model);
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
			return showPaymentTypeList(model);
		}
	}
	
	@GetMapping(DELETE_PATH)
	public String deletePaymentType(@PathVariable("id") int id, Model model) {
		paymentTypeRepo.deleteById(id);
		return showPaymentTypeList(model);
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
			return showPaymentTypeList(model);
		}
	}
}
