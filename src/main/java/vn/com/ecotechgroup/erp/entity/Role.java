package vn.com.ecotechgroup.erp.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;
import vn.com.ecotechgroup.erp.annotation.UniqueField;

import vn.com.ecotechgroup.erp.repository.RoleRepository;

@Entity
@Table(name = "role", schema = "ecotechgroup_erp")
@Data
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 45, unique = true, nullable = false)
	@NotBlank(message = "Không được để trống !")
	@UniqueField(repository = RoleRepository.class, fieldName = "name")
	private String name;

	@Column(length = 1000)
	@Size(max = 1000, message = "Độ dài quá 1000 ký tự !")
	private String description;

	@ToString.Exclude
	@JsonBackReference
	@ManyToMany(mappedBy = "listRole")
//	@JoinTable(name = "user_role", schema = "ecotechgroup_erp",
//			joinColumns = @JoinColumn(name = "role_id"),
//			inverseJoinColumns = @JoinColumn(name = "user_id_au"))
	private List<User> listUsers = new ArrayList<>();

	@JsonBackReference
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
	@JoinTable(name = "role_permission", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id"))
	private Set<Permission> listPermission = new HashSet<>();

	public Role(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public Role() {

	}

}
