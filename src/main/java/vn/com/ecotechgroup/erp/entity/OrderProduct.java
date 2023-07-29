package vn.com.ecotechgroup.erp.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_product", schema = "ecotechgroup_erp")
public class OrderProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;
	
	// prevent toString recursive
	@ToString.Exclude
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE})
	@JoinColumn(name = "order_id")
	private Order order = new Order();
	
//	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product = new Product();
	
	@Column
	private int price;
	
	@Column
	private int quantity;
	
//	@Column(name="total",  insertable = false, updatable = false)
	@Column(name="total",  insertable = false, updatable = false)
	private Long total;
	
	public OrderProduct(Order order, Product product, int price, int quantity) {
		this.order = order;
		this.product = product;
		this.price = price;
		this.quantity = quantity;
	}
	
}
