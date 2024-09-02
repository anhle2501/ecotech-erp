package vn.com.ecotechgroup.erp.controller.admin;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import vn.com.ecotechgroup.erp.entity.Permission;

import vn.com.ecotechgroup.erp.repository.PermissionRepository;


import java.util.Optional;

@Controller
@RequestMapping("admin/permission")
public class PermissionController {

	private final String RETURN_PAGE = "page/admin/permission";
	private final String SHOW_PATH = "/{id}/show";
	private final String NEW_PATH = "/new-permission";
	private final String ADD_PATH = "/{id}";
	private final String UPDATE_PATH = "/{id}";
	private final String DELETE_PATH = "/delete/{id}";
	private final String NAME_ATTRIBUTE = "permission";
	private int default_page = 0;
	private int default_page_size = 50;

	@Autowired
	private PermissionRepository permissionRepo;

	@ModelAttribute(name = NAME_ATTRIBUTE)
	public Permission permission() {
		return new Permission();
	}

	@GetMapping("/{pageNumber}/{pageSize}")
	public String showPermissionList(Model model,
			@Valid @PathVariable("pageNumber") @Min(0) Integer pageNumber,
			@Valid @PathVariable("pageSize") @Min(0) Integer pageSize,
			@RequestParam(name = "search", required = false) String searchTerm) {

		Page<Permission> permissionList;
		if (searchTerm != null) {
			permissionList = permissionRepo.permissionSearchList(
					PageRequest.of(pageNumber, pageSize, Sort.by("id").ascending()), searchTerm);
		} else {
			permissionList = permissionRepo.permissionSearchList(
					PageRequest.of(pageNumber, pageSize, Sort.by("id").ascending()), "");
		}
		model.addAttribute(NAME_ATTRIBUTE, permissionList);
		model.addAttribute("isList", true);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("search", searchTerm);
		return RETURN_PAGE;
	}

	@GetMapping(SHOW_PATH)
	public String showPermission(@PathVariable("id") long id, Model model) {
		Optional<Permission> permissionObj = permissionRepo.findById(id);
		if (permissionObj.isPresent()) {
			model.addAttribute(NAME_ATTRIBUTE, permissionObj.get());
			model.addAttribute("isDetail", true);
			return RETURN_PAGE;
		} else {
			return showPermissionList(model, default_page, default_page_size,
					null);
		}
	}

	@GetMapping(ADD_PATH)
	public String getPermission(@PathVariable("id") long id, Model model) {
		Optional<Permission> permissionObj = permissionRepo.findById(id);
		if (permissionObj.isPresent()) {
			model.addAttribute(NAME_ATTRIBUTE, permissionObj.get());
			model.addAttribute("isUpdate", true);
			return RETURN_PAGE;
		} else {
			return showPermissionList(model, default_page, default_page_size,
					null);
		}
	}

	@PostMapping(UPDATE_PATH)
	public String updatePermission(@PathVariable("id") int id,
			@Valid @ModelAttribute(NAME_ATTRIBUTE) Permission permission,
			Errors errors, Model model) {
		System.out.println(permission);
		if (errors.hasErrors()) {
			model.addAttribute("isUpdate", true);
			return RETURN_PAGE;
		} else {
			permissionRepo.save(permission);
			return showPermissionList(model, default_page, default_page_size,
					null);
		}
	}

	@GetMapping(DELETE_PATH)
	public String deletePermission(@PathVariable("id") long id, Model model) {
		try {
			permissionRepo.deleteById(id);
		} catch (DataIntegrityViolationException de) {
			model.addAttribute("error",
					"Vui lòng xóa dữ liệu liên quan trước khi xóa dữ liệu này !");
			return "error";
		}
		return showPermissionList(model, default_page, default_page_size,
				null);
	}

	@GetMapping(NEW_PATH)
	public String showPermissionForm(Model model) {
		model.addAttribute("isNew", true);
		return RETURN_PAGE;
	}

	@PostMapping(NEW_PATH)
	public String createPermission(
			@Valid @ModelAttribute(NAME_ATTRIBUTE) Permission permission,
			Errors errors, Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("isNew", true);
			return RETURN_PAGE;
		} else {
			permissionRepo.save(permission);
			return showPermissionList(model, default_page, default_page_size,
					null);
		}
	}
}
