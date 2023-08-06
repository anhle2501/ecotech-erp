//package vn.com.ecotechgroup.erp.repository;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.junit.jupiter.api.TestInstance.Lifecycle;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import vn.com.ecotechgroup.erp.entity.Customer;
//import vn.com.ecotechgroup.erp.repository.CustomerRepository;
//
//@SpringBootTest
//@TestInstance(Lifecycle.PER_CLASS)
//public class CustomerRepositoryTest {

//	@Autowired
//	private CustomerRepository customerRepo;
//
//	private Customer customer;
//
//	@BeforeAll
//	public void start() {
//		customer = customerRepo.save(new Customer(1, "test", "test", null, null, null, null, null, null));
//	}
//
//	@AfterAll
//	public void end() {
//		customerRepo.delete(customer);
//	}
//
//	// integrate test (many component)
//	
//	@Test
//	public void findById() {
//		Optional<Customer> resultCustomer = customerRepo
//				.findById(customer.getId());
//		assertThat(resultCustomer.isPresent()).isTrue();
//
//		Optional<Customer> noThingOptional = customerRepo.findById((long) 9999);
//		assertThat(noThingOptional.isEmpty()).isTrue();
//
//	}
//
//	@Test
//	public void updateById() {
//		Optional<Customer> resultCustomer = customerRepo
//				.findById(customer.getId());
//		resultCustomer.ifPresent(customer -> {
//			customer.setName("test2");
//			customerRepo.save(customer);
//		});
//		assertThat(resultCustomer.isPresent()).isTrue();
//		assertThat(resultCustomer.get().getName().equals("test2")).isTrue();
//	}
//}
