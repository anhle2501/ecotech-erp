package vn.com.ecotechgroup.erp.entity;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;

	@Column(length = 45)
	private String code;

	@NotNull(message = "Không được để trống !")
	@Column(length = 100)
	private String name;

	@Column(length = 1000)
	private String description;

//	@ManyToMany(mappedBy = "productsList", fetch = FetchType.LAZY)
//	private List<Order> orders;

	@ToString.Exclude
	@OneToMany(mappedBy = "product")
	private List<OrderProduct> orderProduct;
}
