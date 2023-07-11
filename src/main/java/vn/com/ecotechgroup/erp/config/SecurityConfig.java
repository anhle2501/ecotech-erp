package vn.com.ecotechgroup.erp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import vn.com.ecotechgroup.erp.entity.User;
import vn.com.ecotechgroup.erp.repository.UserRepository;

@Configuration
public class SecurityConfig {

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

	@Bean
	public UserDetailsService userDetailsService(UserRepository userRepository) {
			return username -> {
				User user = userRepository.findByUserName(username);
				if (user != null) return user; 
				throw new UsernameNotFoundException("User '" + username + "' not found");
			};
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	 return http
			 .authorizeHttpRequests()
			 	.requestMatchers("/order", "/customer", "/payment-type")
			 		.hasRole("USER")
		 		.requestMatchers("/", "/**")
		 			.permitAll()
		 	.and()
			 .build();
	}
}
