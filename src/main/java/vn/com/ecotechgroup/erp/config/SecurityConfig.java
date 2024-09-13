package vn.com.ecotechgroup.erp.config;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import vn.com.ecotechgroup.erp.entity.User;
import vn.com.ecotechgroup.erp.repository.UserRepository;

@Configuration
public class SecurityConfig {

	private HttpSecurity httpSecurity;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
//
//	@Bean
//	public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
//		return new SecurityEvaluationContextExtension();
//	}

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
	public UserDetailsService userDetailsService(
			UserRepository userRepository) {
		return username -> {
			User user = userRepository.findByUserName(username);
			if (user != null) {
				System.out.println(user);
				return user;
			}
			throw new UsernameNotFoundException(
					"User '" + username + "' not found");
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

//	@Bean
//	public RoleHierarchy roleHierarchy() {
//	    RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
//	    String hierarchy = "ROLE_ADMIN > ROLE_USER";
//	    roleHierarchy.setHierarchy(hierarchy);
//	    return roleHierarchy;
//	}
//	
//	@Bean
//	public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
//	    DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
//	    expressionHandler.setRoleHierarchy(roleHierarchy());
//	    return expressionHandler;
//	}

//	@Bean
//	public JwtAuthenticationFilter jwtAuthenticationFilter() {
//		return new JwtAuthenticationFilter();
//	}

	@Bean
	public AuthenticationManager authenticationManager(
			UserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);

		return new ProviderManager(authenticationProvider);
	}

//	@Bean
//	public FilterRegistrationBean<JwtAuthenticationFilter> tenantFilterRegistration(
//			JwtAuthenticationFilter filter) {
//		FilterRegistrationBean<JwtAuthenticationFilter> registration = new FilterRegistrationBean<>(
//				filter);
//		registration.setEnabled(false);
//		return registration;
//	}
	
//	@Bean
//	CorsConfigurationSource corsConfigurationSource() {
//		CorsConfiguration configuration = new CorsConfiguration();
//		configuration.setAllowedOrigins(Arrays.asList("http://localhost:1234"));
//		configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","PATCH", "DELETE"));
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", configuration);
//		return source;
//	}

	@Bean
	@Order(1)
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.securityMatcher("/**")
				.authorizeHttpRequests()
				.requestMatchers("/order/**", "/customer/**",
						"/payment-type/**").hasAnyRole("ADMIN", "USER")

				.requestMatchers("/admin/**").hasAnyAuthority("admin:read", "order:read", "customer:read", "payment-type:read", "role:read", "region:read", "product:read", "user:read", "ROLE_ADMIN")

				.requestMatchers("/", "/register", "/js/**", "/css/**",
						"/asset/**", "/index", "/error").permitAll()
				.and()
					.formLogin()
						.loginPage("/login")
						.loginProcessingUrl("/authenticate")
						.usernameParameter("username").passwordParameter("password")
						.defaultSuccessUrl("/index", true).and().logout()
						.logoutSuccessUrl("/login").permitAll().and().build();
	}

//	@Bean
//	@Order(2)
//	public SecurityFilterChain apiFilterChain(HttpSecurity http)
//			throws Exception {
//		return http
//				.cors(cors -> cors.disable())
//				.securityMatcher("/api/**").csrf(csrf -> csrf.disable())
//				.authorizeHttpRequests((authorize) -> authorize
//						.requestMatchers("/api/login").permitAll()
//						.requestMatchers("/api/customers/**", "/api/orders/**",
//								"/api/paymentType/**")
//						.hasAnyRole("USER", "ADMIN")
//
//						.requestMatchers("/api/cache/**").permitAll()
//						.requestMatchers("/api/refresh").permitAll())
//				.addFilterBefore(jwtAuthenticationFilter(),
//						UsernamePasswordAuthenticationFilter.class)
//				.sessionManagement(session -> session
//						.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//
//				.build();
//	}
}
