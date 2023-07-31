package vn.com.ecotechgroup.erp.entity;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistrationForm {
	
	@Column(length = 45, unique = true, nullable = false)
	@NotBlank(message = "Không để trống!")
	@Length(max = 45, message = "Ít hơn 45 ký tự!")
	private  String userName;
	
	@Column(length = 45, nullable = false)
	@NotBlank(message = "Không để trống!")
	@Length(min= 8, max=1000, message = "Nhiều hơn 8 ký tự!")
	private  String password;
	
	@Column(length = 45)
	@Length(max = 45, message = "Ít hơn 45 ký tự!")
	private  String firstName;
	@Column(length = 45)
	@Length(max = 45, message = "Ít hơn 45 ký tự!")
	private  String lastName;
	
	@Column(length = 45)
	@Length(max = 45, message = "Ít hơn 45 ký tự!")
	private  String mobilePhone;
	
	@Column(length = 1000)
	@Length(max = 1000, message = "Ít hơn 1000 ký tự!")
	private  String description;

	public User toUser(PasswordEncoder passwordEncoder) {
		return new User(userName, passwordEncoder.encode(password), firstName, lastName,
				mobilePhone, description);
	}
}
