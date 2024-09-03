package vn.com.ecotechgroup.erp.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.com.ecotechgroup.erp.entity.Region;
import vn.com.ecotechgroup.erp.entity.Role;
import vn.com.ecotechgroup.erp.entity.User;
import vn.com.ecotechgroup.erp.entity.dto.UserDTO;
import vn.com.ecotechgroup.erp.entity.mapper.UserMapper;
import vn.com.ecotechgroup.erp.repository.RegionRepository;
import vn.com.ecotechgroup.erp.repository.RoleRepository;
import vn.com.ecotechgroup.erp.repository.UserRepository;
import vn.com.ecotechgroup.erp.service.UserService;

@Service
public class UserServiceImp implements UserService {

	private final RegionRepository regionRepository;
	private UserRepository userRepo;
	private PasswordEncoder passwordEncoder;
	private RegionRepository regionRep;
	private RoleRepository roleRepo;
	private UserMapper userMapper;

	@Autowired
	public UserServiceImp(UserRepository userRepo, RoleRepository roleRepo,
						  PasswordEncoder passwordEncoder,
						  RegionRepository regionRep,
						  UserMapper userMapper, RegionRepository regionRepository) {
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		this.passwordEncoder = passwordEncoder;
		this.regionRep = regionRep;
		this.userMapper = userMapper;
		this.regionRepository = regionRepository;
	}

	@Override
	public boolean checkUserNameDuplicate(String userName) {
		return userRepo.findByUserName(userName) != null;
	}


	public User save(User user) {
		// Encrypt password
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		// add role
		Role role = roleRepo.getRoleByName("ROLE_USER");
		user.getListRole().add(role);

		// save and return
        return userRepo.save(user);

	}

	@Override
	@Transactional
	public UserDTO update(UserDTO userDTO) {
		User oldUser = userRepo.findById(userDTO.getId())
				.orElseThrow(() -> new IllegalArgumentException("User not found"));

		// set list role
		List<Region> regions = Optional.ofNullable(userDTO.getRegionsId()).orElseGet(ArrayList::new).stream()
				.map(roleId -> regionRepository.findById(roleId)
						.orElseThrow(() -> new IllegalArgumentException("Invalid role ID: " + roleId)))
				.collect(Collectors.toList());
		userDTO.setRegions(regions);

		// set list role
		List<Role> roles = Optional.ofNullable(userDTO.getListRoleId()).orElseGet(ArrayList::new).stream()
				.map(ids -> roleRepo.findById(ids)
						.orElseThrow(() -> new IllegalArgumentException("Invalid role ID: " + ids)))
				.collect(Collectors.toList());
		userDTO.setListRole(roles);

		// Update user entity from DTO except for password
		userMapper.updateUserFromDTO(userDTO, oldUser);

		// Set the converted list back to userDTO
		User updatedUser = userRepo.save(oldUser);
		return userMapper.userToUserDTO(updatedUser);
	}

	@Override
	public void delete(Long id) {
		userRepo.deleteById(id);
	}

	@Override
	public UserDTO getOne(Long id) {
		User user = userRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("User not found"));
		return userMapper.userToUserDTO(user);
	}

	@Override
	public Page<UserDTO> getListPage(Pageable pageable, String searchTerm) {
		Page<User> users = userRepo.userSearchList(pageable, searchTerm, "userName");
		return users.map(userMapper::userToUserDTO);
	}

	@Override
	@Transactional(readOnly = true)
	public UserDTO getUserName(String userName) {
		User user = userRepo.findByUserName(userName);
		return userMapper.userToUserDTO(user);
	}

}
