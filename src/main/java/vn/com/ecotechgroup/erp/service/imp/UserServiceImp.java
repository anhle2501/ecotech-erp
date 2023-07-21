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
	private AuthoritiesRepository auRepo;
	

	@Autowired
	public UserServiceImp(UserRepository userRepo, AuthoritiesRepository auRepo, PasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
		this.auRepo = auRepo;
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
		// add user
		String pw = t.getPassword();
		// encrypt password
		pw = passwordEncoder.encode(pw);
		t.setPassword(pw);
		User user = userRepo.save(t);
		// add role
		Authorities au = new Authorities();
		au.setAuthority("ROLE_USER");
		au.setUserId(user.getId());
		auRepo.save(au);
		
		return user;
		
	}

	@Override
	public User update(User t) {
		// encrypt password
		String pw = t.getPassword();
		pw = passwordEncoder.encode(pw);
		t.setPassword(pw);
		
		// keep authority
		List<Authorities> listAu = userRepo.getReferenceById(t.getId()).getListAuth();
		t.setListAuth(listAu);
		return userRepo.save(t);
	}

	@Override
	public void delete(Long id) {
		auRepo.deleteById(id);
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
