package vn.com.ecotechgroup.erp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import vn.com.ecotechgroup.erp.repository.UserRepository;

@SpringBootApplication
public class ErpApplication {
	
	@Autowired
	private UserRepository uRep;
	
	@Bean
	public CommandLineRunner cmd() {
		return arg -> {
			
			System.out.println("test");
			
		};
	}

	public static void main(String[] args) {
		
		SpringApplication.run(ErpApplication.class, args);
//		
//		ApplicationContext context = SpringApplication.run(ErpApplication.class, args);
//        // Lấy ra bean SimpleBean trong Context
//        SimpleBean2 simpleBean = context.getBean(SimpleBean2.class);
//        // In ra màn hình
//        System.out.println("Simple Bean Example: " + simpleBean.toString());
//		
//        CustomerController customerControllers = context.getBean(CustomerController.class);
//        System.out.println(customerControllers.toString());
	}

}
