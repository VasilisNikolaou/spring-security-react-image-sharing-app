package com.example.imagesharing.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    private String usernameOrEmail;
    private String password;
}
