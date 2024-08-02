
package vn.com.ecotechgroup.erp.restcontroller.projector;

import java.util.List;

import org.springframework.data.rest.core.config.Projection;

import vn.com.ecotechgroup.erp.entity.Order;
import vn.com.ecotechgroup.erp.entity.User;

@Projection(name = "UserRestProjector", types = { User.class })
public interface UserRestProjector {

	long getId();

	String getUserName();

	String getLastName();

	String getFullName();

	String getMobilePhone();

	String getDescription();

//	List<Order> getListOrders();
}
