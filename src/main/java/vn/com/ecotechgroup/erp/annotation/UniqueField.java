package vn.com.ecotechgroup.erp.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {UniqueFieldValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueField {
    String message() default "Dữ liệu bị trùng !";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    //specify the repository class
    Class<?> repository();
    //optional: specify the field in repository to check
    String fieldName() default "" ;

}
