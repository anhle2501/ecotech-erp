package vn.com.ecotechgroup.erp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.com.ecotechgroup.erp.entity.Customer;
import vn.com.ecotechgroup.erp.entity.User;
import vn.com.ecotechgroup.erp.entity.projector.CustomerUserProjector;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	// admin page
	Page<Customer> findCustomerByNameContainsOrDescriptionContains(
			Pageable pageable, String name, String description);

	List<Customer> getCustomerByCreatedByOrderByName(User user);

	@Query("SELECT c FROM Customer c " + "INNER JOIN c.createdBy cc " + "WHERE "
			+ "(:searchTerm is null " + "OR lower(c.name) LIKE %:searchTerm% "
			+ "OR lower(c.address) LIKE %:searchTerm% "
			+ "OR c.phone LIKE %:searchTerm% "
			+ "OR c.taxCode LIKE %:searchTerm% "
			+ "OR lower(cc.fullName) LIKE %:searchTerm% "
			+ "OR lower(c.description) LIKE %:searchTerm%)")
	Page<Customer> customerSearchListAdmin(Pageable pageable,
			@Param("searchTerm") String searchTerm);

	// user page
	@Query("SELECT c FROM Customer c " + "LEFT JOIN c.createdBy cc "  + "  LEFT JOIN c.idUserBelong cbl "
			+ "LEFT JOIN c.regions reg "
			+ "WHERE (cc.id = :user_id OR cbl.id = :user_id OR (:user_id MEMBER OF reg.users)  ) " + "AND " + "(:searchTerm is null "
			+ "OR lower(c.name) LIKE %:searchTerm% "
			+ "OR lower(c.address) LIKE %:searchTerm% "
			+ "OR c.phone LIKE %:searchTerm% "
			+ "OR c.taxCode LIKE %:searchTerm% "
			+ "OR lower(cc.fullName) LIKE %:searchTerm% "
			+ "OR lower(c.description) LIKE %:searchTerm%)")
	Page<Customer> customerSearchListUser(Pageable pageable,
			@Param("user_id") Long user_id,
			@Param("searchTerm") String searchTerm);

	@Query("SELECT c FROM Customer c " + "LEFT JOIN c.createdBy cc "  + " LEFT JOIN c.idUserBelong cbl "
			+ "LEFT JOIN c.regions reg "
			+ "WHERE (cc.id = :user_id OR cbl.id = :user_id OR (:user_id MEMBER OF reg.users))")
	List<Customer> findAllCustomerById(@Param("user_id") Long user_id);
}
