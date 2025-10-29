package com.groupomania.groupomania_api.controller;

import com.groupomania.groupomania_api.model.dto.LoginRequest;
import com.groupomania.groupomania_api.model.dto.RegisterRequest;
import com.groupomania.groupomania_api.model.entity.User;
import com.groupomania.groupomania_api.model.dto.LoginResponse;
import com.groupomania.groupomania_api.service.AuthenticationService;
import com.groupomania.groupomania_api.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterRequest registerRequest) {
        User registeredUser = authenticationService.signup(registerRequest);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginRequest loginRequest) {
        User authenticatedUser = authenticationService.authenticate(loginRequest);

        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = LoginResponse.builder()
                .token(jwtToken)
                .expiresIn(jwtService.getExpirationTime())
                .build();

        return ResponseEntity.ok(loginResponse);
    }
}
