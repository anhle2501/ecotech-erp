package vn.com.ecotechgroup.erp.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import vn.com.ecotechgroup.erp.entity.Authorities;
import vn.com.ecotechgroup.erp.entity.User;
import vn.com.ecotechgroup.erp.repository.AuthoritiesRepository;
import vn.com.ecotechgroup.erp.repository.UserRepository;
import vn.com.ecotechgroup.erp.service.UserService;

@Service
public class UserServiceImp implements UserService {

	private UserRepository userRepo;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImp(UserRepository userRepo, AuthoritiesRepository auRepo, PasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public boolean checkUserNameDuplicate(String userName) {
		Optional<User> optU = Optional.ofNullable(userRepo.findByUserName(userName));
		if (optU.isEmpty())
			return false;
		return true;
	}

	@Override
	public User save(User t) {
		System.out.println("dang o day");
		String pw = t.getPassword();
		pw = passwordEncoder.encode(pw);
		t.setPassword(pw);
		
		User user = userRepo.save(t);
		System.out.println(user);
		
		Authorities au = new Authorities();
		au.setAuthority("ROLE_USER");
		au.setUserId(user.getId());
		List<Authorities> tmpAuthorities = t.getListAuth();
		tmpAuthorities.add(au);
		
		user = userRepo.save(t);
		return user;
		
	}

	@Override
	public User update(User t) {
		return userRepo.save(t);
	}

	@Override
	public void delete(Long id) {
		userRepo.deleteById(id);
	}

	@Override
	public User getOne(Long id) {
		return userRepo.getReferenceById(id);
	}

	@Override
	public Page<User> getListPage(Pageable pageable, String searchTerm) {
		return userRepo.userSearchList(pageable,searchTerm, "userName");
	}

}
