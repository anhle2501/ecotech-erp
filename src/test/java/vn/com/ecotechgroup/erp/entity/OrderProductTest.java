package vn.com.ecotechgroup.erp.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lombok.Getter;

@Getter
public class OrderProductTest {

	private Order order;

	private Product product;

	private OrderProduct orderProduct;

	private List<OrderProduct> listOrderProduct;

	public OrderProductTest() {

		orderProduct = new OrderProduct();
		orderProduct.setOrder(order);
		orderProduct.setProduct(product);
		orderProduct.setQuantity(100);
		orderProduct.setPrice(10);
		orderProduct.setTotal(1000L);

		listOrderProduct = new ArrayList<OrderProduct>();
		for (long i = 0; i < 6; i++) {
			OrderProduct orderProduct = new OrderProduct();
			orderProduct.setId(i);
			orderProduct.setOrder(order);
			orderProduct.setProduct(product);
			orderProduct.setQuantity(100);
			orderProduct.setPrice(i);
			orderProduct.setTotal(1000L);
			listOrderProduct.add(orderProduct);
		}
	}

	@BeforeAll
	static void initAll() {

	}

	@BeforeEach
	void init() {
	}

//	@Test
//	void when_add_product_get_correct_total_5() {
//		assertThat(order.getOrderProduct().size()).isEqualByComparingTo(5);
//	}

//	@Test
//	void failingTest() {
//		fail("a failing test");
//	}
//
//	@Test
//	@Disabled("for demonstration purposes")
//	void skippedTest() {
//		// not executed
//	}
//
//	@Test
//	void abortedTest() {
//		assumeTrue("abc".contains("Z"));
//		fail("test should have been aborted");
//	}

	@AfterEach
	void tearDown() {
	}

	@AfterAll
	static void tearDownAll() {
	}

}
