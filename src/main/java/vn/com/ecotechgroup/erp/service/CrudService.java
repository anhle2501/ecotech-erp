package vn.com.ecotechgroup.erp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import vn.com.ecotechgroup.erp.entity.Order;


public interface CrudService<T> {
	
	T save(T t);
	
	T update(int id);
	
	void delete(T t);
	
	T getOne(int id);
	
	Page<T> getListPage(Pageable pageable, String searchTerm);
		
}
