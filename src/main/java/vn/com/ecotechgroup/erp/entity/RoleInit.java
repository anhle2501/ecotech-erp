package vn.com.ecotechgroup.erp.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import vn.com.ecotechgroup.erp.repository.RoleRepository;

@Data
public class RoleInit {
	
	private RoleRepository roleRep;
	private Role role;
	
	@Autowired
	RoleInit(RoleRepository roleRep) {
		this.roleRep = roleRep;
	}
	
	
	RoleInit(){
		 List<Role> roles = roleRep.findAll();
		 if (roles.isEmpty()) {
			 role = new Role("ADMIN_ROLE","Quyền admin");
			 try {
				 roleRep.save(role);
			 } catch (Exception e) {
				 System.out.println("RoleInit.java failed");
			 }
		 }
	}
}
