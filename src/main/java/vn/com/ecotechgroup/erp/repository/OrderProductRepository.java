package vn.com.ecotechgroup.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.com.ecotechgroup.erp.entity.OrderProduct;
import vn.com.ecotechgroup.erp.entity.OrderProductKey;

public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductKey>{

}
