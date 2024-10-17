package vn.com.ecotechgroup.erp.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.aspectj.util.Reflection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

public class UniqueFieldValidator implements ConstraintValidator<UniqueField, String> {

    @Autowired
    private ApplicationContext applicationContext;

    private Class<?> repositoryClass;
    private String fieldName;

    @Override
    public void initialize(UniqueField uniqueField) {
        this.repositoryClass = uniqueField.repository();
        this.fieldName = uniqueField.fieldName();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true; // Null/empty validation is handled separately if needed
        }

        Object repository = applicationContext.getBean(repositoryClass);
        if (repository == null) {
            throw new IllegalArgumentException("Không tìm thấy bean: " + repositoryClass.getName());
        }

        try {
            // construc the expected method name existBy<FieldName>
            String methodName = "existsBy" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
            Method method = ReflectionUtils.findMethod(repositoryClass, methodName, String.class);

            if (method == null) {
                throw new IllegalArgumentException("Method not found: " + methodName);
            }
            Boolean exists = (Boolean) ReflectionUtils.invokeMethod(method, repository, value);
            if (exists == null) {
                throw new RuntimeException("Invocation of method returned null, which is unexpected: " + methodName);
            }

            return !exists;
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while validating uniqueness", e);
        }
    }
}
