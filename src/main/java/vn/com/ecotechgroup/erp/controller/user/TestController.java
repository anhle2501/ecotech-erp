package vn.com.ecotechgroup.erp.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import vn.com.ecotechgroup.erp.entity.Customer;
import vn.com.ecotechgroup.erp.repository.CustomerRepository;
import vn.com.ecotechgroup.erp.service.CustomerService;

@RestController
@Log4j2
public class TestController {

	@Autowired
	CustomerRepository cs;

	@Autowired
	CustomerService cs1;

	@GetMapping("/api/cache/{id}")
	public Customer rep(@PathVariable("id") Long l) {

		Customer customer = (Customer) cs1.getOne(l);
		return customer;
	}
}
