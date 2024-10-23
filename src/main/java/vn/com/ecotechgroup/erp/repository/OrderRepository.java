package vn.com.ecotechgroup.erp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.com.ecotechgroup.erp.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

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

	@Query("SELECT DISTINCT o FROM Order o " +
			"LEFT JOIN o.customer oc "+
			"LEFT JOIN o.userOrdered uso "+
			"LEFT JOIN o.orderProduct oo " +
			"LEFT JOIN oo.product op " +
			"WHERE (:searchTerm IS NULL OR :searchTerm = '') " +
			"OR LOWER(o.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
			"OR LOWER(o.customer.code) LIKE LOWER(CONCAT('%', :searchTerm, '%'))  " +
			"OR LOWER(o.customer.name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))  " +
			"OR LOWER(o.paymentType.name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))  " +
			"OR LOWER(o.userOrdered.userName) LIKE LOWER(CONCAT('%', :searchTerm, '%'))  " +
			"OR LOWER(o.userOrdered.fullName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
			"OR oo IS NULL " +
			"OR EXISTS (SELECT 1 FROM OrderProduct oo2 " +
				"JOIN oo2.product op2 " +
				"WHERE oo2 MEMBER OF o.orderProduct " +
				"AND (LOWER(op2.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
				"OR LOWER(op2.code) LIKE LOWER(CONCAT('%', :searchTerm, '%'))))")

	Page<Order> orderSearchList(Pageable pageable,
			@Param("searchTerm") String searchTerm);



	// user
//	@Query("SELECT o FROM Order o "
//			+ "LEFT JOIN o.userOrdered ou "
//			+ "LEFT JOIN o.orderProduct oo "
//			+ "LEFT JOIN o.orderProduct.product oop "
//			+ "WHERE ou.id = :user_id "
//			+ "AND "
//			+ "( :searchTerm is null  "
//			+ "OR LOWER(o.description) LIKE %:searchTerm%  "
//			+ "OR LOWER(o.customer.name) LIKE %:searchTerm% "
//			+ "OR LOWER(o.paymentType.name) LIKE %:searchTerm% "
//			+ "OR LOWER(oop.name) LIKE %:searchTerm% "
//			+ "OR LOWER(oop.code) LIKE %:searchTerm% "
//			+ "OR LOWER(oop.description) LIKE %:searchTerm% "
//			+ "OR o.totalPrice = CAST(TRIM(:searchTerm) AS integer) ) "
//			)

	@Query("SELECT o FROM Order o " + "LEFT JOIN o.userOrdered ou "
			+ "LEFT JOIN o.customer oc "
			+ "LEFT JOIN o.userOrdered uso "
			+ "LEFT JOIN o.orderProduct oo "
			+ "LEFT JOIN oo.product op "
			+ "WHERE o.userOrdered.id = :user_id " + "AND "
			+ "( :searchTerm is null " + "OR :searchTerm = '' "
			+ "OR LOWER(o.description) LIKE LOWER(CONCAT('%', :searchTerm, '%'))   "
			+ "OR LOWER(o.customer.name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))  "
			+ "OR LOWER(o.paymentType.name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))  "
			+ "OR oo is null " +
			"OR (oo is not null and LOWER(op.name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))  )"
			+ "OR (oo is not null and LOWER(op.code) LIKE LOWER(CONCAT('%', :searchTerm, '%'))  )"
			+ "OR LOWER(o.userOrdered.userName) LIKE LOWER(CONCAT('%', :searchTerm, '%'))  "
			+ "OR LOWER(o.userOrdered.fullName) LIKE LOWER(CONCAT('%', :searchTerm, '%'))  "
			+ ")")
	Page<Order> orderSearchListUser(Pageable pageable,
			@Param("user_id") Long user_id,
			@Param("searchTerm") String searchTerm);


}
