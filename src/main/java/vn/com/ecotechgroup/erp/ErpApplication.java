package vn.com.ecotechgroup.erp;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import vn.com.ecotechgroup.erp.entity.Order;
import vn.com.ecotechgroup.erp.entity.OrderProduct;
import vn.com.ecotechgroup.erp.entity.Product;
import vn.com.ecotechgroup.erp.repository.CustomerRepository;
import vn.com.ecotechgroup.erp.repository.OrderRepository;
import vn.com.ecotechgroup.erp.repository.PaymentTypeRepository;
import vn.com.ecotechgroup.erp.repository.ProductRepository;

@SpringBootApplication
public class ErpApplication {
	
	@Autowired
	private CustomerRepository repo;

	@Autowired
	private ProductRepository repo1;
	@Autowired
	private PaymentTypeRepository repo2;
	
	@Autowired
	private OrderRepository repo3;
	
	@Bean
	public CommandLineRunner cmd() {
		return arg -> {
			System.out.println("test");
			
			// order object
			
			// product object
			
			// payment object
			
			// 
			
//			Order order = new Order();
//			order.setOrderProduct(new ArrayList<>());
//			Product product = new Product(1, "test2", "test3", "test2", null);
//			
//			OrderProduct orderProduct = new OrderProduct();
//			orderProduct.setOrder(order);
//			orderProduct.setProduct(product);
//			orderProduct.setPrice(0);
//			
//			order.addOrderProduct(orderProduct);
//			System.out.println(order);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ErpApplication.class, args);
		
		
	}

}
