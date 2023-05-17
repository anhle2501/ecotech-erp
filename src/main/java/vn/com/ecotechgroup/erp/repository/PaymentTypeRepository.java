package vn.com.ecotechgroup.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.com.ecotechgroup.erp.entity.PaymentType;

@Repository
public interface PaymentTypeRepository extends JpaRepository<PaymentType, Integer>{

}
