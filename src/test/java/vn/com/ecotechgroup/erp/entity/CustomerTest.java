package vn.com.ecotechgroup.erp.entity;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.Getter;
import vn.com.ecotechgroup.erp.repository.UserRepository;

@Getter
public class CustomerTest {

	private Customer customer;

	private User user = new UserTest().getUser();

	private LocalDateTime now;

	public CustomerTest() {
		customer = Customer.builder().address("40/20 Lu Gia").code("KH0001")
				.description("Khách hàng thường").name("NhutAnh")
				.phone("0374749933").taxCode("123456789").build();
		customer.setCreatedBy(user);
		now = LocalDateTime.now();
		customer.setCreatedDate(now);
		customer.setLastModifiedBy(user);
		customer.setLastModifiedDate(now);
	}

	@BeforeAll
	static void init_customer() {

	}

	@BeforeEach
	void init() {
	}

	@Test
	void get_info_attribute_without_audit() {

		assertThat(customer.getAddress()).isEqualTo("40/20 Lu Gia");
		assertThat(customer.getCode()).isEqualTo("KH0001");
		assertThat(customer.getDescription()).isEqualTo("Khách hàng thường");
		assertThat(customer.getName()).isEqualTo("NhutAnh");
		assertThat(customer.getPhone()).isEqualTo("0374749933");
		assertThat(customer.getTaxCode()).isEqualTo("123456789");
	}

	@Test
	void get_info_attribute_audit() {
		// arrange
		// action
		// assert
		assertThat(customer.getCreatedBy()).isEqualTo(user);
		assertThat(customer.getCreatedDate()).isEqualTo(now);
		assertThat(customer.getLastModifiedBy()).isEqualTo(user);
		assertThat(customer.getLastModifiedDate()).isEqualTo(now);
	}

	@Test
	void get_customer_with_null_user() {
		User user = new User();
		user.setId(-9);
		customer.setIdUserBelong(user);
		assertThat(customer.getIdUserBelong()).isEqualTo(user);
		assertThat(customer.getIdUserBelong().getId()).isEqualTo(-9);
	}

	@AfterEach
	void tearDown() {
	}

	@AfterAll
	static void tearDownAll() {
	}

}
