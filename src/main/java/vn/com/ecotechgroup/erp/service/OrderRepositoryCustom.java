package vn.com.ecotechgroup.erp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.com.ecotechgroup.erp.entity.Order;

public interface OrderRepositoryCustom {
    Page<Order> findAllWithOrdinal(Pageable pageable);
}
