package vn.com.ecotechgroup.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.com.ecotechgroup.erp.entity.OrderProduct;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long >{

}
