package com.groupomania.groupomania_api.controller;

import com.groupomania.groupomania_api.model.entity.Post;
import com.groupomania.groupomania_api.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/post")
@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Post> createPost(@RequestBody Post post, @AuthenticationPrincipal AuthenticatedPrincipal principal) {
        Post createdPost = postService.createPost(post, principal.getName().toString());
        return ResponseEntity.ok(createdPost);
    }
}
