package vn.com.ecotechgroup.erp.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.com.ecotechgroup.erp.entity.OrderProduct;
import vn.com.ecotechgroup.erp.repository.OrderProductRepository;
import vn.com.ecotechgroup.erp.service.CrudService;

public class OrderProductServiceImp implements CrudService<OrderProduct> {

	OrderProductRepository opRep;
	
	@Autowired
	public void setOpRep(OrderProductRepository opRep) {
		this.opRep = opRep;
	}

	@Override
	public OrderProduct save(OrderProduct t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderProduct update(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int id) {
		opRep.deleteById((long) id);
	}

	@Override
	public OrderProduct getOne(long id ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<OrderProduct> getListPage(Pageable pageable,
			String searchTerm) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
