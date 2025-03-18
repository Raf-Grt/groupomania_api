package com.groupomania.groupomania_api.controller;

import com.groupomania.groupomania_api.model.dto.RegisterUserDto;
import com.groupomania.groupomania_api.model.entity.User;
import com.groupomania.groupomania_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admins")
@RestController
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;

    @PostMapping
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<User> createAdministrator(@RequestBody RegisterUserDto userDto) {
        User createdAdmin = userService.createAdministrator(userDto);
        return ResponseEntity.ok(createdAdmin);
    }
}
