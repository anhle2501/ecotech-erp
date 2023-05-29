package vn.com.ecotechgroup.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import vn.com.ecotechgroup.erp.entity.Customer;
import vn.com.ecotechgroup.erp.entity.Order;
import vn.com.ecotechgroup.erp.entity.OrderProduct;
import vn.com.ecotechgroup.erp.entity.PaymentType;
import vn.com.ecotechgroup.erp.entity.Product;
import vn.com.ecotechgroup.erp.repository.CustomerRepository;
import vn.com.ecotechgroup.erp.repository.OrderRepository;
import vn.com.ecotechgroup.erp.repository.PaymentTypeRepository;
import vn.com.ecotechgroup.erp.repository.ProductRepository;

@Service
public class OrderService {

	@Autowired
	private PaymentTypeRepository paymentTypeRepo;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	public void getInformation(Model model, int id) {
		List<PaymentType> paymentTypeList = paymentTypeRepo.findAll();
		List<Customer> customerList = customerRepository.findAll();
		Order order = orderRepository.getReferenceById(id);
		model.addAttribute("paymentTypeList", paymentTypeList);
		model.addAttribute("customerList", customerList);
		model.addAttribute("order", order);
	}
	
	public boolean addProduct (Order order, Product product, int price) throws Exception {
		OrderProduct orderProduct = new OrderProduct();
		orderProduct.setOrder(order);
		orderProduct.setProduct(product);
		orderProduct.setPrice(price);
		order.getOrderProduct().add(orderProduct);
		return true;
	}
	
	public boolean addOrder(List<OrderProduct> orderProduct, Order order, Customer customer, PaymentType paymentType) throws Exception {
		Order newOrder = order;
		newOrder.setPaymentType(paymentType);
		newOrder.setOrderProduct(orderProduct);
		newOrder.setCustomerOrder(customer);
		return true;
	}
	


}
