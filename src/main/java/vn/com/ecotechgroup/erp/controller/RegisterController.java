package vn.com.ecotechgroup.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import vn.com.ecotechgroup.erp.entity.RegistrationForm;
import vn.com.ecotechgroup.erp.entity.User;
import vn.com.ecotechgroup.erp.repository.UserRepository;

@Controller
@RequestMapping("/register")
public class RegisterController {

	private UserRepository userRepository;
	
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public RegisterController(UserRepository userRepository,
			PasswordEncoder encoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = encoder;
	}
	
	@ModelAttribute("user")
	public RegistrationForm createForm() {
		return new RegistrationForm();
	}

	@GetMapping
	public String registerForm() {
		return "page/registration";
	}

	@PostMapping
	public String processRegistration( @ModelAttribute("user") @Valid RegistrationForm form, Errors errors)
	{
		System.out.println(form);
		System.out.println(errors  );
		if (errors.hasErrors()) {
			
			return "page/registration";
		} else {
			userRepository.save(form.toUser(passwordEncoder));			
			return "redirect:/login";
		}
	}
}
