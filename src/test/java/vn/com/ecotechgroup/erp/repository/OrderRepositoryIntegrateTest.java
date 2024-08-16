package vn.com.ecotechgroup.erp.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import vn.com.ecotechgroup.erp.entity.*;
import vn.com.ecotechgroup.erp.entity.Order;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@SpringBootTest
@ActiveProfiles("dev")
class OrderRepositoryIntegrateTest {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderProductRepository orderProductRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private PaymentTypeRepository paymentTypeRepository;

	@Autowired
	private UserRepository userRepository;


	@BeforeAll
	static void initAll() {
//		User user = new User("testUser", "testUser", "testUser", "testUser", "testUser", "testUser");
//		Order order = Order.builder()
//				.userOrdered()
//				.build();
	}

	@BeforeEach
	void init() {
	}

	@Test
	void succeedingTest() {


	}

//	@Test
//	void failingTest() {
//		fail("a failing test");
//	}

	@Test
	@Disabled("for demonstration purposes")
	void skippedTest() {
		// not executed
	}

	@Test
	void abortedTest() {
		assumeTrue("abc".contains("Z"));
		fail("test should have been aborted");
	}

	@Test
	@Transactional
	void testCreateOrderWithProducts() {
		// Setup
		User user = userRepository.save(User.builder().userName("tester").password("123456789").build());

		Customer customer = customerRepository.save(Customer.builder().name("customerTest").code("test").build()
		);
		PaymentType paymentType = paymentTypeRepository.save(PaymentType.builder().name("test").build());

		Product product1 = productRepository.save(Product.builder().name("test01").build());
		Product product2 = productRepository.save(Product.builder().name("test02").build());

		Order order = Order.builder()
				.customer(customer)
				.paymentType(paymentType)
				.userOrdered(user)
				.description("Test Order")
				.build();

		order.setOrderProduct(new ArrayList<>());

		OrderProduct orderProduct1 = new OrderProduct(order, product1, 100, 2);
		OrderProduct orderProduct2 = new OrderProduct(order, product2, 200, 1);

		order.getOrderProduct().add(orderProduct1);
		order.getOrderProduct().add(orderProduct2);

		// Execute
		Order savedOrder = orderRepository.save(order);

		 //Verify
		assertThat(savedOrder.getId()).isNotNull();
		assertThat(savedOrder.getOrderProduct()).hasSize(2);

		// test delete order (cascade)

		Order orderF = orderRepository.findById(savedOrder.getId()).get();
		System.out.println(order.getOrderProduct());
		orderF.setConfirmByUser(null);
		orderF.setUserOrdered(null);
		orderF.setCustomer(null);
		orderF.setPaymentType(null);
		orderF.setUserModified(null);
		Order saveOrder = orderRepository.save(order);  // Save the order to update associations
		orderRepository.deleteById(saveOrder.getId());


		assertThat(orderRepository.findById(saveOrder.getId())).isEmpty();
		assertThat(orderProductRepository.findByOrderId(saveOrder.getId())).hasSize(0);

		paymentTypeRepository.delete(paymentType);
		assertThat(paymentTypeRepository.findById(paymentType.getId())).isEmpty();
//
		userRepository.delete(user);
		assertThat(userRepository.findById(user.getId())).isEmpty();

	}

//	@Test
//	void testDeleteOrderCascade() {
//		// Setup
//		Customer customer = customerRepository.save(Customer.builder().name("customerTest").code("test").build()
//		);
//		PaymentType paymentType = paymentTypeRepository.save(PaymentType.builder().name("test").build());
//		User user = userRepository.save(User.builder().userName("tester").password("123456789").build());
//
//		Product product1 = productRepository.save(Product.builder().name("test01").build());
//		Product product2 = productRepository.save(Product.builder().name("test02").build());
//
//		Order order = Order.builder()
//				.customer(customer)
//				.paymentType(paymentType)
//				.userOrdered(user)
//				.description("Test Order")
//				.build();
//
//		OrderProduct orderProduct1 = new OrderProduct(order, product1, 100, 2);
//		OrderProduct orderProduct2 = new OrderProduct(order, product2, 200, 1);
//
//		order.getOrderProduct().add(orderProduct1);
//		order.getOrderProduct().add(orderProduct2);
//
//		// Execute
//		Order savedOrder = orderRepository.save(order);
//
//		// Verify
//		assertThat(savedOrder.getId()).isNotNull();
//		assertThat(savedOrder.getOrderProduct()).hasSize(2);
//		assertThat(orderProductRepository.findAll()).hasSize(2);
//
//		// Execute
//		orderRepository.delete(savedOrder);
//
//		// Verify
//		assertThat(orderRepository.findById(savedOrder.getId())).isEmpty();
//		assertThat(orderProductRepository.findAll()).isEmpty();
//	}
//
//	@Test
//	void testOrphanRemoval() {
//		// Setup
//
//		Order order = Order.builder()
//				.customer(customer)
//				.paymentType(paymentType)
//				.userOrdered(user)
//				.description("Test Order")
//				.build();
//
//		OrderProduct orderProduct1 = new OrderProduct(order, product1, 100, 2);
//		OrderProduct orderProduct2 = new OrderProduct(order, product2, 200, 1);
//
//		order.getOrderProduct().add(orderProduct1);
//		order.getOrderProduct().add(orderProduct2);
//
//		// Execute
//		Order savedOrder = orderRepository.save(order);
//
//		// Verify
//		assertThat(savedOrder.getId()).isNotNull();
//		assertThat(savedOrder.getOrderProduct()).hasSize(2);
//		assertThat(orderProductRepository.findAll()).hasSize(2);
//
//		// Remove the product from the order
//		savedOrder.getOrderProduct().clear();
//		orderRepository.save(savedOrder);
//
//		// Verify that the OrderProduct is also removed from the database
//		assertThat(orderProductRepository.findAll()).isEmpty();
//	}

	@AfterEach
	void tearDown() {
	}

	@AfterAll
	static void tearDownAll() {
	}
}