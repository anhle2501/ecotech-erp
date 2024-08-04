package vn.com.ecotechgroup.erp.service.imp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import vn.com.ecotechgroup.erp.entity.Order;
import vn.com.ecotechgroup.erp.service.OrderRepositoryCustom;

import java.util.List;
@Component
public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Order> findAllWithOrdinal(Pageable pageable) {
        String baseQuery = "SELECT o.*, ROW_NUMBER() OVER (ORDER BY o.id) AS ordinal_number FROM ecotechgroup_erp.an_order o";
        String countQuery = "SELECT COUNT(*) FROM ecotechgroup_erp.an_order";


        Query query = entityManager.createNativeQuery(baseQuery, Order.class)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize());

        Query count = entityManager.createNativeQuery(countQuery);
        long total = ((Number) count.getSingleResult()).longValue();

        @SuppressWarnings("unchecked")
        List<Order> results = query.getResultList();

        return new PageImpl<>(results, pageable, total);
    }
}
