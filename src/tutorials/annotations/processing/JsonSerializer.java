package tutorials.annotations.processing;

import tutorials.annotations.json.JsonIgnore;
import tutorials.annotations.json.JsonProperty;
import tutorials.annotations.json.JsonSerializable;

import java.lang.reflect.Field;
import java.util.StringJoiner;

public class JsonSerializer {
    public static String serialize(Object obj) throws IllegalAccessException {
        if (obj == null) {
            throw new IllegalArgumentException("Serialization is not possible: object is null");
        }
        if (!obj.getClass().isAnnotationPresent(JsonSerializable.class)) {
            throw new IllegalArgumentException("Serialization is not possible: class "
                    + obj.getClass().getSimpleName() + " doesn't have an annotation @JsonSerializable");
        }

        StringJoiner jsonElements = new StringJoiner(",");

        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            if (field.isAnnotationPresent(JsonIgnore.class)) {
                continue;
            }

            String key = field.isAnnotationPresent(JsonProperty.class)
                    ? field.getAnnotation(JsonProperty.class).value()
                    : field.getName();

            Object value = field.get(obj);
            String formatted = (value instanceof String)
                    ? "\"" + value + "\""
                    : String.valueOf(value);

            jsonElements.add("\"" + key + "\":" + formatted);
        }

        return "{" + jsonElements.toString() + "}";
    }
}
