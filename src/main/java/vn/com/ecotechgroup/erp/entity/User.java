package vn.com.ecotechgroup.erp.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
	
	@Column(insertable = false)
	private boolean nonLock;
	@Column(insertable = false)
	private boolean nonExpired;
	@Column(insertable = false)
	private boolean pwNonExpired;
	@Column(insertable = false)
	private boolean enable;
//	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id_au")
	private List<Authorities> listAuth;
	
	
//	 @OneToMany(mappedBy = "userOrdered", fetch = FetchType.LAZY)
//	 private List<Order> listOrders;
	
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
		// TODO Auto-generated method stub
		return userName + " " + "(" + fullName + ")";
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
	
	
}
