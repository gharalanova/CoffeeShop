package com.softuni.coffeesshop.model.binding;

import jakarta.validation.constraints.Size;

public class LoginBindingModel {

    @Size(min = 5, max = 20)
    private String username;

    @Size(min = 3)
    private String password;

    public LoginBindingModel() {
    }

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
}
