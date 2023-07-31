package vn.com.ecotechgroup.erp.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PermissionDefination {
	
	ADMIN_READ("admin:read"),
	ADMIN_UPDATE("admin:update"),
	ADMIN_CREATE("admin:create"),
	ADMIN_DELETE("admin:update"),
	
	MANAGER_READ("manager:read"),
	MANAGER_UPDATE("manager:update"),
	MANAGER_CREATE("manager:create"), 
	MANAGER_DELETE("manager:update")
	
	;
	
	@Getter
	private final String permission;
}
