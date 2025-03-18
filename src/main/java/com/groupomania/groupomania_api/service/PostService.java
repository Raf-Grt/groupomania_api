package com.groupomania.groupomania_api.service;

import com.groupomania.groupomania_api.model.entity.Post;
import com.groupomania.groupomania_api.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
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
