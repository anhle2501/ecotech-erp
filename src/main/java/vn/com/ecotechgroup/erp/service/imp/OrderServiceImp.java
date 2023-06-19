package vn.com.ecotechgroup.erp.service.imp;

import java.util.List;

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
		// TODO Auto-generated method stub
		return orderRepo.save(order);
	}


	@Override
	public Order update(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void delete(Order t) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Order getOne(int id) {
		// TODO Auto-generated method stub
		return null;
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
		if (order.getOrderProduct().size() == 0) {
			order.addProduct(product, price, quantity);
			order.setTotalPrice(price * quantity);	
			
		} else {
			order.addProduct(product, price, quantity);
			List<OrderProduct> products = order.getOrderProduct();
			List<Integer> priceList = products.stream().map(p -> p.getPrice() * p.getQuantity()).toList();
			long total = (long) priceList.stream().reduce(0, (subtotal, element) -> (subtotal + element) );
			order.setTotalPrice(total);	
			
//			List<OrderProduct> newOrderProducts = products.stream().map(o -> {
//				o.setOrder(order);
//				return o;
//			}).toList();
//			System.out.println("newOrderProducts");
//			System.out.println(newOrderProducts);
//			order.setOrderProduct(newOrderProducts);
		}}


	@Override
	public void removeProduct(Order order, Integer productIndex) {
		if (order.getOrderProduct().size() != 0 && productIndex != 0) {
			List<OrderProduct> products = order.getOrderProduct();
			OrderProduct product = products.get(productIndex - 1);
			long total = product.getPrice() * product.getQuantity();
			order.setTotalPrice(order.getTotalPrice() - total);
			products.remove(productIndex - 1);
		}
	}
	
	
}
