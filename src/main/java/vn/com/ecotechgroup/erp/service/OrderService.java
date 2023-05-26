package vn.com.ecotechgroup.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import vn.com.ecotechgroup.erp.entity.Customer;
import vn.com.ecotechgroup.erp.entity.Order;
import vn.com.ecotechgroup.erp.entity.PaymentType;
import vn.com.ecotechgroup.erp.repository.CustomerRepository;
import vn.com.ecotechgroup.erp.repository.OrderRepository;
import vn.com.ecotechgroup.erp.repository.PaymentTypeRepository;

@Service
public class OrderService {

	@Autowired
	private PaymentTypeRepository paymentTypeRepo;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	
	
	public void addOrderAttribute(Model model, int id) {
		List<PaymentType> paymentTypeList = paymentTypeRepo.findAll();
		List<Customer> customerList = customerRepository.findAll();
		Order order = orderRepository.getReferenceById(id);
		model.addAttribute("paymentTypeList", paymentTypeList);
		model.addAttribute("customerList", customerList);
		model.addAttribute("order", order);
	}

}
