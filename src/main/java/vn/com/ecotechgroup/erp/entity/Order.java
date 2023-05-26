package vn.com.ecotechgroup.erp.entity;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="`order`")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;

	@Column
	@CreatedDate
	private LocalDateTime createAt;

//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinTable(
//			name = "order_product",
//			joinColumns = @JoinColumn(name = "order_id"),
//			inverseJoinColumns = @JoinColumn(name = "product_id")
//			)
//	private List<Product> productsList;
	
	@OneToMany(mappedBy = "order")
	private List<OrderProduct> orderProduct;

	@OneToOne
	@JoinColumn(name="customer_id")
	private Customer customerOrder;
	
	@OneToOne
	@JoinColumn(name="payment_type_id")
	@NotNull(message="Không được để trống !")
	private PaymentType paymentType;
	
	@Column(length=1000)
	private String description;
	
	@Column
	private BigInteger totalPrice;
}
