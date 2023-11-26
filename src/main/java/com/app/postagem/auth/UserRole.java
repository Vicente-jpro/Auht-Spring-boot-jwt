package com.app.postagem.auth;

public enum UserRole {
    USER("User");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
