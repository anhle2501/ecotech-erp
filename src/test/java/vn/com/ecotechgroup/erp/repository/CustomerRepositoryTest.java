package vn.com.ecotechgroup.erp.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import vn.com.ecotechgroup.erp.entity.Customer;
import vn.com.ecotechgroup.erp.entity.CustomerTest;
import vn.com.ecotechgroup.erp.entity.User;
import vn.com.ecotechgroup.erp.repository.CustomerRepository;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    private User user;
    private Customer customer1, customer2;

//    @BeforeAll
//    void setUp() {
//        user = new User();
//        user.setUserName("admin");
//        user.setFullName("Admin User");
//        user = userRepository.save(user);
//
//        customer1 = new Customer();
//        customer1.setName("Customer 1");
//        customer1.setDescription("Description 1");
//        customer1.setCreatedBy(user);
//        customer1 = customerRepository.save(customer1);
//
//        customer2 = new Customer();
//        customer2.setName("Customer 2");
//        customer2.setDescription("Description 2");
//        customer2.setCreatedBy(user);
//        customer2 = customerRepository.save(customer2);
//    }
//
//    @AfterAll
//    public void deleteAll() {
//
//    }
//
//    @Test
//    void testFindCustomerByNameContainsOrDescriptionContains() {
//        Pageable pageable = PageRequest.of(0, 10);
//        Page<Customer> page = customerRepository.findCustomerByNameContainsOrDescriptionContains(pageable, "Customer", "Description");
//
//        assertThat(page.getContent()).hasSize(2);
//    }
//
//    @Test
//    void testGetCustomerByCreatedByOrderByName() {
//        List<Customer> customers = customerRepository.getCustomerByCreatedByOrderByName(user);
//
//        assertThat(customers).hasSize(2);
//    }
//
//    @Test
//    void testCustomerSearchListAdmin() {
//        Pageable pageable = PageRequest.of(0, 10);
//        Page<Customer> page = customerRepository.customerSearchListAdmin(pageable, "Customer");
//
//        assertThat(page.getContent()).hasSize(2);
//    }
//
//    @Test
//    void testCustomerSearchListUser() {
//        Pageable pageable = PageRequest.of(0, 10);
//        Page<Customer> page = customerRepository.customerSearchListUser(pageable, (Long) user.getId(), "Customer");
//
//        assertThat(page.getContent()).hasSize(2);
//    }
//
//    @Test
//    void testFindAllCustomerById() {
//        List<Customer> customers = customerRepository.findAllCustomerForUser( (Long) user.getId());
//
//        assertThat(customers).hasSize(2);
//    }
}