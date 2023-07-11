package vn.com.ecotechgroup.erp.service;

import org.springframework.stereotype.Service;

import vn.com.ecotechgroup.erp.entity.Order;
import vn.com.ecotechgroup.erp.entity.Product;


public interface OrderService extends CrudService<Order>, UiService{

	public Order addProduct(Order order, Product product, int price, int quantity);
	
	public void removeProduct(Order order, Long productIndex);
	
	public void removeProduct(Order order, long productIndex);
	
}