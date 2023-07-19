package vn.com.ecotechgroup.erp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "authorities")
public class Authorities {
	
	@Id
	@Column(name = "user_id_au")
	private Long userId;
	
	@NotBlank(message = "Không được để trống !")
	@Size(max = 45)
	@Column
	private String authority;
	
	@ToString.Exclude
	@OneToOne
	@JoinColumn(name = "user_id_au")
	User userDetails;
}