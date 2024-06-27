package com.model;

import java.io.Serializable;

public class Profile implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String email;
    private String password;
    // Add any additional fields as needed

    public Profile() {
        // Default constructor
    }

    // Constructor with all fields
    public Profile(String username, String email, String password) {
        this.username = username;
        this.password = password;
        this.email = email;
        // Initialize other fields if needed
    }

    // Getters and setters for all fields

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
