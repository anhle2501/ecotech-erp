package vn.com.ecotechgroup.erp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    SimpleBean2 simpleBeanConfigure(){
        // Khởi tạo một instance của SimpleBean và trả ra ngoài
        return new SimpleBean2("loda222222");
    }
}