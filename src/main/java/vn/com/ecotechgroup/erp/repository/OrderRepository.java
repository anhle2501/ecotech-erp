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

@Query("SELECT o FROM Order o " +
		"LEFT JOIN o.orderProduct oo " +
		"LEFT JOIN oo.product op " +
		"WHERE (:searchTerm is null OR :searchTerm = '' )" +
		"OR LOWER(o.description) LIKE %:searchTerm%  " +
		"OR LOWER(o.customer.code) LIKE %:searchTerm% " +
		"OR LOWER(o.customer.name) LIKE %:searchTerm% " +
		"OR LOWER(o.paymentType.name) LIKE %:searchTerm% " +
		"OR oo is null " +
		"OR (oo is not null and LOWER(op.name) LIKE %:searchTerm% )" +
		"OR (oo is not null and LOWER(op.code) LIKE %:searchTerm% )" +
		"OR LOWER(o.userOrdered.userName) LIKE %:searchTerm% " +
		"OR LOWER(o.userOrdered.fullName) LIKE %:searchTerm% "
		)
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
			+ "LEFT JOIN o.orderProduct oo "
			+ "LEFT JOIN oo.product op "
			+ "WHERE o.userOrdered.id = :user_id " + "AND "
			+ "( :searchTerm is null " + "OR :searchTerm = '' "
			+ "OR LOWER(o.description) LIKE %:searchTerm%  "
			+ "OR LOWER(o.customer.name) LIKE %:searchTerm% "
			+ "OR LOWER(o.paymentType.name) LIKE %:searchTerm% "
			+ "OR oo is null " +
			"OR (oo is not null and LOWER(op.name) LIKE %:searchTerm% )"
			+ "OR (oo is not null and LOWER(op.code) LIKE %:searchTerm% )"
			+ "OR LOWER(o.userOrdered.userName) LIKE %:searchTerm% "
			+ "OR LOWER(o.userOrdered.fullName) LIKE %:searchTerm% "
			+ ")")
	Page<Order> orderSearchListUser(Pageable pageable,
			@Param("user_id") Long user_id,
			@Param("searchTerm") String searchTerm);


}
