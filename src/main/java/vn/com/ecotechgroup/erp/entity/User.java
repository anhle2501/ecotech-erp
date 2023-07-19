package vn.com.ecotechgroup.erp.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "user") 
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@Data
public class User implements UserDetails {
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;
	
	@Column(length = 45, unique = true, nullable = false)
	@NotBlank(message = "Không để trống!")
	@Length(max = 45, message = "Ít hơn 45 ký tự!")
	private String userName;
	
	@Column(length = 45, nullable = false)
	@NotBlank(message = "Không để trống!")
	@Length(min= 8, max=1000, message = "Nhiều hơn 8 ký tự!")
	private String password;
	
	@Column(length = 45)
	@Length(max = 45, message = "Ít hơn 45 ký tự!")
	private String firstName;
	@Column(length = 45)
	@Length(max = 45, message = "Ít hơn 45 ký tự!")
	private String lastName;
	@Column(insertable = false, updatable = false)
	private String fullName;
	@Column(length = 45)
	@Length(max = 45, message = "Ít hơn 45 ký tự!")
	private String mobilePhone;
	@Column(length = 1000)
	@Length(max = 1000, message = "Ít hơn 1000 ký tự!")
	private String description;
	
	@Column(insertable = false)
	private boolean nonLock;
	@Column(insertable = false)
	private boolean nonExpired;
	@Column(insertable = false)
	private boolean pwNonExpired;
	@Column(insertable = false)
	private boolean enable;
//	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="user_id_au")
	private List<Authorities> listAuth = new ArrayList<>();
	
	
//	 @OneToMany(mappedBy = "userOrdered", fetch = FetchType.LAZY)
//	 private List<Order> listOrders;
	
//	public String getUserName() {
//		return this.userName;
//	}
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return listAuth.stream().map(e -> new SimpleGrantedAuthority(e.getAuthority())).toList();
	}
	@Override
	public String getPassword() {
		return password ;
	}
	@Override
	public String getUsername() {
		return userName;
	}
	@Override
	public boolean isAccountNonExpired() {
		
		return this.nonExpired;
	}
	@Override
	public boolean isAccountNonLocked() {
		
		return this.nonExpired;
	}
	@Override
	public boolean isCredentialsNonExpired() {
	
		return this.pwNonExpired;
	}
	@Override
	public boolean isEnabled() {
		
		return this.enable;
	}
	
	public User(
			String userName,
			String password,
			String firstName,
			String lastName,
			String mobilePhone,
			String description) {
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobilePhone = mobilePhone;
		this.description = description;
	}
	public String getUserName() {
		return userName;
	}
	
}
