package vn.com.ecotechgroup.erp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import vn.com.ecotechgroup.erp.entity.Permission;
import vn.com.ecotechgroup.erp.entity.Role;

@Repository
//@RestResource(exported = false)
public interface PermissionRepository extends JpaRepository<Permission, Long> {

	List<Permission> findPermissionByNameStartingWith(String beginWith);

	List<Permission> findPermissionByName(String beginWith);

	@Query("SELECT p FROM Permission p WHERE " +
			"( :searchTerm IS NULL OR :searchTerm = '' " +
			"OR lower(p.name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))" +
			"OR lower(p.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) )")
	Page<Permission> permissionSearchList(Pageable pageable,
							  @Param("searchTerm") String searchTerm);
}
