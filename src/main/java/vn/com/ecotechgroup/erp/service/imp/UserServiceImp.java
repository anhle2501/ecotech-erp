package vn.com.ecotechgroup.erp.service.imp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.com.ecotechgroup.erp.entity.Role;
import vn.com.ecotechgroup.erp.entity.User;
import vn.com.ecotechgroup.erp.repository.RoleRepository;
import vn.com.ecotechgroup.erp.repository.UserRepository;
import vn.com.ecotechgroup.erp.service.UserService;

@Service
public class UserServiceImp implements UserService {

	private UserRepository userRepo;
	private PasswordEncoder passwordEncoder;

	private RoleRepository roleRepo;

	@Autowired
	public UserServiceImp(UserRepository userRepo, RoleRepository roleRepo,
			PasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		this.passwordEncoder = passwordEncoder;

	}

	@Override
	public boolean checkUserNameDuplicate(String userName) {
		Optional<User> optU = Optional
				.ofNullable(userRepo.findByUserName(userName));
		if (optU.isEmpty())
			return false;
		return true;
	}

	@Override
	public User save(User t) {
		// add user
		String pw = t.getPassword();
		// encrypt password
		pw = passwordEncoder.encode(pw);
		t.setPassword(pw);

		// add role
		Role role = roleRepo.getRoleByName("ROLE_USER");
		System.out.println(role);
		t.getListRole().add(role);
		User user = userRepo.save(t);
		return user;

	}

	@Override
	public User update(User t) {
		// get old pw
		String oldPw = userRepo.getReferenceById(t.getId()).getPassword();
		String pw = t.getPassword();
		// encrypt password
		boolean isChange = passwordEncoder.matches(t.getPassword(), oldPw);
		// it pw change -> set new pw
		if (!isChange) {
			t.setPassword(passwordEncoder.encode(pw));
		}
		return userRepo.save(t);
	}

	@Override
	public void delete(Long id) {
		User user = userRepo.getReferenceById(id);
		userRepo.delete(user);
	}

	@Override
	@Transactional
	public User getOne(Long id) {
		return userRepo.getReferenceById(id);
	}

	@Override
	public Page<User> getListPage(Pageable pageable, String searchTerm) {
		return userRepo.userSearchList(pageable, searchTerm, "userName");
	}

}
