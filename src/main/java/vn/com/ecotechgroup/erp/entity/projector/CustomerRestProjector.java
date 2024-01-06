
package vn.com.ecotechgroup.erp.entity.projector;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import vn.com.ecotechgroup.erp.entity.Customer;
import vn.com.ecotechgroup.erp.entity.User;

@Projection(name = "CustomerRestProjector", types = { Customer.class,
		User.class })
public interface CustomerRestProjector {

	long getId();

	String getCode();

	String getName();

	String getAddress();

	String getPhone();

	String getTaxCode();

	LocalDateTime getCreateAt();

	LocalDateTime getLastModifiedDate();

	@Value("#{target.user}")
	User getUser();

	User getUserModified();

	String getDescription();
}
