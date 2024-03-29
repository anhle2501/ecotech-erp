package vn.com.ecotechgroup.erp.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import vn.com.ecotechgroup.erp.audit.AuditableData;

//@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "product", schema = "ecotechgroup_erp")
public class Product extends AuditableData implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;

	@Column(length = 45, unique = true)
	@Size(max = 45, message = "Độ dài quá 45 ký tự !")
	@Pattern(regexp = "(\\b(\\w)+\\b)", message = "Mã có định dạng liên tục !")
	private String code;

	@Size(min = 5, max = 100, message = "Độ dài từ 5-100 ký tự !")
	@Column(length = 100, unique = true)
	private String name;

	@Column(length = 1000)
	@Size(max = 1000, message = "Độ dài quá 1000 ký tự !")
	private String description;

	@Size(max = 10, message = "Độ dài quá 10 ký tự !")
	@Pattern(regexp = "(\\b(\\w)+\\b)", message = "Đơn vị tính có định dạng liên tục !")
	@Column(length = 10, unique = true)
	private String unit;

	
}
