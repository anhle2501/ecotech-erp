package vn.com.ecotechgroup.erp.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.com.ecotechgroup.erp.entity.Product;
import vn.com.ecotechgroup.erp.repository.ProductRepository;
import vn.com.ecotechgroup.erp.service.ProductService;

@Service
public class ProductServiceImp implements ProductService {

	private ProductRepository proRep;

	@Autowired
	public void setProRep(ProductRepository proRep) {
		this.proRep = proRep;
	}

	@Override
	public Product save(Product t) {
		return proRep.save(t);
	}

	@Override
	public Product update(Product t) {
		return proRep.save(null);
	}

	@Override
	public void delete(Long id) {
		proRep.deleteById(id);

	}

	@Override
	public Product getOne(Long id) {
		return proRep.getReferenceById(id);
	}

	@Override
	public Page<Product> getListPage(Pageable pageable, String searchTerm) {
		return null;
	}

}
