package vn.com.ecotechgroup.erp.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.com.ecotechgroup.erp.entity.OrderProduct;
import vn.com.ecotechgroup.erp.repository.OrderProductRepository;
import vn.com.ecotechgroup.erp.service.CrudService;

@Service
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
	public OrderProduct update(OrderProduct t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		opRep.deleteById((long) id);
	}

	@Override
	public OrderProduct getOne(Long id) {
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
