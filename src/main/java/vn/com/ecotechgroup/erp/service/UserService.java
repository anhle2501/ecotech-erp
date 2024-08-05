package vn.com.ecotechgroup.erp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import vn.com.ecotechgroup.erp.entity.User;
import vn.com.ecotechgroup.erp.entity.dto.UserDTO;

public interface UserService{

	boolean checkUserNameDuplicate(String userName);

	void delete(Long id);

	UserDTO getOne(Long id);

	UserDTO getUserName(String userName);

	UserDTO update(UserDTO userDTO);

	User save(User user);

	Page<UserDTO> getListPage(Pageable of, String searchTerm);

}
