package vn.com.ecotechgroup.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import vn.com.ecotechgroup.erp.entity.Permission;

@Repository
//@RestResource(exported = false)
public interface PermissionRepository extends JpaRepository<Permission, Long> {

	List<Permission> findPermissionByNameStartingWith(String beginWith);

}
