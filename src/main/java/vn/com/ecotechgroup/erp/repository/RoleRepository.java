package vn.com.ecotechgroup.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import vn.com.ecotechgroup.erp.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role getRoleByName(String name);
}
