package vn.com.ecotechgroup.erp.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "an_order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;

	@Column
	@CreationTimestamp
	@CreatedDate // phai dung jpa auditing
	private LocalDateTime createAt;

//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinTable(
//			name = "order_product",
//			joinColumns = @JoinColumn(name = "order_id"),
//			inverseJoinColumns = @JoinColumn(name = "product_id")
//			)
//	private List<Product> productsList;

	@ToString.Exclude
	@OneToMany(mappedBy = "order", cascade = { CascadeType.PERSIST,
			CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REMOVE})
	private List<OrderProduct> orderProduct = new ArrayList<>();

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH,
			CascadeType.DETACH, CascadeType.MERGE })
	@JoinColumn(name = "customer_id")
	private Customer customer = new Customer();

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH,
			CascadeType.DETACH, CascadeType.MERGE })
	@JoinColumn(name = "payment_type_id")
//	@NotNull(message="Không được để trống !")
	private PaymentType paymentType = new PaymentType();

	@Column(length = 1000)
	private String description;

	@Column
	private long totalPrice;

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
			List<Integer> priceList = products.stream().map(p -> p.getPrice() * p.getQuantity()).toList();
			long total = (long) priceList.stream().reduce(0, (subtotal, element) -> (subtotal + element) );
			this.setTotalPrice(total);	
		}
	}

	public void removeProduct(int productIndex) {
		if (this.getOrderProduct().size() != 0 && productIndex != 0) {
			List<OrderProduct> products = this.getOrderProduct();
			OrderProduct product = products.get(productIndex - 1);
			long total = product.getPrice() * product.getQuantity();
			this.setTotalPrice(this.getTotalPrice() - total);
			products.remove(productIndex - 1);
		}
		
//		orderProduct = orderProduct.stream()
//				.filter(op -> op.getProduct().getId() != productId)
//				.collect(Collectors.toList());
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
