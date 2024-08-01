package vn.com.ecotechgroup.erp.service.imp;

import java.util.Optional;
import java.util.concurrent.ConcurrentMap;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import vn.com.ecotechgroup.erp.entity.Customer;
import vn.com.ecotechgroup.erp.entity.Region;
import vn.com.ecotechgroup.erp.repository.CustomerRepository;
import vn.com.ecotechgroup.erp.repository.RegionRepository;
import vn.com.ecotechgroup.erp.service.CustomerService;

@Service
public class CustomerServiceImp implements CustomerService {

	@Autowired
	private CustomerRepository customerRep;
	@Autowired
	private RegionRepository regionRep;
	
	@Autowired
	public CustomerServiceImp(CustomerRepository customerRep) {
		this.customerRep = customerRep;
	}

	@Override
	public Customer save(Customer t) {
		return customerRep.save(t);
	}

	@Override
	public Customer update(Customer t) {
		t.getRegions().forEach( r -> {
			Optional<Region> region = regionRep.findById(r.getId());
			region.ifPresent( e -> e.getCustomers().add(t));
		});
		return customerRep.save(t);
	}

	@Override
	public void delete(Long id) {
		try {
			customerRep.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	@Cacheable(value = "customer", key = "#l")
	public Customer getOne(Long l) {
		Customer customer = customerRep.findById(l).orElse(new Customer());
		return customer;
	}

	@Override
	public Page<Customer> getListPage(Pageable pageable, String searchTerm) {

		return customerRep.customerSearchListAdmin(pageable, searchTerm);
	}

}
