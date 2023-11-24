package vn.com.ecotechgroup.erp.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lombok.Getter;

@Getter
public class ProductTest {

	private Product product; 

	private User user = new UserTest().getUser();

	public ProductTest() {
		product = Product.builder()
				.code("BVTV").name("Product 1")
				.unit("Bao").user(user).description("product1")
				.build();
	}
	
	
	@BeforeAll
	static void init_paymentType() {
		
	}

	@BeforeEach
	void init() {
	}

	@Test
	void get_info_attribute() {
		assertThat(product.getCode()).isEqualTo("BVTV");
		assertThat(product.getName()).isEqualTo("Product 1");
		assertThat(product.getUnit()).isEqualTo("Bao");
		assertThat(product.getDescription()).isEqualTo("product1");
		assertThat(product.getUser()).isEqualTo(user);
	}

	@AfterEach
	void tearDown() {
	}

	@AfterAll
	static void tearDownAll() {
	}

}
