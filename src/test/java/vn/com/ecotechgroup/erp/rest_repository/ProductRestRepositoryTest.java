package vn.com.ecotechgroup.erp.rest_repository;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import vn.com.ecotechgroup.erp.entity.Customer;
import vn.com.ecotechgroup.erp.entity.Product;
import vn.com.ecotechgroup.erp.entity.ProductTest;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
@ActiveProfiles(profiles = { "dev", "nosecurity" })
public class ProductRestRepositoryTest {

	@Autowired
	private MockMvc mockMvc;

	@LocalServerPort
	private int port;

	@Value("${host.name}")
	private String hostName;

	private String featureName = "/api/products";

	public String baseUrl;

	public static ResponseEntity<Customer> response;

	private static Product productTest;

	@BeforeAll
	public static void setUp() {
		ProductTest test = new ProductTest();
		productTest = test.getProduct();
	}

	@BeforeEach
	public void setup_baseUrl() {
		baseUrl = this.hostName + ":" + this.port + this.featureName;
	}

	@Test
	public void should_return_true_when_test_on_localhost() {
		assertEquals(hostName + ":" + this.port + this.featureName, baseUrl);
	}

	@Test
	@Order(1)
	public void should_return_customer_when_post_for_entity_using_pojo()
			throws JsonProcessingException, Exception {

		MvcResult result = mockMvc
				.perform(post(baseUrl).contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper()
								.writeValueAsString(productTest)))
				.andExpect(status().isCreated()).andReturn();

		String customerCreate = result.getResponse().getContentAsString();

		JsonNode root = new ObjectMapper().readTree(customerCreate);
		String selfRef = root.path("_links").path("self").path("href").asText();
		String id = selfRef.replace(baseUrl + "/", "");
		productTest.setId(Long.valueOf(id));
	}

	@Test
	@Order(2)
	public void should_return_customer_when_get_for_entity() throws Exception {
		System.out.println(baseUrl + "/" + productTest.getId());

		mockMvc.perform(get(baseUrl + "/" + productTest.getId()))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/hal+json"))
				.andExpect(jsonPath("$.name", is(productTest.getName())));
	}

	@Test
	@Order(3)
	public void should_delete_customer_when_delete() throws Exception {
		mockMvc.perform(delete(baseUrl + "/" + productTest.getId()))
				.andExpect(status().isNoContent());
		mockMvc.perform(get(baseUrl + "/" + productTest.getId()))
				.andExpect(status().isNotFound());
	}

	@AfterAll
	public static void tearDown() {

	}
}
