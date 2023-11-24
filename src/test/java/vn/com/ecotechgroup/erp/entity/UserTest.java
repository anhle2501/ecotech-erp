package vn.com.ecotechgroup.erp.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lombok.Data;

@Data
public class UserTest {

	private User user; 
	
	public UserTest() {
		user = new User();
		user.setUserName("nhutanh99");
		user.setPassword("123456789Abc");
		user.setDescription("mô tả");
		user.setMobilePhone("12345678");
		user.setLastName("anh");
		user.setFirstName("le");
		user.setEnable(true);
		user.setNonExpired(true);
		user.setNonLock(true);
		user.setPwNonExpired(true);
		
	}
	

	@BeforeAll
	static void init_user() {
		
	}

	@BeforeEach
	void init() {
	}

	@Test
	void get_info_attribute() {
		assertThat(user.getUsername()).isEqualTo("nhutanh");
		assertThat(user.getDescription()).isEqualTo("mô tả");
		assertThat(user.getMobilePhone()).isEqualTo("12345678");
		assertThat(user.getLastName()).isEqualTo("anh");
		assertThat(user.getFirstName()).isEqualTo("le");
		assertThat(user.isEnable()).isEqualTo(true);
		assertThat(user.isNonExpired()).isEqualTo(true);
		assertThat(user.isNonLock()).isEqualTo(true);
		assertThat(user.isPwNonExpired()).isEqualTo(true);
	}

	@AfterEach
	void tearDown() {
	}

	@AfterAll
	static void tearDownAll() {
	}

}
