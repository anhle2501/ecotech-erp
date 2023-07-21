package vn.com.ecotechgroup.erp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.com.ecotechgroup.erp.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long >{

	Page<Customer> findCustomerByNameContainsOrDescriptionContains(Pageable pageable, String name, String description);

	
}
