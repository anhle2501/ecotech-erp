package vn.com.ecotechgroup.erp.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import vn.com.ecotechgroup.erp.repository.PermissionRepository;

@Data
@Component
public class PermissionInit {

	private PermissionRepository permissionRep;
	
	private Permission permission;
	
	@Autowired
	
	public PermissionInit(PermissionRepository permissionRep) {
		this.permissionRep = permissionRep;
	}
	
	private void createPermission(String prefix) {
		List<Permission> permissions = this.permissionRep.findPermissionByNameStartingWith(prefix);
		 if (permissions.isEmpty()) {
			 permissions.add(new Permission(prefix + ":read","Chức năng đọc"));
			 permissions.add(new Permission(prefix + ":create","Chức năng tạo mới"));
			 permissions.add(new Permission(prefix + ":update","Chức năng cập nhật"));
			 permissions.add(new Permission(prefix + ":delete","Chức năng xóa"));
			 permissions.add(new Permission(prefix + ":upload","Chức năng tải lên"));
			 permissions.add(new Permission(prefix + ":download","Chức năng tải xuống"));
			 permissions.add(new Permission(prefix + ":report","Chức năng báo cáo"));
			 try {
				 permissionRep.saveAll(permissions);
			 } catch (Exception e) {
				 System.out.println("PermissionInit.java failed");
			 }
		 }
	}
	
	public void init(List<String> features){
//		"admin", "user", "customer", "product", "payment-type"
		features.forEach(t -> createPermission(t));
	}
}
