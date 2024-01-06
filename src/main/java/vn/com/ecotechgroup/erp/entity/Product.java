package vn.com.ecotechgroup.erp.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
//@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product", schema = "ecotechgroup_erp")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;

	@Column(length = 45, unique = true)
	@Size(max = 45, message = "Độ dài quá 45 ký tự !")
	@Pattern(regexp = "(\\b(\\w)+\\b)", message = "Mã có định dạng liên tục !")
	private String code;

	@Size(min = 5, max = 100, message = "Độ dài từ 5-100 ký tự !")
	@Column(length = 100, unique = true)
	private String name;

	@Column(length = 1000)
	@Size(max = 1000, message = "Độ dài quá 1000 ký tự !")
	private String description;

//	@ManyToMany(mappedBy = "productsList", fetch = FetchType.LAZY)
//	private List<Order> orders;

	@Size(max = 10, message = "Độ dài quá 10 ký tự !")
	@Pattern(regexp = "(\\b(\\w)+\\b)", message = "Đơn vị tính có định dạng liên tục !")
	@Column(length = 10, unique = true)
	private String unit;

	@OneToOne
	@JoinColumn(name = "create_by", updatable = false)
	@CreatedBy
	@JsonManagedReference
	private User user;

	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createAt;

	@OneToOne
	@JoinColumn(name = "last_modified_by")
	@LastModifiedBy
	@JsonManagedReference
	private User userModified;

	@LastModifiedDate
	@Column
	private LocalDateTime last_modified_date;

//	@ToString.Exclude
//	@OneToMany(mappedBy = "product")
//	private List<OrderProduct> orderProduct;

//	public void addOrder(Order order, int price, int quantity) {
//		OrderProduct newOrderProduct = new OrderProduct();
//		newOrderProduct.setOrder(order);
//		newOrderProduct.setProduct(this);
//		newOrderProduct.setPrice(price);
//		newOrderProduct.setQuantity(quantity);
//		orderProduct.add(newOrderProduct);
//	}
//	
//	public void removeOrder(long orderId) {
//		orderProduct = 
//			orderProduct.stream()
//				.filter(op -> op.getOrder().getId() != orderId)
//				.collect(Collectors.toList());
//	}
}
