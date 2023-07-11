package vn.com.ecotechgroup.erp.entity;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "user") 
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
@Data
public class User implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;
	
	
	
	@Column(length = 45, unique = true, nullable = false)
	@NotBlank(message = "Không để trống!")
	@Size(max = 45, message = "Ít hơn 45 ký tự!")
	private final String userName;
	
	@Column(length = 45, nullable = false)
	@NotBlank(message = "Không để trống!")
	@Size(min= 8, max=1000, message = "Nhiều hơn 8 ký tự!")
	private final String password;
	
	@Column(length = 45)
	@Size(max = 45, message = "Ít hơn 45 ký tự!")
	private final String firstName;
	@Column(length = 45)
	@Size(max = 45, message = "Ít hơn 45 ký tự!")
	private final String lastName;
	@Column(insertable = false, updatable = false)
	private String fullName;
	@Column(length = 45)
	@Size(max = 45, message = "Ít hơn 45 ký tự!")
	private final String mobilePhone;
	@Column(length = 1000)
	@Size(max = 1000, message = "Ít hơn 1000 ký tự!")
	private final String description;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}
	@Override
	public String getPassword() {
		
		return password ;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}
	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
	
		return false;
	}
	@Override
	public boolean isEnabled() {
		
		return true;
	}
	
	
}
