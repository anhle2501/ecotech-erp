package vn.com.ecotechgroup.erp.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import vn.com.ecotechgroup.erp.entity.User;

public class AuditorAwareImp implements AuditorAware<User> {

	@Override
	public Optional<User> getCurrentAuditor() {

		return Optional.ofNullable(SecurityContextHolder.getContext())
				.map(SecurityContext::getAuthentication)
				.filter(Authentication::isAuthenticated)
				.map(Authentication::getPrincipal).map(User.class::cast);
	}

}
