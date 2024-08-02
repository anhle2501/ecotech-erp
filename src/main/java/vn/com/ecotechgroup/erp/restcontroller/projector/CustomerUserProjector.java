package vn.com.ecotechgroup.erp.restcontroller.projector;

import java.time.LocalDateTime;

import vn.com.ecotechgroup.erp.entity.User;

public interface CustomerUserProjector {

	Long getId();

	String getAddress();

	String getCode();

	User getCreatedBy();

	LocalDateTime getCreatedDate();

	String getDescription();

	LocalDateTime getLastModifiedDate();

	String getName();

	String getPhone();

	String getTaxCode();

}
