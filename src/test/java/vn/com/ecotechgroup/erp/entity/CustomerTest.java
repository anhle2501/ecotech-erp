package vn.com.ecotechgroup.erp.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lombok.Getter;

@Getter
public class CustomerTest {

	private Customer customer; 

	public CustomerTest() {
		customer = Customer.builder()
				.address("40/20 Lu Gia")
				.code("KH0001")
				.description("Khách hàng thường")
				.name("NhutAnh")
				.phone("0374749933")
				.taxCode("123456789")
				.build();
	}
	
	@BeforeAll
	static void init_customer() {
		
	}

	@BeforeEach
	void init() {
	}

	@Test
	void get_info_attribute() {
		
		assertThat(customer.getAddress()).isEqualTo("40/20 Lu Gia");
		assertThat(customer.getCode()).isEqualTo("KH0001");
		assertThat(customer.getDescription()).isEqualTo("Khách hàng thường");
		assertThat(customer.getName()).isEqualTo("NhutAnh");
		assertThat(customer.getPhone()).isEqualTo("0374749933");
		assertThat(customer.getTaxCode()).isEqualTo("123456789");
	}

	@AfterEach
	void tearDown() {
	}

	@AfterAll
	static void tearDownAll() {
	}

}
