package com.example.imagesharing.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String email;
}
