package vn.com.ecotechgroup.erp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.com.ecotechgroup.erp.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("SELECT p FROM Product p "
			+ "WHERE p.name LIKE %:searchTerm% "
			+ "OR p.code LIKE %:searchTerm% "
			+ "OR p.description LIKE %:searchTerm% ")
	Page<Product> productSearchList(Pageable pageable, @Param("searchTerm") String searchTerm);
}
