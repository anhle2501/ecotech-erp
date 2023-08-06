package vn.com.ecotechgroup.erp.service;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;

import vn.com.ecotechgroup.erp.entity.Order;
import vn.com.ecotechgroup.erp.entity.Product;
import vn.com.ecotechgroup.erp.entity.User;


public interface OrderService extends CrudService<Order>, UiService{

	public Order addProduct(Order order, Product product, int price, int quantity);
	
	public void removeProduct(Order order, Long productIndex);
	
	public void removeProduct(Order order, long productIndex);
	
	public void confirmOrder(Long id, User user);

	public Page<Order> getListPageUser(PageRequest of, Long id, String searchTerm);

	
}