package vn.com.ecotechgroup.erp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.com.ecotechgroup.erp.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role getRoleByName(String name);
	boolean existsByName(String name);

	// admin
	@Query("SELECT p FROM Role p WHERE " +
			"( :searchTerm IS NULL OR :searchTerm = '' " +
			"OR lower(p.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
			"OR lower(p.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) )")
	Page<Role> roleSearchList(Pageable pageable,
											@Param("searchTerm") String searchTerm);
}
