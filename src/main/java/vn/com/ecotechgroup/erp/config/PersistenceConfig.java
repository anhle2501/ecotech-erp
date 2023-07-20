package vn.com.ecotechgroup.erp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaAuditing
@EnableTransactionManagement
@EnableJpaRepositories
public class PersistenceConfig {

	 @Bean
	  public AuditorAware<AuditableUser> auditorProvider() {
	    return new AuditorAwareImpl();
	  }
}
