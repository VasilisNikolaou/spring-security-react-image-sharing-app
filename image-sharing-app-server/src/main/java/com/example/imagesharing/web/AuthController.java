package com.example.imagesharing.web;

import com.example.imagesharing.payload.ApiResponse;
import com.example.imagesharing.payload.JwtAuthenticationResponse;
import com.example.imagesharing.payload.LoginRequest;
import com.example.imagesharing.payload.SignUpRequest;
import com.example.imagesharing.security.JwtTokenProvider;
import com.example.imagesharing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping(value = "/signin",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@RequestBody final LoginRequest loginRequest) {
        final UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword());

        final Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwtTokenProvider.generateToken(authentication)));
    }

    @PostMapping(value = "/signup",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> registerUser(@RequestBody final SignUpRequest signUpRequest) {
         userService.saveUser(signUpRequest);

         ApiResponse response = new ApiResponse("Successful Registration");

         return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
