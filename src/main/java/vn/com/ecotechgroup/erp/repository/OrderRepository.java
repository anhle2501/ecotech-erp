package vn.com.ecotechgroup.erp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.com.ecotechgroup.erp.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long > {

	// pending
//	@Query(value = "SELECT AUTO_INCREMENT "
//			+ "FROM information_schema.tables "
//			+ "WHERE table_name = 'order' "
//			+ "AND table_schema = DATABASE( ) ;", nativeQuery = true)
//	public int getNextId();

//	@Query(value = "SELECT "
//		+ "o.id, "
//		+ "c.name, "
//		+ "p.name, "
//		+ "GROUP_CONCAT(pro.name) as product, "
//		+ "GROUP_CONCAT(pro.code) as code, "
//		+ "o.create_at, "
//		+ "o.description, "
//	   	+ "o.total_price "
//		+ "FROM Order o "
//		+ "LEFT JOIN Customer c on c.id = o.customer_id "
//		+ "LEFT JOIN payment_type p on p.id = o.payment_type_id "
//		+ "LEFT JOIN order_product op on op.order_id = o.id "
//		+ "LEFT JOIN product pro on pro.id = op.product_id "
//		+ "GROUP BY o.id ;"
//		, nativeQuery = true )
	
	@Query("SELECT o FROM Order o "
			+ "JOIN o.orderProduct oo "
			+ "WHERE "
			+ " ( LOWER(o.description) LIKE %:searchTerm% "
			+ "OR LOWER(o.customer.name) LIKE %:searchTerm% "
			+ "OR LOWER(o.paymentType.name) LIKE %:searchTerm% "
			+ "OR LOWER(oo.product.name) LIKE %:searchTerm% "
			+ "OR LOWER(oo.product.code) LIKE %:searchTerm% "
			+ "OR LOWER(oo.product.description) LIKE %:searchTerm% )"
//			+ "OR o.totalPrice = CAST(:searchTerm AS int)"
			)
	Page<Order> orderSearchList(Pageable pageable, @Param("searchTerm") String searchTerm);
	//TODO sort in all controller
	
//	@Query("SELECT c FROM Customer c "
//			+ "JOIN c.user cc "
//			+ "WHERE cc.id = :user_id "
//			+ "AND "
//			+ "(:searchTerm is null "
//			+ "OR lower(c.name) LIKE %:searchTerm% "
//			+ "OR lower(c.address) LIKE %:searchTerm% "
//			+ "OR c.phone LIKE %:searchTerm% "
//			+ "OR c.taxCode LIKE %:searchTerm% "
//			+ "OR lower(cc.fullName) LIKE %:searchTerm% "
//			+ "OR lower(c.description) LIKE %:searchTerm%)"
//	
//	
	// user
	@Query("SELECT o FROM Order o "
			+ "INNER JOIN o.userOrdered ou "
			+ "FULL OUTER JOIN o.orderProduct oo "
			+ "WHERE ou.id = :user_id "
			+ "AND "
			+ "( :searchTerm is null "
			+ "OR LOWER(o.description) LIKE %:searchTerm% "
			+ "OR LOWER(o.customer.name) LIKE %:searchTerm% "
			+ "OR LOWER(o.paymentType.name) LIKE %:searchTerm% "
			+ "OR LOWER(oo.product.name) LIKE %:searchTerm% "
			+ "OR LOWER(oo.product.code) LIKE %:searchTerm% "
			+ "OR LOWER(oo.product.description) LIKE %:searchTerm% )"
//			+ "OR o.totalPrice = CAST(:searchTerm AS int)"
			)
	Page<Order> orderSearchListUser(Pageable pageable, @Param("user_id") Long user_id, @Param("searchTerm") String searchTerm);

	
}
