package vn.com.ecotechgroup.erp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import vn.com.ecotechgroup.erp.ErpApplication;
import vn.com.ecotechgroup.erp.repository.PermissionRepository;

@Configuration
@ComponentScan(basePackageClasses = ErpApplication.class)
public class ConfigTest {
	
//	@Bean 
//	public PermissionRepository pRep() {
//		return 
//	}
}
