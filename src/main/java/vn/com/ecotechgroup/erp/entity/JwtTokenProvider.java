package vn.com.ecotechgroup.erp.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtTokenProvider {
	// Đoạn JWT_SECRET này là bí mật, chỉ có phía server biết
	private static final String JWT_SECRET = "nhutanhle12345678901674749933nhutanhle12345678901674749933nhutanhle12345678901674749933";

	// Thời gian có hiệu lực của chuỗi jwt
	private static final long ACCESS_TOKEN_EXPIRATION = 1000 * 60 * 15; // 15
																		// minutes
	private static final long REFRESH_TOKEN_EXPIRATION = 1000 * 60 * 60 * 24 * 7; // 7 days

	@Autowired
	public UserDetailsService userService;

	private String createToken(Map<String, Object> claims, String subject,
			long expiration) {
		return Jwts.builder().claims(claims).subject(subject)
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS256, JWT_SECRET).compact();
	}

	private boolean isTokenExpired(String token) {
		final Date expiration = extractExpiration(token);
		return expiration.before(new Date());
	}

	public String extractUsername(String token) {
		return extractClaims(token).getSubject();
	}

	private Date extractExpiration(String token) {
		return extractClaims(token).getExpiration();
	}

	private Claims extractClaims(String token) {
		return Jwts.parser().setSigningKey(JWT_SECRET).build()
				.parseClaimsJws(token).getBody();
	}

	public boolean isAccessTokenExpired(String token) {
		return isTokenExpired(token);
	}

	public boolean isRefreshTokenExpired(String token) {
		return isTokenExpired(token);
	}

	public String generateAccessToken(User user) {
		return generateToken(user, ACCESS_TOKEN_EXPIRATION);
	}

	public String generateRefreshToken(User user) {
		return generateToken(user, REFRESH_TOKEN_EXPIRATION);
	}

	public String generateToken(User user, long expiration) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("permissions", user.getPermissionsOnly(user.getListRole()));
		return createToken(claims, user.getUsername(), expiration);
	}

	public String refreshAccessToken(String refreshToken) {
		if (validateToken(refreshToken)) {
			String username = extractUsername(refreshToken);
			return generateAccessToken(
					(User) userService.loadUserByUsername(username));
		}
		return null;
	}

	public boolean validateToken(String authToken) {
		try {
			String username = extractUsername(authToken);
			return (username.equals(
					userService.loadUserByUsername(username).getUsername())
					&& !isAccessTokenExpired(authToken));

		} catch (MalformedJwtException ex) {
			log.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			log.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			log.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			log.error("JWT claims string is empty.");
		}
		return false;
	}

}