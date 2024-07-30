package vn.com.ecotechgroup.erp.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import vn.com.ecotechgroup.erp.audit.AuditableData;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customer", schema = "ecotechgroup_erp")
public class Customer extends AuditableData implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;

	@Size(max = 45, message = "Độ dài quá 45 ký tự !")
	@Pattern(regexp = "(\\b(\\w)+\\b)", message = "Mã có định dạng liên tục !")
	@Column(length = 45, unique = true)
	private String code;

	@NotBlank(message = "Không được để trống !")
	@Size(min = 5, max = 100, message = "Độ dài từ 5-100 ký tự !")
	@Column(length = 100, unique = true)
	private String name;

	@Size(max = 45, message = "Độ dài quá 200 ký tự !")
	@Column(length = 200)
	private String address;

	@Size(max = 45, message = "Độ dài quá 10 ký tự !")
	@Pattern(regexp = "(\\d){10}", message = "Số điện thoại gồm 10 chữ số !")
	@Column(length = 10)
	private String phone;

	@Size(max = 45, message = "Độ dài quá 20 ký tự !")
	@Pattern(regexp = "((\\d){8,})?", message = "Mã số thuế lớn hơn 8 ký tự !")
	@Column(length = 20)
	private String taxCode;

	@Column(length = 1000)
	@Size(max = 1000, message = "Độ dài quá 1000 ký tự !")
	private String description;

	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "id_user_belong")
	private User idUserBelong;


	@ManyToMany(mappedBy = "customers", cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
	private Set<Region> regions = new HashSet<>();

	@Override
	public final boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Customer customer)) return false;

        return id == customer.id && Objects.equals(code, customer.code) && Objects.equals(name, customer.name) && Objects.equals(address, customer.address) && Objects.equals(phone, customer.phone) && Objects.equals(taxCode, customer.taxCode) && Objects.equals(description, customer.description);
	}

	@Override
	public int hashCode() {
		int result = Long.hashCode(id);
		result = 31 * result + Objects.hashCode(code);
		result = 31 * result + Objects.hashCode(name);
		result = 31 * result + Objects.hashCode(address);
		result = 31 * result + Objects.hashCode(phone);
		result = 31 * result + Objects.hashCode(taxCode);
		result = 31 * result + Objects.hashCode(description);
		return result;
	}
}
