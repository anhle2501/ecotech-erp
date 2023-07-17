package vn.com.ecotechgroup.erp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.security.auth.message.config.AuthConfig;
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
//				System.out.println(user);
				if (user != null) return user; 
				throw new UsernameNotFoundException("User '" + username + "' not found");
			};
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	 return http
			 .authorizeHttpRequests()
			 	.requestMatchers("/order/**", "/customer/**", "/payment-type/**", "/product/**")
			 		.hasRole("USER")
		 		.requestMatchers("/","/register","/js/**", "/css/**", "/asset/**" ,"/index")
		 			.permitAll()
		 	.and()
		 	.formLogin()
		 		.loginPage("/login")
		 		.loginProcessingUrl("/authenticate")
		 		.usernameParameter("username")
		 		.passwordParameter("password")
		 		.defaultSuccessUrl("/order/0/50", true)
		 		.permitAll()
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
