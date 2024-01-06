package vn.com.ecotechgroup.erp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import vn.com.ecotechgroup.erp.audit.AuditorAwareImp;
import vn.com.ecotechgroup.erp.entity.User;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
//@EnableJpaAuditing
public class JpaAuditingConfig {

	@Bean
	public AuditorAware<User> auditorProvider() {
		return new AuditorAwareImp();
	}
}