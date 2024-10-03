package vn.com.ecotechgroup.erp.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import vn.com.ecotechgroup.erp.entity.Order;

import static org.hamcrest.MatcherAssert.assertThat;

@ActiveProfiles("test")
@SpringBootTest
public class OrderServiceGetTest {
    OrderService orderService;
    final int PAGE = 0;
    final int SIZE = 100;

    @Autowired
    OrderServiceGetTest(OrderService orderService) {
        this.orderService = orderService;
    }

    @Test
    public void testGetOrderListAdmin() {
        Page<Order> orders = orderService.getListPage(PageRequest.of(PAGE, SIZE, Sort.by("id").ascending()), null);
        // then
        Assertions.assertThat(orders.getTotalElements()).isGreaterThan(20L);
        Assertions.assertThat(orders.getNumber()).isEqualTo(0);
        Assertions.assertThat(orders.getSize()).isEqualTo(50L);
    }
}
