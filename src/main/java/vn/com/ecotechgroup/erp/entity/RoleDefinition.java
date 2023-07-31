//package vn.com.ecotechgroup.erp.entity;
//
//import java.util.Collections;
//import java.util.List;
//
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//
//@RequiredArgsConstructor
//public enum RoleDefinition {
//	
//	USER(Collections.emptySet()),
//	ADMIN(List.of(
//			ADMIN_READ,
//			ADMIN_UPDATE,
//			ADMIN_DELETE,
//			ADMIN_CREATE,
//			MANAGER_READ,
//			MANAGER_UPDATE,
//			MANAGER_DELETE,
//			MANAGER_CREATE
//			)),
//	
//	MANAGER(List.of(
//			MANAGER_READ,
//			MANAGER_UPDATE,
//			MANAGER_DELETE,
//			MANAGER_CREATE
//			
//			))
//	
//	;
//	@Getter
//	private final List<Permission> permissions;
//	
//	public List<SimpleGrantedAuthority> getAuthorities(){
//		List<SimpleGrantedAuthority> authorities = getPermissions().stream()
//				.map(permission -> new SimpleGrantedAuthority(permission.name()))
//				.toList();
//	}
//}
