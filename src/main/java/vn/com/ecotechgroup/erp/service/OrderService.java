package vn.com.ecotechgroup.erp.service;

import org.springframework.stereotype.Service;

import vn.com.ecotechgroup.erp.entity.Order;
import vn.com.ecotechgroup.erp.entity.Product;


public interface OrderService extends CrudService<Order>, UiService{

	public void addProduct(Order order, Product product, int price, int quantity);
	
	public void removeProduct(Order order, Integer productIndex);
}