package vn.com.ecotechgroup.erp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "authorities")
public class Authorities {
	
	@Id
	@Size(max = 45)
	@Column(name = "user_id_au")
	private Integer userId;
	
	@NotBlank
	@Size(max = 45)
	@Column
	private String authority;
	
//	@ManyToOne
////	@JoinColumn(name = "user_id_au")
//	User userDetails;
}