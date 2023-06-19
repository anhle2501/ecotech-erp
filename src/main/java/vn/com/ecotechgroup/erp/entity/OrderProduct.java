package vn.com.ecotechgroup.erp.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class OrderProduct {

	@EmbeddedId
	private OrderProductKey id = new OrderProductKey();
	
	// prevent toString recursive
//	@ToString.Exclude
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE})
	@MapsId("orderId")
	@JoinColumn(name = "order_id")
	private Order order = new Order();
	
//	@ToString.Exclude
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE})
	@MapsId("productId")
	@JoinColumn(name = "product_id")
	private Product product = new Product();
	
	@Column
	private int price;
	
	@Column
	private int quantity;
	
	@Column(name="total",  insertable = false, updatable = false)
	private long total;
}
