package vn.com.ecotechgroup.erp.entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {
	@NotNull
	private String accessToken;
	@NotNull
	private String refreshToken;
}
