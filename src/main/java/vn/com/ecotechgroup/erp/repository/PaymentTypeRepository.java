package vn.com.ecotechgroup.erp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.com.ecotechgroup.erp.entity.PaymentType;

@Repository
public interface PaymentTypeRepository
		extends JpaRepository<PaymentType, Long> {

	@Query("SELECT p FROM PaymentType p WHERE " +
			"( :searchTerm IS NULL OR :searchTerm = '' " +
			"OR lower(p.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
			"OR lower(p.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) )")
	Page<PaymentType> paymentTypeSearchList(Pageable pageable,
			@Param("searchTerm") String searchTerm);

}
