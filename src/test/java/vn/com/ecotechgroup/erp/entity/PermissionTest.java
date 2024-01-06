package vn.com.ecotechgroup.erp.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lombok.Data;

@Data
public class PermissionTest {

	private Permission permission;

	public PermissionTest() {
		permission = new Permission();
		permission.setDescription("test permission");
		permission.setName("testpermission");
	}

	@BeforeAll
	static void init_user() {

	}

	@BeforeEach
	void init() {
	}

	@Test
	void get_info_attribute() {
		assertThat(permission.getName()).isEqualTo("testpermission");
		assertThat(permission.getDescription()).isEqualTo("test permission");
	}

	@AfterEach
	void tearDown() {
	}

	@AfterAll
	static void tearDownAll() {
	}

}
