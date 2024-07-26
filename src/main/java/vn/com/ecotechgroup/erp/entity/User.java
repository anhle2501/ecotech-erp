package vn.com.ecotechgroup.erp.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "user", schema = "ecotechgroup_erp")
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@Data
public class User implements UserDetails, Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;

	@Column(length = 45, unique = true, nullable = false)
	@NotBlank(message = "Không để trống!")
	@Length(max = 45, message = "Ít hơn 45 ký tự!")
	private String userName;

	@JsonIgnore
	@Column(length = 45, nullable = false)
	@NotBlank(message = "Không để trống!")
	@Length(min = 8, max = 1000, message = "Nhiều hơn 8 ký tự!")
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

//	@JsonIgnore
//	@Column(insertable = false)
//	private String accessToken;
//	
//	@JsonIgnore
//	@Column(insertable = false)
//	private String refreshToken;

	@ToString.Exclude
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH,
			CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id_au"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	@JsonManagedReference
	private List<Role> listRole = new ArrayList<>();

	@ToString.Exclude
	@OneToMany(mappedBy = "userOrdered", fetch = FetchType.EAGER, cascade = {
			CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JsonBackReference
	private List<Order> listOrders;

	@ToString.Exclude
	@OneToMany(mappedBy = "idUserBelong", fetch = FetchType.LAZY)
	private List<Customer> listCustomers;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getGrantedAuthorities(getPermissions(listRole));
	}

	private List<String> getPermissions(List<Role> roles) {

		List<String> permission = new ArrayList<>();
		List<Permission> collection = new ArrayList<>();

		for (Role role : roles) {
			permission.add(role.getName());
			collection.addAll(role.getListPermission());
		}
		for (Permission item : collection) {
			permission.add(item.getName());
		}
		return permission;
	}
	
	public List<String> getPermissionsOnly(List<Role> roles) {

		List<String> permission = new ArrayList<>();
		List<Permission> collection = new ArrayList<>();

		for (Role role : roles) {
			collection.addAll(role.getListPermission());
		}
		for (Permission item : collection) {
			permission.add(item.getName());
		}
		return permission;
	}
	

	private List<GrantedAuthority> getGrantedAuthorities(
			List<String> permission) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String privilege : permission) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
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

	public User(String userName, String password, String firstName,
			String lastName, String mobilePhone, String description) {
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
