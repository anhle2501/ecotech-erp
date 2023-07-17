package vn.com.ecotechgroup.erp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import vn.com.ecotechgroup.erp.controller.CustomerController;
import vn.com.ecotechgroup.erp.entity.Authorities;
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
	
//	@Autowired
//	private Authorities au;
	
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
//		SpringApplication.run(ErpApplication.class, args);
		
		ApplicationContext context = SpringApplication.run(ErpApplication.class, args);
        // Lấy ra bean SimpleBean trong Context
        SimpleBean2 simpleBean = context.getBean(SimpleBean2.class);
        // In ra màn hình
        System.out.println("Simple Bean Example: " + simpleBean.toString());
		
        CustomerController customerControllers = context.getBean(CustomerController.class);
        System.out.println(customerControllers.toString());
	}

}
