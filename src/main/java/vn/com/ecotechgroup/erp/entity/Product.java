package vn.com.ecotechgroup.erp.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "orders")
@Entity
@Table
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	
	@Column(length=45)
	private String code;
	
	@NotNull(message="Không được để trống !")
	@Column(length=100)
	private String name;
	
	@Column(length=1000)
	private String description;
	
	@ManyToMany(mappedBy = "productsList", fetch = FetchType.LAZY)
	private List<Order> orders;
	
}
