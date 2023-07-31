package vn.com.ecotechgroup.erp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import vn.com.ecotechgroup.erp.entity.PermissionInit;
import vn.com.ecotechgroup.erp.repository.PermissionRepository;

@SpringBootApplication
public class ErpApplication {
	
	@Autowired
	private PermissionRepository pRep;

	
	@Autowired
	public ErpApplication(PermissionRepository pRep) {
		super();
		this.pRep = pRep;
	}

	@Bean
	public CommandLineRunner cmd() {
		return arg -> {
			
			System.out.println("test");
			PermissionInit p = new PermissionInit(pRep);
			System.out.println("test1");
			System.out.println(p.getPermissionRep()); 
			System.out.println("test2");
			p.init(List.of(	"admin", "customer", "product", "payment-type"));
		
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
