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
	int orderId;
	
	@Column(name = "product_id")
	int productId;

}
