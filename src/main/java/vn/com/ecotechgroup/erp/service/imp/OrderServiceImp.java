package vn.com.ecotechgroup.erp.service.imp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import vn.com.ecotechgroup.erp.entity.Customer;
import vn.com.ecotechgroup.erp.entity.Order;
import vn.com.ecotechgroup.erp.entity.PaymentType;
import vn.com.ecotechgroup.erp.entity.Product;
import vn.com.ecotechgroup.erp.entity.User;
import vn.com.ecotechgroup.erp.repository.CustomerRepository;
import vn.com.ecotechgroup.erp.repository.OrderProductRepository;
import vn.com.ecotechgroup.erp.repository.OrderRepository;

import vn.com.ecotechgroup.erp.repository.PaymentTypeRepository;
import vn.com.ecotechgroup.erp.repository.ProductRepository;
import vn.com.ecotechgroup.erp.repository.UserRepository;
import vn.com.ecotechgroup.erp.service.OrderService;
import vn.com.ecotechgroup.erp.service.UiService;

@Service
public class OrderServiceImp implements OrderService, UiService {

	private PaymentTypeRepository paymentTypeRepo;
	private CustomerRepository customerRepo;

	@Autowired
	private OrderRepository orderRepo;

	private ProductRepository productRepo;
	private OrderProductRepository orderProductRepo;

	private UserRepository userRepo;

	@Autowired
	public OrderServiceImp(PaymentTypeRepository paymentTypeRepo,
			CustomerRepository customerRepo,
//			OrderRepository orderRepo, 
			ProductRepository productRepo,
			OrderProductRepository orderProductRepo, UserRepository userRepo) {
		this.paymentTypeRepo = paymentTypeRepo;
		this.customerRepo = customerRepo;
//		this.orderRepo = orderRepo;
		this.productRepo = productRepo;
		this.orderProductRepo = orderProductRepo;
		this.userRepo = userRepo;
	}

	@Override
	public Order save(Order order) {

		return orderRepo.save(order);
	}

	@Override
	public Order update(Order order) {
		return orderRepo.save(order);
	}

	@Override
	public void delete(Long orderId) {
		System.out.println(orderId);
		orderRepo.deleteById(orderId);
	}

	@Override
	public Order getOne(Long id) {
		return orderRepo.getReferenceById(id);
	}

	@Override
	public Page<Order> getListPage(Pageable pageable, String searchTerm) {
		return orderRepo.orderSearchList(pageable, searchTerm);
	}

	@Override
	public Page<Order> getListPageUser(PageRequest of, Long user_id,
			String searchTerm) {
		return orderRepo.orderSearchListUser(of, user_id, searchTerm);
	}

	@Override
	public void getInformation(Model model) {
		List<PaymentType> paymentTypeList = paymentTypeRepo.findAll();
		List<Customer> customerList = customerRepo
				.findAll(Sort.by(Sort.Direction.ASC, "name"));
		List<Product> productList = productRepo.findAll();
		model.addAttribute("paymentTypeList", paymentTypeList);
		model.addAttribute("customerList", customerList);
		model.addAttribute("productListShow", productList);
	}

	@Override
	public Order addProduct(Order order, Product product, int price,
			int quantity) {
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

	@Override
	public void confirmOrder(Long orderId, User user) {
		// confirm
		Optional<Order> order = Optional.of(getOne(orderId));
		if (order.isPresent() == true) {
			Order ord = order.get();
			// update only when it is not confirm
			if (ord.getIsConfirm() != true) {
				ord.setIsConfirm(true);
				// set user
				ord.setConfirmAt(LocalDateTime.now());
				Optional<User> u = Optional
						.of(userRepo.findByUserName(user.getUserName()));
				if (u.isPresent() == true) {
					User us = u.get();
					ord.setConfirmByUser(us);
					save(ord);
				}
			}
		}
	}

	@Override
	public void getInformationForUser(Model model, User user) {
		List<PaymentType> paymentTypeList = paymentTypeRepo.findAll();

		List<Customer> customerList = customerRepo
				.getCustomerByCreatedByOrderByName(user);

		List<Product> productList = productRepo.findAll();
		model.addAttribute("paymentTypeList", paymentTypeList);
		model.addAttribute("customerList", customerList);
		model.addAttribute("productListShow", productList);
	}

}
