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
import vn.com.ecotechgroup.erp.repository.OrderProductRepository;
import vn.com.ecotechgroup.erp.repository.OrderRepository;
import vn.com.ecotechgroup.erp.repository.PaymentTypeRepository;
import vn.com.ecotechgroup.erp.repository.ProductRepository;
import vn.com.ecotechgroup.erp.service.OrderService;

@Service
public class OrderServiceImp implements OrderService {

	private PaymentTypeRepository paymentTypeRepo;
	private CustomerRepository customerRepo;
	private OrderRepository orderRepo;
	private ProductRepository productRepo;
	private OrderProductRepository orderProductRepo;
	
	@Autowired
	public OrderServiceImp(PaymentTypeRepository paymentTypeRepo,
			CustomerRepository customerRepo, OrderRepository orderRepo,
			ProductRepository productRepo, OrderProductRepository orderProductRepo) {
		this.paymentTypeRepo = paymentTypeRepo;
		this.customerRepo = customerRepo;
		this.orderRepo = orderRepo;
		this.productRepo = productRepo;
		this.orderProductRepo = orderProductRepo;
	}


	@Override
	public Order save(Order order) {
		return orderRepo.save(order);
	}


	@Override
	public Order update(int id) {
		return null;
	}


	@Override
	public void delete(int orderId) {
		orderRepo.deleteById((long) orderId);
	}


	@Override
	public Order getOne(long id) {
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
	public Order addProduct(Order order, Product product, int price, int quantity) {
//		OrderProduct orderProduct = new OrderProduct(order, product, price, quantity);
//		orderProductRepo.save(orderProduct);
		order.addProduct(product, price, quantity);
//		//will double save because of cascade persist	
		return save(order);

	}

	@Override
	public void removeProduct(Order order, Long productIndex) {
		order.removeProduct(productIndex);
		save(order);
	}
	
	@Override
	public void removeProduct(Order order, long productId) {
		// cascade delete dont have so need to delete 2 time at 2 place
		order.removeProduct(productId);
		orderProductRepo.deleteById(productId);
		save(order);
	}
	
}
