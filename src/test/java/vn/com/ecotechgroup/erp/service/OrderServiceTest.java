package vn.com.ecotechgroup.erp.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import vn.com.ecotechgroup.erp.entity.Order;
import vn.com.ecotechgroup.erp.repository.OrderRepository;
import vn.com.ecotechgroup.erp.service.imp.OrderRepositoryCustomImpl;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest
@DataJpaTest
@ActiveProfiles("test")
@Import({OrderRepositoryCustomImpl.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderServiceTest {

    @Autowired
    OrderRepositoryCustom orderRepositoryCustom;


//    @BeforeAll
//    public void setUp() {
//    }

    @Test
    public void testGetOrderWithOrdinal() {
//        Order order1 = new Order();
//        order1.setDescription("Order 1");
//        orderRepository.save(order1);
//
//        Order order2 = new Order();
//        order2.setDescription("Order 2");
//        orderRepository.save(order2);

        // When
        Pageable pageable = PageRequest.of(0, 10);
        Page<Order> result = orderRepositoryCustom.findAllWithOrdinal(pageable);
        List<Order> result1 = result.get().toList();
        System.out.println(result1);
        // Then
        assertThat(result.getContent().size()).isEqualTo(2);
        assertThat(result.getContent().get(0).getDescription()).isEqualTo("Order 1");
        assertThat(result.getContent().get(1).getDescription()).isEqualTo("Order 2");
    }
}
