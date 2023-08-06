package vn.com.ecotechgroup.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import vn.com.ecotechgroup.erp.entity.Order;

public interface TestRepository extends CrudRepository<Order, Long> {

	@Modifying
	@Transactional
	@Query(value="delete from Order c where c.id = ?1")
	void deleteByIdTest(Long orderId);

}
