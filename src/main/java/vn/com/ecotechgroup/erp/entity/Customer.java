package vn.com.ecotechgroup.erp.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer", schema = "ecotechgroup_erp")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;

	
	@Size(max = 45, message = "Độ dài quá 45 ký tự !")
	@Pattern(regexp = "(\\b(\\w)+\\b)", message = "Mã có định dạng liên tục !")
	@Column(length = 45)
	private String code;

	@NotBlank(message = "Không được để trống !")
	@Size(min = 5, max = 100, message = "Độ dài từ 5-100 ký tự !")
	@Column(length = 100)
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
	
	
	@OneToOne
	@JoinColumn(name = "create_by")
	@CreatedBy
	private User user;
	
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createAt;
	
	@Column(length = 1000)
	@Size(max = 1000, message = "Độ dài quá 1000 ký tự !")
	private String description;

}
