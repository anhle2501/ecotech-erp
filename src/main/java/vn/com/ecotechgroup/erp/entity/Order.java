package vn.com.ecotechgroup.erp.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "an_order", schema = "ecotechgroup_erp")
@EntityListeners(AuditingEntityListener.class)
@ToString
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;

	@Column(updatable = false)
	@CreatedDate // phai dung jpa auditing
	private LocalDateTime createAt;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, targetEntity = OrderProduct.class, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<OrderProduct> orderProduct = new ArrayList<>();

	@OneToOne
	@JoinColumn(name = "customer_id")
	private Customer customer = new Customer();

	@OneToOne
	@JoinColumn(name = "payment_type_id")
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

	@Column(columnDefinition = "bigint default 0")
	private long totalPrice;

	@CreatedBy
	@ManyToOne
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

//	@Transient
//	private Long ordinalNumber;

	public void addProduct(Product product, int price, int quantity) {
		if (this.getOrderProduct().size() == 0) {
			OrderProduct newOrderProduct = new OrderProduct(this, product,
					price, quantity);
			orderProduct.add(newOrderProduct);
			this.setTotalPrice( (long) price * quantity);
		} else {
			OrderProduct newOrderProduct = new OrderProduct(this, product,
					price, quantity);
			orderProduct.add(newOrderProduct);
			List<OrderProduct> products = this.getOrderProduct();
			List<Long> priceList = products.stream()
					.map(p -> p.getPrice() * p.getQuantity()).toList();
			long total = (long) priceList.stream().reduce(0L,
                    Long::sum);
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
		Optional.of(totalPrice).ifPresentOrElse( (value -> this.totalPrice = value), () -> this.totalPrice = 0);
	}

	@Override
	public final boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Order order)) return false;

        return id == order.id && totalPrice == order.totalPrice && isConfirm == order.isConfirm && Objects.equals(createAt, order.createAt) && Objects.equals(customer, order.customer) && Objects.equals(paymentType, order.paymentType) && Objects.equals(description, order.description) && Objects.equals(userModified, order.userModified) && Objects.equals(last_modified_date, order.last_modified_date) && Objects.equals(userOrdered, order.userOrdered) && Objects.equals(confirmAt, order.confirmAt) && Objects.equals(confirmByUser, order.confirmByUser);
	}

	@Override
	public int hashCode() {
		int result = Long.hashCode(id);
		result = 31 * result + Objects.hashCode(createAt);
		result = 31 * result + Objects.hashCode(customer);
		result = 31 * result + Objects.hashCode(paymentType);
		result = 31 * result + Objects.hashCode(description);
		result = 31 * result + Objects.hashCode(userModified);
		result = 31 * result + Objects.hashCode(last_modified_date);
		result = 31 * result + Long.hashCode(totalPrice);
		result = 31 * result + Objects.hashCode(userOrdered);
		result = 31 * result + Boolean.hashCode(isConfirm);
		result = 31 * result + Objects.hashCode(confirmAt);
		result = 31 * result + Objects.hashCode(confirmByUser);
		return result;
	}
}
