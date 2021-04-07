package com.example.imagesharing.payload;

import lombok.Value;

@Value
public class JwtAuthenticationResponse {

    String tokenType = "Bearer";
    String accessToken;

}
