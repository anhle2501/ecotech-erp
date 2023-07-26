package vn.com.ecotechgroup.erp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.com.ecotechgroup.erp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUserName(String username);
	
	
	@Query("SELECT u FROM User u "
			+ "WHERE u.userName LIKE %:searchTerm% "
			+ "OR u.fullName LIKE %:searchTerm% "
			+ "OR u.mobilePhone LIKE %:searchTerm% "
			+ "OR u.description LIKE %:searchTerm% "
			+ "order by :orderBy"
			)
	Page<User> userSearchList(Pageable pageable, String searchTerm, String orderBy);
}
