package com.groupomania.groupomania_api.repository;

import com.groupomania.groupomania_api.model.entity.Role;
import com.groupomania.groupomania_api.model.entity.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleEnum roleEnum);
}
