package vn.com.ecotechgroup.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.com.ecotechgroup.erp.entity.OrderProduct;

@Repository
public interface OrderProductRepository
		extends JpaRepository<OrderProduct, Long> {

}
