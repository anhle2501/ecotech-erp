package vn.com.ecotechgroup.erp.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "permission", schema = "ecotechgroup_erp")
@Getter
@Setter
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
	@ManyToMany
	@JoinTable(name = "role_permission", joinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private List<Role> listRole = new ArrayList<>();

	public Permission(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public Permission() {
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Permission that = (Permission) o;
		return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description);
	}

	@Override
	public int hashCode() {
		int result = Objects.hashCode(id);
		result = 31 * result + Objects.hashCode(name);
		result = 31 * result + Objects.hashCode(description);
		return result;
	}
}
