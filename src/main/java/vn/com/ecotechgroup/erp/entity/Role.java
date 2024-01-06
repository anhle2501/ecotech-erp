package vn.com.ecotechgroup.erp.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
	private String name;

	@Column(length = 1000)
	@Size(max = 1000, message = "Độ dài quá 1000 ký tự !")
	private String description;

	@ToString.Exclude
	@JsonBackReference
	@ManyToMany(mappedBy = "listRole", cascade = { CascadeType.REFRESH,
			CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST })
	private List<User> users = new ArrayList<>();

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH,
			CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST })
	@JsonBackReference
	@JoinTable(name = "role_permission", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id"))
	private List<Permission> listPermission = new ArrayList<>();

	public Role(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public Role() {

	}

}
