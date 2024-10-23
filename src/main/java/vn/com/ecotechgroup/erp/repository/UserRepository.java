package vn.com.ecotechgroup.erp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.com.ecotechgroup.erp.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByUserName(String code);

	User findByUserName(String username);

	@Query("SELECT u FROM User u " + "WHERE lower(u.userName) LIKE LOWER(CONCAT('%', :searchTerm, '%'))  "
			+ "OR lower(u.fullName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) "
			+ "OR lower(u.mobilePhone) LIKE LOWER(CONCAT('%', :searchTerm, '%')) "
			+ "OR lower(u.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " + "order by :orderBy")
	Page<User> userSearchList(Pageable pageable, String searchTerm,
			String orderBy);

	@Query("SELECT u FROM User u")
	List<User> findAllUser();
}
