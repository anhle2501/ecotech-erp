package vn.com.ecotechgroup.erp.audit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import vn.com.ecotechgroup.erp.entity.User;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditing {

    @Bean
    public AuditorAware<User> auditorProvider() {
    	
        return new AuditorAwareImp(); 
    }
}