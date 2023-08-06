package vn.com.ecotechgroup.erp;

import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import jakarta.transaction.Transactional;
import vn.com.ecotechgroup.erp.entity.Order;


@SpringBootApplication
public class ErpApplication {


	@Bean
	@Transactional
	public CommandLineRunner cmd() {
		return arg -> {
			
			System.out.println("test");
//			
//			Order order = new Order();
//			order.setDescription("123");
//			
//			Order save = oRep.save(order);
//			System.out.println(save);
//			oRep.delete(save);
			
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
