package com.example.contabliumv2.Dto;

import com.example.contabliumv2.Model.UserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class UserDto {

    private String username;
    private String password;
    private String fullname;
    @Enumerated(EnumType.STRING)
    private UserRole role;


    public UserDto() {
    }

    public UserDto(String username, String password, String fullname, UserRole role) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.role = role;
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
