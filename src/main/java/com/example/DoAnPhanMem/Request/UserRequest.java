package com.example.DoAnPhanMem.Request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.context.annotation.Bean;

public class UserRequest {

    @NotBlank(message = "UserName not empty or exist space")
    @Size(min = 5, max = 20, message = "UserName Size from 5 to 20 characters")
    private String userName;
    @NotBlank(message = "Password not empty or exist space")
    @Size(min = 8, max = 32, message = "Password Size from 8 to 32 characters")
    private String password;
    private String role;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
}
