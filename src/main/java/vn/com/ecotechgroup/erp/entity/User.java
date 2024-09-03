package vn.com.ecotechgroup.erp.entity;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

import lombok.*;
import org.hibernate.annotations.Formula;
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

import static java.util.Collections.sort;

@Entity
@Table(name = "\"user\"", schema = "ecotechgroup_erp")
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@AllArgsConstructor
@Builder
@Getter
@Setter
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
	@ToString.Exclude
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

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH,
			CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinTable(schema = "ecotechgroup_erp",name = "user_role", joinColumns = @JoinColumn(name = "user_id_au"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	@JsonManagedReference
	private List<Role> listRole = new ArrayList<>();

//	@ToString.Exclude
//	@OneToMany(mappedBy = "userOrdered", fetch = FetchType.EAGER, cascade = {
//			CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
//			CascadeType.REFRESH })
//	@JsonBackReference
//	private List<Order> listOrders;

	@ToString.Exclude
	@OneToMany(mappedBy = "idUserBelong", fetch = FetchType.LAZY)
	private List<Customer> listCustomers;

	@ToString.Exclude
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(schema = "ecotechgroup_erp",name = "region_user", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "region_id"))
	private List<Region> regions;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;
		return id == user.id && nonLock == user.nonLock && nonExpired == user.nonExpired && pwNonExpired == user.pwNonExpired && enable == user.enable && Objects.equals(userName, user.userName) && Objects.equals(password, user.password) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(mobilePhone, user.mobilePhone) && Objects.equals(description, user.description);
	}

	@Override
	public int hashCode() {
		int result = Long.hashCode(id);
		result = 31 * result + Objects.hashCode(userName);
		result = 31 * result + Objects.hashCode(password);
		result = 31 * result + Objects.hashCode(firstName);
		result = 31 * result + Objects.hashCode(lastName);
		result = 31 * result + Objects.hashCode(mobilePhone);
		result = 31 * result + Objects.hashCode(description);
		result = 31 * result + Boolean.hashCode(nonLock);
		result = 31 * result + Boolean.hashCode(nonExpired);
		result = 31 * result + Boolean.hashCode(pwNonExpired);
		result = 31 * result + Boolean.hashCode(enable);
		return result;
	}

	public boolean compareRegion(List<Region> regions) {
		Collections.sort(this.regions, new Comparator<Region>() {
			@Override
			public int compare(Region o1, Region o2) {
				if (o1.getId() <= o2.getId()) { return 1;}
				if (o1.getId() >= o2.getId()) { return -1;}
				return 0;
			}
		});
		Collections.sort(regions, new Comparator<Region>() {
			@Override
			public int compare(Region o1, Region o2) {
				if (o1.getId() <= o2.getId()) { return 1;}
				if (o1.getId() >= o2.getId()) { return -1;}
				return 0;
			}
		});
        return this.regions.equals(regions);
	}


}
