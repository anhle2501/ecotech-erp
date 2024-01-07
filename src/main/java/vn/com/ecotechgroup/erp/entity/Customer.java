package vn.com.ecotechgroup.erp.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import vn.com.ecotechgroup.erp.audit.AuditableData;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
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

}
