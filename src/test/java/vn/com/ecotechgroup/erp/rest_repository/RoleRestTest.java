//package vn.com.ecotechgroup.erp.rest_repository;
//
//import static org.hamcrest.Matchers.is;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import vn.com.ecotechgroup.erp.entity.Customer;
//import vn.com.ecotechgroup.erp.entity.User;
//import vn.com.ecotechgroup.erp.entity.UserTest;
//
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//
//@AutoConfigureMockMvc
//@TestMethodOrder(MethodOrderer.MethodName.class)
//@ActiveProfiles(profiles = { "dev", "nosecurity" })
//public class RoleRestTest {
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@LocalServerPort
//	private int port;
//
//	@Value("${host.name}")
//	private String hostName;
//
//	private String featureName = "/api/roles";
//
//	public String baseUrl;
//
//	public static ResponseEntity<Customer> response;
//
//	private static User user;
//
//	@BeforeAll
//	public static void setUp() {
//		UserTest test = new UserTest();
//		user = test.getUser();
//		user.setDescription("test user");
//	}
//
//	@BeforeEach
//	public void setup_baseUrl() {
//		baseUrl = this.hostName + ":" + this.port + this.featureName;
//	}
//
//	@Test
//	public void should_return_true_when_test_on_localhost() {
//		assertEquals(hostName + ":" + this.port + this.featureName, baseUrl);
//	}
//
//	@Test
//	public void a_should_return_customer_when_post_for_entity_using_pojo()
//			throws JsonProcessingException, Exception {
//
//		MvcResult result = mockMvc
//				.perform(post(baseUrl).contentType(MediaType.APPLICATION_JSON)
//						.content(new ObjectMapper().writeValueAsString(user)))
//				.andExpect(status().isCreated()).andReturn();
//
//		String customerCreate = result.getResponse().getContentAsString();
//
//		JsonNode root = new ObjectMapper().readTree(customerCreate);
//		String selfRef = root.path("_links").path("self").path("href").asText();
//		String id = selfRef.replace(baseUrl + "/", "");
//		user.setId(Long.valueOf(id));
//	}
//
//	@Test
//
//	public void b_should_return_customer_when_get_for_entity()
//			throws Exception {
//		System.out.println(baseUrl + "/" + user.getId());
//
//		mockMvc.perform(get(baseUrl + "/" + user.getId()))
//				.andExpect(status().isOk())
//				.andExpect(content().contentType("application/hal+json"))
//				.andExpect(
//						jsonPath("$.description", is(user.getDescription())));
//	}
//
//	@Test
//	public void c_should_delete_customer_when_delete() throws Exception {
//		mockMvc.perform(delete(baseUrl + "/" + user.getId()))
//				.andExpect(status().isNoContent());
//		mockMvc.perform(get(baseUrl + "/" + user.getId()))
//				.andExpect(status().isNotFound());
//	}
//
//	@AfterAll
//	public static void tearDown() {
//
//	}
//}
