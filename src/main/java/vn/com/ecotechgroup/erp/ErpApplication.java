package vn.com.ecotechgroup.erp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import jakarta.transaction.Transactional;


@SpringBootApplication
public class ErpApplication {
		
	@Value("${host.name}")
	private String name;
	@Value("${server.port}")
	private int port;
	
	@Bean
	@Transactional
	public CommandLineRunner cmd() {
		
		return (arg -> {
			System.out.println("port");
			System.out.println(name);
			System.out.println(port);
			System.out.println("test");

		});
	}

	public static void main(String[] args) {
		
		SpringApplication.run(ErpApplication.class, args);

	}

}
