package vn.com.ecotechgroup.erp.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "permission", schema = "ecotechgroup_erp")
@Data
public class Permission implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column(length = 45)
	@NotBlank(message = "")
	private String name;

	@Column(length = 1000)
	@Size(max = 1000, message = "Độ dài quá 1000 ký tự !")
	private String description;

	@ToString.Exclude
	@ManyToMany(mappedBy = "listPermission")
	private List<Role> roles = new ArrayList<>();

	public Permission(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public Permission() {
	}
}
