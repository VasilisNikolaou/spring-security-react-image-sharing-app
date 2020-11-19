package com.example.imagesharing.service;

import com.example.imagesharing.model.User;
import com.example.imagesharing.payload.SignUpRequest;
import com.example.imagesharing.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public void saveUser(SignUpRequest signUpRequest) {
        userRepo.save(convertDtoToEntity(signUpRequest));
    }

    private User convertDtoToEntity(SignUpRequest signUpRequest) {
        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        return new User(signUpRequest.getUsername(),
                encodedPassword,
                signUpRequest.getFirstName(),
                signUpRequest.getLastName(),
                signUpRequest.getEmail());
    }


}
