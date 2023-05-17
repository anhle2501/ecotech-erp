package vn.com.ecotechgroup.erp;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
//			System.out.println("repo.count()"+ repo.count()); 
//			Iterator x = repo.findAll().iterator();
//			while (x.hasNext()) {
//				System.out.println(x.next());
//			}
//			Iterator x1 = repo1.findAll().iterator();
//			while (x1.hasNext()) {
//				System.out.println(x1.next());
//			}
			
//			Iterator x3 = repo3.findAll().iterator();
//			while (x3.hasNext()) {
//				System.out.println(x3.next());
//			}
//			
//			Iterator x2 = repo2.findAll().iterator();
//			while (x2.hasNext()) {
//				System.out.println(x2.next().toString());
//			}
			

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ErpApplication.class, args);
		
		
	}

}
