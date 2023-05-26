package vn.com.ecotechgroup.erp.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.com.ecotechgroup.erp.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
