package vn.com.ecotechgroup.erp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

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
	
//	@ToString.Exclude
//	@OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE})
//	@JoinColumn(name = "user_id_au")
//	User userDetails;
}