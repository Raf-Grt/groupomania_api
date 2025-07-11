package com.groupomania.groupomania_api.repository;

import com.groupomania.groupomania_api.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
