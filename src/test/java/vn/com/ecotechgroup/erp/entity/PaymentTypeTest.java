package vn.com.ecotechgroup.erp.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lombok.Getter;

@Getter
public class PaymentTypeTest {

	private PaymentType paymentType;

	private User user = new UserTest().getUser();

	public PaymentTypeTest() {
		paymentType = PaymentType.builder().day(30).description("30 ngày")
				.user(user).name("Thanh toán").build();
	}

	@BeforeAll
	static void init_paymentType() {

	}

	@BeforeEach
	void init() {
	}

	@Test
	void get_info_attribute() {
		assertThat(paymentType.getDescription()).isEqualTo("30 ngày");
		assertThat(paymentType.getName()).isEqualTo("Thanh toán");
		assertThat(paymentType.getDay()).isEqualTo(30);
		assertThat(paymentType.getUser()).isEqualTo(user);
	}

	@AfterEach
	void tearDown() {
	}

	@AfterAll
	static void tearDownAll() {
	}

}
