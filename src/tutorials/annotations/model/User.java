package tutorials.annotations.model;

import tutorials.annotations.validation.NotBlank;
import tutorials.annotations.validation.NotNull;
import tutorials.annotations.validation.Range;

public class User {
    @Range(min = 1)
    private final long id;

    @NotBlank
    private String username;

    @NotNull
    private String email;

    @Range(min=18, max=100)
    private int age;

    private String password;

    public User(long id, String username, String email, int age, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.age = age;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String getPassword() {
        return password;
    }
}
