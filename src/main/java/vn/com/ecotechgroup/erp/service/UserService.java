package vn.com.ecotechgroup.erp.service;

import jakarta.transaction.Transactional;
import vn.com.ecotechgroup.erp.entity.User;

public interface UserService extends CrudService<User> {

	public boolean checkUserNameDuplicate(String userName);

}
