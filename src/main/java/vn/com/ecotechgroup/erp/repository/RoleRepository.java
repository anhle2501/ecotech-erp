package vn.com.ecotechgroup.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.com.ecotechgroup.erp.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role getRoleByName(String name);
}
