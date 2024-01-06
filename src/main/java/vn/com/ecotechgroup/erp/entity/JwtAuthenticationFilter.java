package vn.com.ecotechgroup.erp.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import vn.com.ecotechgroup.erp.repository.UserRepository;
import vn.com.ecotechgroup.erp.service.UserService;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private JwtTokenProvider tokenProvider;
	@Autowired
	private UserDetailsService userSer;

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException, java.io.IOException {
		try {
			System.out.println("JwtAuthenticationFilter");
			// Lấy jwt từ request
			String jwt = getJwtFromRequest(request);

			if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
				// Lấy id user từ chuỗi jwt
				String userName = tokenProvider.extractUsername(jwt);
				// Lấy thông tin người dùng từ id
				UserDetails userDetails = userSer.loadUserByUsername(userName);

				if (userDetails != null) {
					// Nếu người dùng hợp lệ, set thông tin cho Seturity Context
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					logger.info(authentication);
					authentication
							.setDetails(new WebAuthenticationDetailsSource()
									.buildDetails(request));

					SecurityContextHolder.getContext()
							.setAuthentication(authentication);
				}
			}
		} catch (Exception ex) {
			log.error("failed on set user authentication", ex);
		}

		filterChain.doFilter(request, response);
	}

	private String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		// Kiểm tra xem header Authorization có chứa thông tin jwt không
		if (StringUtils.hasText(bearerToken)
				&& bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}
}
