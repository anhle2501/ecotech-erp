package vn.com.ecotechgroup.erp.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import vn.com.ecotechgroup.erp.service.UserService;


public class NoDuplicateConstraintValidator<NoDuplicate> implements ConstraintValidator<NoDuplicate, String> {

	private String value;
	
	public void initialize(NoDuplicate text) {
		value = (String) text;
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
	
		return false;
	}

}

