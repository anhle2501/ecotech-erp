package vn.com.ecotechgroup.erp.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "an_order", schema = "ecotechgroup_erp")
@EntityListeners(AuditingEntityListener.class)
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;

	@Column(updatable = false)
	@CreatedDate // phai dung jpa auditing
	private LocalDateTime createAt;

//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinTable(
//			name = "order_product",
//			joinColumns = @JoinColumn(name = "order_id"),
//			inverseJoinColumns = @JoinColumn(name = "product_id")
//			)
//	private List<Product> productsList;

	@OneToMany(mappedBy = "order", cascade = { CascadeType.ALL })
	@JsonManagedReference
	private List<OrderProduct> orderProduct = new ArrayList<>();

	@OneToOne
	@JoinColumn(name = "customer_id")
	private Customer customer = new Customer();

	@OneToOne
	@JoinColumn(name = "payment_type_id")
//	@NotNull(message="Không được để trống !")
	private PaymentType paymentType = new PaymentType();

	@Column(length = 1000)
	private String description;

	@OneToOne
	@JoinColumn(name = "last_modified_by")
	@LastModifiedBy
	@JsonManagedReference
	private User userModified;

	@LastModifiedDate
	@Column
	private LocalDateTime last_modified_date;

	@Column
	private long totalPrice;

	@CreatedBy
	@OneToOne
	@JoinColumn(name = "user_id")
	@JsonManagedReference
	private User userOrdered;

	@Column(name = "is_confirm")
	private boolean isConfirm;

	public boolean getIsConfirm() {
		return this.isConfirm;
	}

	public void setIsConfirm(boolean b) {
		this.isConfirm = b;
	}

	@Column
	private LocalDateTime confirmAt;

	@OneToOne
	@JoinColumn(name = "confirm_by")
	private User confirmByUser;

	public void addProduct(Product product, int price, int quantity) {
		if (this.getOrderProduct().size() == 0) {
			OrderProduct newOrderProduct = new OrderProduct(this, product,
					price, quantity);
			orderProduct.add(newOrderProduct);
			this.setTotalPrice(price * quantity);
		} else {
			OrderProduct newOrderProduct = new OrderProduct(this, product,
					price, quantity);
			orderProduct.add(newOrderProduct);
			List<OrderProduct> products = this.getOrderProduct();
			List<Long> priceList = products.stream()
					.map(p -> p.getPrice() * p.getQuantity()).toList();
			long total = (long) priceList.stream().reduce(0L,
					(subtotal, element) -> (subtotal + element));
			this.setTotalPrice(total);
		}
	}

	public void removeProduct(Long productIndex) {
		int tmp = (int) (productIndex - 1);
		if (this.getOrderProduct().size() != 0 && productIndex != 0) {
			List<OrderProduct> products = this.getOrderProduct();
			OrderProduct product = products.get(tmp);

			long total = product.getPrice() * product.getQuantity();
			this.setTotalPrice(this.getTotalPrice() - total);
			products.remove(tmp);
		}
	}

	public void removeProduct(long productId) {
		List<OrderProduct> orderProducts = this.getOrderProduct();
		OrderProduct deleteProduct = orderProducts.stream()
				.filter((op) -> op.getId() == productId).toList().get(0);
		orderProducts = orderProducts.stream()
				.filter((op) -> op.getId() != productId).toList();
		this.setOrderProduct(orderProducts);
		long total = deleteProduct.getPrice() * deleteProduct.getQuantity();
		this.setTotalPrice(this.getTotalPrice() - total);
	}

	public Order(List<OrderProduct> orderProduct, Customer customer,
			PaymentType paymentType, String description, long totalPrice) {
		this.orderProduct = orderProduct;
		this.customer = customer;
		this.paymentType = paymentType;
		this.description = description;
		this.totalPrice = totalPrice;
	}
}
