package vn.com.ecotechgroup.erp.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testCustomerPage() throws Exception {
//		mockMvc.perform(get("/customer")).andExpect(status().isOk())
//				.andExpect(view().name("page/customer"))
//				.andExpect(content().string(containsString("Danh sách khách hàng")));
	}

}
