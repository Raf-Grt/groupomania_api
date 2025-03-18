package com.groupomania.groupomania_api.service;

import com.groupomania.groupomania_api.model.dto.LoginUserDto;
import com.groupomania.groupomania_api.model.dto.RegisterUserDto;
import com.groupomania.groupomania_api.model.entity.Role;
import com.groupomania.groupomania_api.model.entity.RoleEnum;
import com.groupomania.groupomania_api.model.entity.User;
import com.groupomania.groupomania_api.repository.RoleRepository;
import com.groupomania.groupomania_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    public User signup(RegisterUserDto input) {
        Role roleUser = roleRepository.findByName(RoleEnum.USER)
                .orElseThrow(() -> new RuntimeException("User role not found"));

        User user = User.builder()
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()))
                .role(roleUser)
                .build();

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}
