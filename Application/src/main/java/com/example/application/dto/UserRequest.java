package com.example.application.dto;

import jakarta.validation.constraints.*;

public class UserRequest {

    @NotBlank(message = "Fullname không được để trống")
    private String fullname;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng format")
    private String email;

    private String phone;

    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}