package vn.com.ecotechgroup.erp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

import vn.com.ecotechgroup.erp.entity.User;
import vn.com.ecotechgroup.erp.repository.UserRepository;

@Configuration
public class SecurityConfig {

	private HttpSecurity httpSecurity;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// in memory
//	@Bean
//	public UserDetailsService userDetailsService(
//			PasswordEncoder passwordEncoder) {
//		List<UserDetails> usersList = new ArrayList<>();
//		usersList.add(new User("buzz", passwordEncoder.encode("password"),
//				Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
//		usersList.add(new User("woody", passwordEncoder.encode("password"),
//				Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
//		return new InMemoryUserDetailsManager(usersList);
//	}

	// custom
	
//	@Bean
//	public UserDetailsService users() {
//		UserDetails user = User.builder()
//			.username("user")
//			.password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
//			.roles("USER")
//			.build();
//		UserDetails admin = User.builder()
//			.username("admin")
//			.password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
//			.roles("USER", "ADMIN")
//			.build();
//		return new InMemoryUserDetailsManager(user, admin);
//	}

	@Bean
	public UserDetailsService userDetailsService(UserRepository userRepository) {
			return username -> {
				User user = userRepository.findByUserName(username);
				System.out.println(user);	
				System.out.println(user.getAuthorities());	
				if (user != null) return user; 
				throw new UsernameNotFoundException("User '" + username + "' not found");
			};
	}
	
	
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) 
//	  throws Exception {
//	    auth.jdbcAuthentication()
////	      .dataSource(dataSource)
////	      .usersByUsernameQuery("select email,password,enabled "
////	        + "from bael_users "
////	        + "where email = ?")
////	      .authoritiesByUsernameQuery("select email,authority "
////	        + "from authorities "
////	        + "where email = ?");
//	}
	
	@Bean
	public RoleHierarchy roleHierarchy() {
	    RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
	    String hierarchy = "ROLE_ADMIN > ROLE_USER";
	    roleHierarchy.setHierarchy(hierarchy);
	    return roleHierarchy;
	}
//	
//	@Bean
//	public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
//	    DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
//	    expressionHandler.setRoleHierarchy(roleHierarchy());
//	    return expressionHandler;
//	}
	
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	 return http
			 .authorizeHttpRequests()
			 	.requestMatchers("/order/**", 
			 				"/customer/**", 
			 				"/payment-type/**", 
			 				"/product/**"
			 				)
			 		.hasRole("USER")
		 		.requestMatchers("/order/**", 
		 				"/customer/**", 
		 				"/payment-type/**", 
		 				"/product/**",
		 				"/user/**"
		 				)
		 		.hasRole("ADMIN")
		 		.requestMatchers("/","/register","/js/**", "/css/**", "/asset/**" ,"/index")
		 			.permitAll()
		 	.and()
		 	.formLogin()
		 		.loginPage("/login")
		 		.loginProcessingUrl("/authenticate")
		 		.usernameParameter("username")
		 		.passwordParameter("password")
		 		.defaultSuccessUrl("/order/0/50", true)
		 	.and()
		 		.logout()
		 		.logoutSuccessUrl("/login")
		 		.permitAll()
//		 	.and()
//		 	 .exceptionHandling().accessDeniedPage("page/denied.html")
		 	 .and()
			 .build();
	}
}
