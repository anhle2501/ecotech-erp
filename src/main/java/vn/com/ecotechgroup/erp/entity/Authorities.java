//package vn.com.ecotechgroup.erp.entity;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToMany;
//import jakarta.persistence.Table;
//import lombok.Data;
//
////@Data
////@Entity
////@Table(name = "authorities")
////public class Authorities {
////	
////	@ManyToMany
////	@JoinColumn(
////			name = "authorities", 
////	        joinColumns = @JoinColumn(
////	          name = "role_id", referencedColumnName = "id"), 
////	        inverseJoinColumns = @JoinColumn(
////	          name = "privilege_id", referencedColumnName = "id")
////			)
////	private User userDetails;
////	
////	private Role role;
//	
////	@ToString.Exclude
////	@OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE})
////	@JoinColumn(name = "user_id_au")
////	User userDetails;
//}