package vn.com.ecotechgroup.erp.controller.admin;

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
import vn.com.ecotechgroup.erp.entity.User;
import vn.com.ecotechgroup.erp.service.UserService;

@Controller
@RequestMapping("admin/user")
public class UserController {

	private final String RETURN_PAGE = "page/admin/user";
	private final String SHOW_PATH = "/{id}/show";
	private final String NEW_PATH = "/new-user";
	private final String ADD_PATH = "/{id}";
	private final String UPDATE_PATH = "/{id}";
	private final String DELETE_PATH = "/delete/{id}";
	private final String NAME_ATTRIBUTE = "user";
	private int default_page = 0;
	private int default_page_size = 50;

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@ModelAttribute(name = NAME_ATTRIBUTE)
	public User user() {
		return new User();
	}

	@GetMapping("/{pageNumber}/{pageSize}")
	public String showUserList(Model model,
			@Valid @PathVariable("pageNumber") @Min(0) Integer pageNumber,
			@Valid @PathVariable("pageSize") @Min(0) Integer pageSize,
			@RequestParam(name = "search", required = false) String searchTerm) {
		Page<User> userList;
		if (searchTerm != null) {
			userList = userService.getListPage(
					PageRequest.of(pageNumber, pageSize), searchTerm);
		} else {
			userList = userService
					.getListPage(PageRequest.of(pageNumber, pageSize), "");
		}
		model.addAttribute(NAME_ATTRIBUTE, userList);
		model.addAttribute("isList", true);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("search", searchTerm);
		return RETURN_PAGE;
	}

	@GetMapping(SHOW_PATH)
	public String showUser(@PathVariable("id") long id, Model model) {
		Optional<User> userObj = Optional.ofNullable(userService.getOne(id));
		if (userObj.isPresent()) {
			System.out.println(userObj);
			model.addAttribute(NAME_ATTRIBUTE, userObj.get());
			model.addAttribute("isDetail", true);
			return RETURN_PAGE;
		} else {
			return showUserList(model, default_page, default_page_size, null);
		}
	}

	@GetMapping(ADD_PATH)
	public String getUser(@PathVariable("id") long id, Model model) {
		Optional<User> userObj = Optional.ofNullable(userService.getOne(id));
		if (userObj.isPresent()) {
			model.addAttribute(NAME_ATTRIBUTE, userObj.get());
			model.addAttribute("isUpdate", true);
			return RETURN_PAGE;
		} else {
			return showUserList(model, default_page, default_page_size, null);
		}
	}

	@PostMapping(UPDATE_PATH)
	public String updateUser(@PathVariable("id") int id,
			@Valid @ModelAttribute(NAME_ATTRIBUTE) User user,
			Errors errors, Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("isUpdate", true);
			return RETURN_PAGE;
		} else {
			userService.update(user);
			return showUserList(model, default_page, default_page_size, null);
		}
	}

	@GetMapping(DELETE_PATH)
	public String deleteUser(@PathVariable("id") long id, Model model) {
		try {
			userService.delete(id);
		} catch (DataIntegrityViolationException de) {
			model.addAttribute("error", "Vui lòng xóa dữ liệu liên quan trước khi xóa dữ liệu này !");
			return "error";
		}
		return showUserList(model, default_page, default_page_size, null);
	}

	@GetMapping(NEW_PATH)
	public String showUser(Model model) {
		model.addAttribute("isNew", true);
		return RETURN_PAGE;
	}

	@PostMapping(NEW_PATH)
	public String createUser(@Valid @ModelAttribute(NAME_ATTRIBUTE) User user,
			Errors errors, Model model) {
		
		if (errors.hasErrors()) {
			model.addAttribute("isNew", true);
			return RETURN_PAGE;
		} else {
			
			if (userService.checkUserNameDuplicate(user.getUserName()) == false ){
				userService.save(user);
				return showUserList(model, default_page, default_page_size, null);
			} else {
				model.addAttribute("error", "User name bị trùng !");
				model.addAttribute("isNew", true);
				return RETURN_PAGE;
			}
			
			
		}
	}
//	@Autowired
//	UserRepository uRep;
//	@Autowired
//	RoleRepository rRep;
//	@GetMapping("/test")
//	public String test() {
//		User u = uRep.findByUserName("nhutanh");
//		System.out.println(u);
//		
//		List<Role> x = (List<Role>) u.getAuthorities();
//		System.out.println(x);
//		
//		System.out.println(x.get(0).getAuthority());
//		
//		Role r = rRep.getReferenceById((long) 1);
//		System.out.println(r);
//		return "index";
//	}
}
