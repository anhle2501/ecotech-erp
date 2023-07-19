package vn.com.ecotechgroup.erp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CrudService<T> {
	
	T save(T t);
	
	T update(T t);
	
	void delete(Long id);
	
	T getOne(Long l);
	
	Page<T> getListPage(Pageable pageable, String searchTerm);
		
}
