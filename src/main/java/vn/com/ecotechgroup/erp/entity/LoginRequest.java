package vn.com.ecotechgroup.erp.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

	@NotBlank
	private String userName;

	@NotBlank
	private String password;

}
