//package vn.com.ecotechgroup.erp.controller;
//
//import static org.mockito.ArgumentMatchers.isA;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultMatcher;
//
//import vn.com.ecotechgroup.erp.controller.user.CustomerControllerUser;
//import vn.com.ecotechgroup.erp.repository.CustomerRepository;
//
////@SpringBootTest
////@AutoConfigureMockMvc(addFilters = false)
////@ExtendWith(MockitoExtension.class)
//
////@TestPropertySource(properties = "spring.security.enabled=false")
//@SpringBootTest
//@AutoConfigureMockMvc(addFilters = false)
//@ActiveProfiles(profiles = { "dev", "nosecurity" })
//public class CustomerControllerTest {
//
//	@Autowired
//	private MockMvc mockMvc;
//	
//	@Mock
//	private CustomerRepository customerRep;
//	
//	@Mock
//	private CustomerControllerUser customerControllerUser;
//	
//	
//	CustomerControllerTest() {
//	
//	}
//	
//	@Test
//	public void test_dependency() throws Exception {
//		
//		when(customerControllerUser.showTestMock()).thenReturn("hellowold");
//		
//		mockMvc.perform(get("/test-mock"))
//			.andExpect(status().isOk())
//			.andExpect(content());
//		
//	}
//
//    
////	@Test
////	@WithMockUser(username = "user", password = "password", roles = "USER")
////	public void testCustomerPage() throws Exception {	
////		
////		
////		when(customerControllerUser.showCustomerList(
////				any(),
////				Integer.valueOf(0),
////				Integer.valueOf(50),
////				any(String.class),
////				(vn.com.ecotechgroup.erp.entity.User) nullable(User.class)))
////			.thenReturn("page/customer");
////		
////		doNothing().when(model.addAttribute(any(String.class), any()));
//		
////		verify(model.addAttribute(any(String.class), any()));
//		
////		model.addAttribute("customer", customerList);
////		model.addAttribute("isList", true);
////		model.addAttribute("pageNumber", pageNumber);
////		model.addAttribute("pageSize", pageSize);
////		model.addAttribute("search", searchTerm);
//		
////		when(showCustomerList(any(ConcurrentModel.class),
////				any(Integer.class),
////				any(Integer.class),
////				any(String.class),
////				any(User.class)))
////			.then
////		
////		when(customerRep.customerSearchListUser(
////				any(PageRequest.class), 
////				any(Long.class) , 
////				any(String.class)))
////			.thenReturn(customerRepReal.customerSearchListUser(
////					PageRequest.of(0, 50,Sort.by("createdDate").descending()),
////					47L, 
////					null));
////		
////		mockMvc.perform(get("/customer/0/50"))
////				.andExpect(status().isOk())
////				.andExpect(view().name("page/customer")).andExpect(content()
////						.string(containsString("Danh sách khách hàng")));
////	}
//
//	
//
//}
