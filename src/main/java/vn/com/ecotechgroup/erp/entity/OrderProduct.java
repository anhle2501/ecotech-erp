package vn.com.ecotechgroup.erp.entity;

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
	OrderProductKey id;
	
	// prevent toString recursive
	@ToString.Exclude
	@ManyToOne
	@MapsId("orderId")
	@JoinColumn(name = "order_id")
	Order order;
	

	@ManyToOne
	@MapsId("productId")
	@JoinColumn(name = "product_id")
	Product product;
	
	@Column
	int price;
}
