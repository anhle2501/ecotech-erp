package vn.com.ecotechgroup.erp.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.com.ecotechgroup.erp.entity.JwtTokenProvider;
import vn.com.ecotechgroup.erp.entity.LoginRequest;
import vn.com.ecotechgroup.erp.entity.LoginResponse;
import vn.com.ecotechgroup.erp.entity.User;

@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@PostMapping("/login")
	public LoginResponse authenticateUser(
			@RequestBody LoginRequest loginRequest) {

		System.out.println(loginRequest);
		// Xác thực từ username và password.
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(
						loginRequest.getUserName(),
						loginRequest.getPassword()));

		// Nếu không xảy ra exception tức là thông tin hợp lệ
		// Set thông tin authentication vào Security Context
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Trả về jwt cho người dùng.
		User user = (User) authentication.getPrincipal();
		String accessToken = tokenProvider.generateAccessToken(user);
		String refreshToken = tokenProvider.generateRefreshToken(user);
		return new LoginResponse(accessToken, refreshToken);
	}

	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(
			@RequestParam("refreshToken") String refreshToken) {
		// Validate the refresh token
		System.out.println("vo day chua");
		if (tokenProvider.validateToken(refreshToken)) {
			// If valid, generate a new access token
			SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			System.out.println(
					"SecurityContextHolder.getContext().getAuthentication().getPrincipal();");
			System.out.println(SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal());

//            String newAccessToken = tokenProvider.generateAccessToken(user);
			return ResponseEntity.ok(
					new LoginResponse().builder().accessToken("11").build());
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body("Invalid refresh token");
		}
	}

}
