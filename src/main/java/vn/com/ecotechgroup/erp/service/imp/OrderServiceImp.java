package vn.com.ecotechgroup.erp.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.mysql.cj.log.Log;

import vn.com.ecotechgroup.erp.entity.Customer;
import vn.com.ecotechgroup.erp.entity.Order;
import vn.com.ecotechgroup.erp.entity.OrderProduct;
import vn.com.ecotechgroup.erp.entity.PaymentType;
import vn.com.ecotechgroup.erp.entity.Product;
import vn.com.ecotechgroup.erp.repository.CustomerRepository;
import vn.com.ecotechgroup.erp.repository.OrderRepository;
import vn.com.ecotechgroup.erp.repository.PaymentTypeRepository;
import vn.com.ecotechgroup.erp.repository.ProductRepository;
import vn.com.ecotechgroup.erp.service.OrderService;

@Service
public class OrderServiceImp implements OrderService {

	@Autowired
	private PaymentTypeRepository paymentTypeRepo;
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private ProductRepository productRepo;

	@Override
	public Order save(Order order) {
		return orderRepo.save(order);
	}


	@Override
	public Order update(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void delete(int orderId) {
		orderRepo.deleteById(orderId);
	}


	@Override
	public Order getOne(int id) {
		return orderRepo.getReferenceById(id);
	}


	@Override
	public Page<Order> getListPage(Pageable pageable, String searchTerm) {
		return orderRepo.orderSearchList(pageable, searchTerm);
	}

	@Override 
	public void getInformation(Model model) {
		List<PaymentType> paymentTypeList = paymentTypeRepo.findAll();
		List<Customer> customerList = customerRepo.findAll(Sort.by(Sort.Direction.ASC, "name"));
		List<Product> productList = productRepo.findAll();
		model.addAttribute("paymentTypeList", paymentTypeList);
		model.addAttribute("customerList", customerList);
		model.addAttribute("productListShow", productList);
	}
	
	@Override
	public void addProduct(Order order, Product product, int price, int quantity) {
		order.addProduct(product, price, quantity);
		orderRepo.save(order);
	}

	@Override
	public void removeProduct(Order order, Integer productIndex) {
		order.removeProduct(productIndex);
		orderRepo.save(order);
	}
	
	
}
