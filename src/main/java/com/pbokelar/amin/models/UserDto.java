package com.pbokelar.amin.models;

import jakarta.validation.constraints.NotEmpty;

public class UserDto {
    
    @NotEmpty(message="Username is required") private String username;
    @NotEmpty(message = "Password is required") private String password;
    private String role;
    private String name;
    private String phone_number;
    private String address;
    private int loggedin;
    
    
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
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone_number() {
        return phone_number;
    }
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getLoggedin() {
        return loggedin;
    }
    public void setLoggedin(int loggedin) {
        this.loggedin = loggedin;
    }

    
}
