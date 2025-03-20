package javaassignment2.models;

public class User {
    private String username;
    private String password;
    private String role;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getter Methods (Fix Missing Methods)
    public String getUsername() {
        return username;
    }

    public String getPassword() { // Fix for UserDAO
        return password;
    }

    public String getRole() {
        return role;
    }
}
