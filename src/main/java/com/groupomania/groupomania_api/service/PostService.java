package com.groupomania.groupomania_api.service;

import com.groupomania.groupomania_api.model.entity.Post;
import com.groupomania.groupomania_api.model.entity.User;
import com.groupomania.groupomania_api.repository.PostRepository;
import com.groupomania.groupomania_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public List<Post> getUserPosts(String email) {
        User user = getUser(email);

        return postRepository.findByUserIdOrOrderByCreatedAt(user.getId());
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post createPost(Post createdPost, String email) {
        User user = getUser(email);

        Post post = Post.builder()
                .user(user)
                .content(createdPost.getContent())
                .image_url(createdPost.getImage_url())
                .build();
        return postRepository.save(post);
    }

    private User getUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user;
    }

    public Post updatePost(Post updatedPost, Long userId) {
        Post post = postRepository.findById(updatedPost.getId())
                .orElseThrow(() -> new RuntimeException("Post not found"));

        if (!post.getUser().getId().equals(userId)) {
            throw new RuntimeException("You can not update this post");
        }

        post.setContent(updatedPost.getContent());
        post.setImage_url(updatedPost.getImage_url());
        return postRepository.save(post);
    }

    public void deletePost(Post deletedPost, Long userId) {
        Post post = postRepository.findById(deletedPost.getId())
                .orElseThrow(() -> new RuntimeException("Post not found"));

        if (!post.getUser().getId().equals(userId)) {
            throw new RuntimeException("You can not delete this post");
        }

        postRepository.delete(post);
    }
}
