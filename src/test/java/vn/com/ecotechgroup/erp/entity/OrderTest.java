package vn.com.ecotechgroup.erp.entity;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import lombok.Data;

@Data
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class OrderTest {

	private Order order;
	private Customer customer = new CustomerTest().getCustomer();
	private PaymentType paymentType = new PaymentTypeTest().getPaymentType();
	private Product product = new ProductTest().getProduct();
	private User user = new UserTest().getUser();
	private OrderProduct orderProduct = new OrderProductTest()
			.getOrderProduct();
	private List<OrderProduct> listOrderProduct = new ArrayList<OrderProduct>();

	public OrderTest() {
		order = Order.builder().customer(customer).paymentType(paymentType)
				.id(1l).isConfirm(false).orderProduct(listOrderProduct)
				.description("This is order description")
				.build();
	}

	@BeforeAll
	static void initAll() {

	}

	@BeforeEach
	void init() {
	}

	@Test
	void when_add_product_get_correct_size_of_0() {
		assertThat(order.getOrderProduct().size()).isEqualByComparingTo(0);
	}

	@Test
	void when_add_product_get_correct_total_6() {
		for (long i = 0; i < 6; i++) {
			order.addProduct(product, 100, 10);
		}
		assertThat(order.getOrderProduct().size()).isEqualByComparingTo(6);
	}

	@AfterEach
	void tearDown() {
	}

	@AfterAll
	static void tearDownAll() {
	}

}
