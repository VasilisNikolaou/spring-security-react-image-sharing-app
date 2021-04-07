package com.example.imagesharing.payload;

import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
public class LoginRequest {

    @NotBlank(message = "username or email cannot be null or empty")
    String usernameOrEmail;

    @NotBlank(message = "password cannot be null or empty")
    String password;
}
