package vn.com.ecotechgroup.erp.controller.user;

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
import vn.com.ecotechgroup.erp.repository.UserRepository;
import vn.com.ecotechgroup.erp.service.UserService;

@Controller
@RequestMapping("/register")
public class RegisterControllerUser {

	private UserService userService;

	private PasswordEncoder passwordEncoder;

	@Autowired
	public RegisterControllerUser(UserService userService,
			PasswordEncoder encoder) {
		this.userService = userService;
		this.passwordEncoder = encoder;
	}

//	@Autowired
//	public RegisterController(
//			UserService userService) {
//		this.userService = userService;
//	}

	@ModelAttribute("user")
	public RegistrationForm createForm() {
		return new RegistrationForm();
	}

	@GetMapping
	public String registerForm() {
		return "page/registration";
	}

	@PostMapping
	public String processRegistration(
			@ModelAttribute("user") @Valid RegistrationForm form,
			Errors errors) {
		if (errors.hasErrors()) {

			return "page/registration";
		} else {
			userService.save(form.toUser(passwordEncoder));
			return "redirect:/login";
		}
	}
}
