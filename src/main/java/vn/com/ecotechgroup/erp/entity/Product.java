package vn.com.ecotechgroup.erp.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product", schema = "ecotechgroup_erp")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;

	@Column(length = 45)
	@Size(max = 45, message = "Độ dài quá 45 ký tự !")
	@Pattern(regexp = "(\\b(\\w)+\\b)", message = "Mã có định dạng liên tục !")
	private String code;

	@Size(min = 5, max = 100, message = "Độ dài từ 5-100 ký tự !")
	@Column(length = 100)
	private String name;

	@Column(length = 1000)
	@Size(max = 1000, message = "Độ dài quá 1000 ký tự !")
	private String description;

//	@ManyToMany(mappedBy = "productsList", fetch = FetchType.LAZY)
//	private List<Order> orders;
	
	@Size(max = 10 , message = "Độ dài quá 10 ký tự !")
	@Pattern(regexp = "(\\b(\\w)+\\b)", message = "Mã có định dạng liên tục !")
	@Column(length = 10)
	private String unit;
	
	@OneToOne
	@JoinColumn(name = "create_by")
	@CreatedBy
	private User user;
	
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createAt;
	
	@OneToOne
	@JoinColumn(name = "last_modified_by")
	@LastModifiedBy
	private User userModified;
	
	@LastModifiedDate
	@Column(updatable = false)
	private LocalDateTime last_modified_date;
	
	@ToString.Exclude
	@OneToMany(mappedBy = "product",
			cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE}
			)
	private List<OrderProduct> orderProduct;
	
	
	public void addOrder(Order order, int price, int quantity) {
		OrderProduct newOrderProduct = new OrderProduct();
		newOrderProduct.setOrder(order);
		newOrderProduct.setProduct(this);
		newOrderProduct.setPrice(price);
		newOrderProduct.setQuantity(quantity);
		orderProduct.add(newOrderProduct);
	}
	
	public void removeOrder(long orderId) {
		orderProduct = 
			orderProduct.stream()
				.filter(op -> op.getOrder().getId() != orderId)
				.collect(Collectors.toList());
	}
}
