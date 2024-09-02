package vn.com.ecotechgroup.erp.controller.admin;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import vn.com.ecotechgroup.erp.entity.Role;
import vn.com.ecotechgroup.erp.repository.RoleRepository;

import java.util.Optional;

@Controller
@RequestMapping("admin/role")
public class RoleController {

	private final String RETURN_PAGE = "page/admin/role";
	private final String SHOW_PATH = "/{id}/show";
	private final String NEW_PATH = "/new-role";
	private final String ADD_PATH = "/{id}";
	private final String UPDATE_PATH = "/{id}";
	private final String DELETE_PATH = "/delete/{id}";
	private final String NAME_ATTRIBUTE = "role";
	private int default_page = 0;
	private int default_page_size = 50;

	@Autowired
	private RoleRepository roleRepo;

	@ModelAttribute(name = NAME_ATTRIBUTE)
	public Role role() {
		return new Role();
	}

	@GetMapping("/{pageNumber}/{pageSize}")
	public String showRoleList(Model model,
			@Valid @PathVariable("pageNumber") @Min(0) Integer pageNumber,
			@Valid @PathVariable("pageSize") @Min(0) Integer pageSize,
			@RequestParam(name = "search", required = false) String searchTerm) {

		Page<Role> roleList;
		if (searchTerm != null) {
			roleList = roleRepo.roleSearchList(
					PageRequest.of(pageNumber, pageSize), searchTerm);
		} else {
			roleList = roleRepo.roleSearchList(
					PageRequest.of(pageNumber, pageSize), "");
		}
		model.addAttribute(NAME_ATTRIBUTE, roleList);
		model.addAttribute("isList", true);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("search", searchTerm);
		return RETURN_PAGE;
	}

	@GetMapping(SHOW_PATH)
	public String showRole(@PathVariable("id") long id, Model model) {
		Optional<Role> roleObj = roleRepo.findById(id);
		if (roleObj.isPresent()) {
			model.addAttribute(NAME_ATTRIBUTE, roleObj.get());
			model.addAttribute("isDetail", true);
			return RETURN_PAGE;
		} else {
			return showRoleList(model, default_page, default_page_size,
					null);
		}
	}

	@GetMapping(ADD_PATH)
	public String getRole(@PathVariable("id") long id, Model model) {
		Optional<Role> roleObj = roleRepo.findById(id);
		if (roleObj.isPresent()) {
			model.addAttribute(NAME_ATTRIBUTE, roleObj.get());
			model.addAttribute("isUpdate", true);
			return RETURN_PAGE;
		} else {
			return showRoleList(model, default_page, default_page_size,
					null);
		}
	}

	@PostMapping(UPDATE_PATH)
	public String updateRole(@PathVariable("id") int id,
			@Valid @ModelAttribute(NAME_ATTRIBUTE) Role role,
			Errors errors, Model model) {
		System.out.println(role);
		if (errors.hasErrors()) {
			model.addAttribute("isUpdate", true);
			return RETURN_PAGE;
		} else {
			roleRepo.save(role);
			return showRoleList(model, default_page, default_page_size,
					null);
		}
	}

	@GetMapping(DELETE_PATH)
	public String deleteRole(@PathVariable("id") long id, Model model) {
		try {
			roleRepo.deleteById(id);
		} catch (DataIntegrityViolationException de) {
			model.addAttribute("error",
					"Vui lòng xóa dữ liệu liên quan trước khi xóa dữ liệu này !");
			return "error";
		}
		return showRoleList(model, default_page, default_page_size,
				null);
	}

	@GetMapping(NEW_PATH)
	public String showRoleForm(Model model) {
		model.addAttribute("isNew", true);
		return RETURN_PAGE;
	}

	@PostMapping(NEW_PATH)
	public String createRole(
			@Valid @ModelAttribute(NAME_ATTRIBUTE) Role role,
			Errors errors, Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("isNew", true);
			return RETURN_PAGE;
		} else {
			roleRepo.save(role);
			return showRoleList(model, default_page, default_page_size,
					null);
		}
	}
}
