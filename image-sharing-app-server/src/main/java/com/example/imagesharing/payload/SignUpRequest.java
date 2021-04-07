package com.example.imagesharing.payload;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Value
public class SignUpRequest {

    @NotBlank(message = "username cannot be null or empty")
    @Size(min = 4, max = 10, message = "username must be between 4 and 10 characters")
    String username;

    @NotBlank(message = "password cannot be null or empty")
    @Size(min = 7, max = 25, message = "password must be between 7 and 25 characters")
    String password;

    @NotBlank(message = "first name cannot be null or empty")
    @Size(min = 5, max = 15, message = "first name must be between 5 and 15 characters")
    String firstName;

    @NotBlank(message = "last name cannot be null or empty")
    @Size(min = 5, max = 15, message = "last name must be between 5 and 15 characters")
    String lastName;

    @NotBlank(message = "email cannot be null or empty")
    @Size(min = 9, max = 30, message = "email must be between 9 and 30 characters")
    String email;
}
