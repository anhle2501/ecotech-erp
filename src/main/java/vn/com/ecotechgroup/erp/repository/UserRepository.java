package vn.com.ecotechgroup.erp.repository;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import vn.com.ecotechgroup.erp.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long > {
	User findByUserName(String username);
}
