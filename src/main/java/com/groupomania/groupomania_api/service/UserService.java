package com.groupomania.groupomania_api.service;

import com.groupomania.groupomania_api.model.dto.RegisterUserDto;
import com.groupomania.groupomania_api.model.entity.Role;
import com.groupomania.groupomania_api.model.entity.RoleEnum;
import com.groupomania.groupomania_api.model.entity.User;
import com.groupomania.groupomania_api.repository.RoleRepository;
import com.groupomania.groupomania_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);

        return users;
    }

    public User createAdministrator(RegisterUserDto input) {
        Role roleAdmin = roleRepository.findByName(RoleEnum.ADMIN)
                .orElseThrow(() -> new RuntimeException("Admin role not found"));

        User user = User.builder()
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()))
                .role(roleAdmin)
                .build();

        return userRepository.save(user);
    }
}
