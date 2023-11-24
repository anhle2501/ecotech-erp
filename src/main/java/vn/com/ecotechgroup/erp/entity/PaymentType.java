package vn.com.ecotechgroup.erp.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
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
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment_type", schema = "ecotechgroup_erp")
//@EntityListeners(AuditingEntityListener.class)
public class PaymentType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;

	@NotBlank(message = "Không được để trống!")
	@Column(length = 45)
	private String name;

	@Column(length = 1000)
	@Size(max = 1000, message = "Độ dài quá 1000 ký tự !")
	private String description;

	@Column
	@Max(value = 255, message = "Tối đa nợ 255 ngày.")
	private int day;
	
	@OneToOne
	@JoinColumn(name = "create_by", updatable = false)
	@CreatedBy
	private User user;
	
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createAt;
	
	@OneToOne
	@JoinColumn(name = "last_modified_by")
	@LastModifiedBy
	private User userModified;
	
	@LastModifiedDate
	@Column
	private LocalDateTime last_modified_date;
}
