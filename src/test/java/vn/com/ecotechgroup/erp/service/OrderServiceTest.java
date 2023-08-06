//package vn.com.ecotechgroup.erp.service;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.when;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//
//import vn.com.ecotechgroup.erp.entity.Customer;
//import vn.com.ecotechgroup.erp.entity.Order;
//import vn.com.ecotechgroup.erp.entity.OrderProduct;
//import vn.com.ecotechgroup.erp.entity.PaymentType;
//import vn.com.ecotechgroup.erp.entity.Product;
//import vn.com.ecotechgroup.erp.repository.OrderRepository;
//import vn.com.ecotechgroup.erp.service.imp.OrderServiceImp;
//
//public class OrderServiceTest {
//
//	@Mock
//	private OrderRepository oRep;
//	
//	@InjectMocks
//	private OrderServiceImp oSerImp;
//	
//	private Order order;
//	
//	private Customer customer;
//	
//	private PaymentType paymentType;
//	
//	private Product product;
//	
//	private OrderProduct orderProduct;
//	
//	
//	@BeforeEach
//	public void setup() {
//		customer = Customer.builder()
//				.address("40/20 Lu Gia")
//				.code("KH0001")
//				.description("Khách hàng thường")
//				.id(1l).name("NhutAnh").phone("0374749933").taxCode("123456789")
//				.build();
//		
//		paymentType = PaymentType.builder()
//				.day(30)
//				.id(1L)
//				.name("TT30")
//				.build();
//		
//		product = Product.builder()
//				.id(1L).code("BVTV").name("Product 1")
//				.build();
//		
//		
//		
//		order = Order.builder()
//				.customer(customer)
//				.paymentType(paymentType)
//				.id(1l).isConfirm(false)
//				.orderProduct(null)
//				.description("This is order description")
//				.build();
//		
//		order.addProduct(product, 100, 10);
//		
//	}
//	
//	@Test
//	@DisplayName("Test Order service")
//	public void order_product_add_is_working() {
//		
//		
////		when(oRep.count()).thenReturn(1L);
////		when(oRep.getReferenceById(1L)).thenReturn(order);
//		when(oRep.save(order)).thenReturn(order);
//		
//		System.out.println(oRep);
//		System.out.println(oSerImp);
//		
//		 // when -  action or the behaviour that we are going test
//	    Order savedOrder = oSerImp.save(order);
//	    
//	    System.out.println(savedOrder);
//
//	    System.out.println(savedOrder);
//	    // then - verify the output
//	    assertThat(savedOrder).isNotNull();
//	}
//
//	
//	
//}
