package vn.com.ecotechgroup.erp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.com.ecotechgroup.erp.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	boolean existsByCode(String code);
	boolean existsByName(String code);

	@Query("SELECT p FROM Product p " + "WHERE "
			+ "( :searchTerm is null " + "OR :searchTerm = '' "
			+ "OR LOWER(p.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) "
			+ "OR LOWER(p.code) LIKE LOWER(CONCAT('%', :searchTerm, '%')) "
			+ "OR LOWER(p.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) "
			+ ")")
	Page<Product> productSearchList(Pageable pageable, String searchTerm);
}
