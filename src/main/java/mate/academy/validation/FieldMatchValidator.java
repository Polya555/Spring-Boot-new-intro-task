package mate.academy.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
    private String firstFieldName;

    private String secondFieldName;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        this.firstFieldName = constraintAnnotation.first();
        this.secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        Field firstField = null;
        try {
            firstField = value.getClass().getDeclaredField(firstFieldName);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        firstField.setAccessible(true);
        Object firstValue = null;
        try {
            firstValue = firstField.get(value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        Field secondField = null;
        try {
            secondField = value.getClass().getDeclaredField(secondFieldName);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        secondField.setAccessible(true);
        Object secondValue = null;
        try {
            secondValue = secondField.get(value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return firstValue != null ? firstValue.equals(secondValue) : secondValue == null;
    }
}
