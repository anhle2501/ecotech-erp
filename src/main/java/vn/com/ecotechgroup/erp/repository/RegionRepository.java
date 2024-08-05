package vn.com.ecotechgroup.erp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.com.ecotechgroup.erp.entity.PaymentType;
import vn.com.ecotechgroup.erp.entity.Product;
import vn.com.ecotechgroup.erp.entity.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

    // for admin page
    @Query("SELECT r FROM Region r " + "WHERE r.name LIKE %:searchTerm% "
            + "OR r.description LIKE %:searchTerm% ")
    Page<Region> regionSearchList(Pageable pageable,
                                            @Param("searchTerm") String searchTerm);

}