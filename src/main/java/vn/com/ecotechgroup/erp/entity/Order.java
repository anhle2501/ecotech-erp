package vn.com.ecotechgroup.erp.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "productsList")
@Entity
@Table(name="`order`")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;

	@Column
	private LocalDateTime createAt;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "order_product",
			joinColumns = @JoinColumn(name = "order_id"),
			inverseJoinColumns = @JoinColumn(name = "product_id")
			)
	private List<Product> productsList;

	@OneToOne
	@JoinColumn(name="customer_id")
	private Customer customerOrder;
	
	@OneToOne
	@JoinColumn(name="payment_type_id")
	@NotNull(message="Không được để trống !")
	private PaymentType paymentType;
	
	@Column(length=1000)
	private String description;
	
}
