package vn.com.ecotechgroup.erp.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import vn.com.ecotechgroup.erp.dto.LoginRequest;
import vn.com.ecotechgroup.erp.dto.LoginResponse;
import vn.com.ecotechgroup.erp.entity.JwtTokenProvider;
import vn.com.ecotechgroup.erp.entity.User;
import vn.com.ecotechgroup.erp.service.UserService;

@RestController
@RequestMapping("/api")
//@CrossOrigin("http://localhost:1234")
public class LoginController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(
			@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
		System.out.println(loginRequest);
		// Xác thực từ username và password.
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(
						loginRequest.getUsername(),
						loginRequest.getPassword()));

		// Nếu không xảy ra exception tức là thông tin hợp lệ
		// Set thông tin authentication vào Security Context
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Trả về jwt cho người dùng.
		User user = (User) authentication.getPrincipal();
		String accessToken = tokenProvider.generateAccessToken(user);
		String refreshToken = tokenProvider.generateRefreshToken(user);
		
		// set cookie để trả về cho người dùng
		Cookie cookie = new Cookie("refreshToken", refreshToken);
		cookie.setHttpOnly(true);
		response.addCookie(cookie);
		return ResponseEntity.ok(new LoginResponse(accessToken));
		
	}

	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(
			@CookieValue("refreshToken") String refreshToken) {
			
			String newAccessToken = tokenProvider.refreshAccessToken(refreshToken);
			return ResponseEntity.ok(new LoginResponse().builder().accessToken(newAccessToken).build());
		
	}

}
