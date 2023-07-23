package vn.com.ecotechgroup.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.com.ecotechgroup.erp.entity.Permission;

public interface PrivilegeRepository extends JpaRepository<Permission, Long> {

}
