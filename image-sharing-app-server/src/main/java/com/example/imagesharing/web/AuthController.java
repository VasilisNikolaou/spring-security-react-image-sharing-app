package com.example.imagesharing.web;

import com.example.imagesharing.model.User;
import com.example.imagesharing.payload.ApiResponse;
import com.example.imagesharing.payload.SignUpRequest;
import com.example.imagesharing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping(value = "/signup",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> registerUser(@RequestBody SignUpRequest signUpRequest) {
         userService.saveUser(signUpRequest);

         ApiResponse response = new ApiResponse("Successful Registration");

         return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
