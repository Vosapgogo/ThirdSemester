package tutorials.annotations.processing;

public class ObjectProcessor {
    public String process(Object obj) {
        try {
            System.out.println("Validation started...");
            Validator.validate(obj);
            System.out.println("Validation successful.");
            return "";

        } catch (ValidationException e) {
            System.err.println("Validation error: " + e.getMessage());
            return "{\"status\":\"error\",\"message\":\"" + e.getMessage() + "\"}";

        } catch (IllegalAccessException e) {
            System.err.println("Internal error: " + e.getMessage());
            return "{\"status\":\"error\",\"message\":\"Internal server error\"}";
        }
    }


}
