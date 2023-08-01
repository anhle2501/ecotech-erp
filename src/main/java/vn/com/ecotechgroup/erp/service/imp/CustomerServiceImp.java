package vn.com.ecotechgroup.erp.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.com.ecotechgroup.erp.entity.Customer;
import vn.com.ecotechgroup.erp.repository.CustomerRepository;
import vn.com.ecotechgroup.erp.service.CustomerService;

@Service
public class CustomerServiceImp implements CustomerService {

	private CustomerRepository customerRep;
	
	
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
		
		return customerRep.save(t);
	}

	@Override
	public void delete(Long id) {
		
		customerRep.deleteById(id);
	}

	@Override
	public Customer getOne(Long l) {
		
		return customerRep.getReferenceById(l);
	}

	@Override
	public Page<Customer> getListPage(Pageable pageable, String searchTerm) {
		
		return customerRep
				.customerSearchListAdmin(pageable, searchTerm);
	}

	
	
}
