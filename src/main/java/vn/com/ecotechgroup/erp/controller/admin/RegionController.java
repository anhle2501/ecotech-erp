package vn.com.ecotechgroup.erp.controller.admin;

import jakarta.transaction.Transactional;
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
import vn.com.ecotechgroup.erp.entity.Customer;
import vn.com.ecotechgroup.erp.entity.Region;
import vn.com.ecotechgroup.erp.entity.User;
import vn.com.ecotechgroup.erp.repository.CustomerRepository;
import vn.com.ecotechgroup.erp.repository.RegionRepository;
import vn.com.ecotechgroup.erp.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin/region")
public class RegionController {
    
    private final String RETURN_PAGE = "page/admin/region";
    private final String SHOW_PATH = "/{id}/show";
    private final String NEW_PATH = "/new-region";
    private final String ADD_PATH = "/{id}";
    private final String UPDATE_PATH = "/{id}";
    private final String DELETE_PATH = "/delete/{id}";
    private final String NAME_ATTRIBUTE = "region";
    private int default_page = 0;
    private int default_page_size = 50;

    @Autowired
    private UserRepository userRep;

    @Autowired
    private RegionRepository regionRepo;

    @Autowired
    private CustomerRepository customerRepo;

    @ModelAttribute(name = NAME_ATTRIBUTE)
    public Region region() {
        return new Region();
    }

    @GetMapping("/{pageNumber}/{pageSize}")
    public String showRegionList(Model model,
                                      @Valid @PathVariable("pageNumber") @Min(0) Integer pageNumber,
                                      @Valid @PathVariable("pageSize") @Min(0) Integer pageSize,
                                      @RequestParam(name = "search", required = false) String searchTerm) {

        Page<Region> regionList;
        if (searchTerm != null) {
            regionList = regionRepo.regionSearchList(
                    PageRequest.of(pageNumber, pageSize), searchTerm);
        } else {
            regionList = regionRepo.regionSearchList(
                    PageRequest.of(pageNumber, pageSize), "");
        }
        model.addAttribute(NAME_ATTRIBUTE, regionList);
        model.addAttribute("isList", true);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("search", searchTerm);
        return RETURN_PAGE;
    }

    @GetMapping(SHOW_PATH)
    public String showRegion(@PathVariable("id") long id, Model model) {
        Optional<Region> regionObj = regionRepo.findById(id);
        if (regionObj.isPresent()) {
            model.addAttribute(NAME_ATTRIBUTE, regionObj.get());
            model.addAttribute("isDetail", true);
            return RETURN_PAGE;
        } else {
            return showRegionList(model, default_page, default_page_size,
                    null);
        }
    }

    @GetMapping(ADD_PATH)
    public String getRegion(@PathVariable("id") long id, Model model) {
        Optional<Region> regionObj = regionRepo.findById(id);
        List<Customer> cl = customerRepo.findAll();
        List<User> ul = userRep.findAll();
        if (regionObj.isPresent()) {
            model.addAttribute(NAME_ATTRIBUTE, regionObj.get());
            model.addAttribute("isUpdate", true);
            model.addAttribute("customerList", cl);
            model.addAttribute("userList", ul);
            return RETURN_PAGE;
        } else {
            return showRegionList(model, default_page, default_page_size,
                    null);
        }
    }

    @PostMapping(UPDATE_PATH)
    @Transactional
    public String updateRegion(@PathVariable("id") int id,
                                    @Valid @ModelAttribute(NAME_ATTRIBUTE) Region region,
                                    Errors errors, Model model) {
        if (errors.hasErrors()) {
            List<Customer> lc = customerRepo.findAll();
            List<User> ul = userRep.findAll();
            model.addAttribute("isUpdate", true);
            model.addAttribute("customerList", lc);
            model.addAttribute("userList", ul);
            return RETURN_PAGE;
        } else {
            System.out.println(region);

            for (User user : region.getUsers()) {
                user.getRegions().add(region);
                userRep.save(user);
            }

            for (Customer customer : region.getCustomers()) {
                customer.getRegions().add(region);
                customerRepo.save(customer);
            }

            regionRepo.save(region);

            return showRegionList(model, default_page, default_page_size,
                    null);
        }
    }

    @GetMapping(DELETE_PATH)
    public String deleteRegion(@PathVariable("id") long id, Model model) {
        try {
            regionRepo.deleteById(id);
        } catch (DataIntegrityViolationException de) {
            model.addAttribute("error",
                    "Vui lòng xóa dữ liệu liên quan trước khi xóa dữ liệu này !");
            return "error";
        }
        return showRegionList(model, default_page, default_page_size,
                null);
    }

    @GetMapping(NEW_PATH)
    public String showRegionForm(Model model) {
        model.addAttribute("isNew", true);
        return RETURN_PAGE;
    }

    @PostMapping(NEW_PATH)
    public String createRegion(
            @Valid @ModelAttribute(NAME_ATTRIBUTE) Region region,
            Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("isNew", true);
            return RETURN_PAGE;
        } else {
            regionRepo.save(region);
            return showRegionList(model, default_page, default_page_size,
                    null);
        }
    }
}
