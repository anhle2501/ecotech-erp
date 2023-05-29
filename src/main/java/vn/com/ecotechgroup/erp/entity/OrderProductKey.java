package vn.com.ecotechgroup.erp.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class OrderProductKey implements Serializable {

	@Column(name = "order_id")
	private int orderId;
	
	@Column(name = "product_id")
	private int productId;

}
