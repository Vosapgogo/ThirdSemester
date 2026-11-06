package tutorials.annotations.processing;

import tutorials.annotations.validation.NotBlank;
import tutorials.annotations.validation.NotNull;
import tutorials.annotations.validation.Range;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class Validator {

    public static void validate(Object obj) throws ValidationException, IllegalAccessException {
        Class<?> clazz = obj.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(obj);
            String name = field.getName();

            for (Annotation annotation : field.getAnnotations()) {
                switch (annotation) {
                    case NotNull n -> {
                        if (value == null)
                            throw new ValidationException("Field '" + name + "' must not be null");
                    }
                    case NotBlank b -> {
                        if (!(value instanceof String s) || s.trim().isEmpty())
                            throw new ValidationException("Field '" + name + "' must not be blank");
                    }
                    case Range r -> {
                        if (!(value instanceof Number num))
                            throw new ValidationException("Field '" + name + "' must be a number");
                        long number = num.longValue();
                        if (number < r.min() || number > r.max())
                            throw new ValidationException("Field '" + name + "' must be between " + r.min() + " and " + r.max());
                    }
                    default -> { }
                }
            }
        }
    }
}