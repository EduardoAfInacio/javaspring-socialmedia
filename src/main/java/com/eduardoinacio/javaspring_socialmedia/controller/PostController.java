package com.eduardoinacio.javaspring_socialmedia.controller;

import com.eduardoinacio.javaspring_socialmedia.controller.dto.post.PostCreateRequest;
import com.eduardoinacio.javaspring_socialmedia.service.PostService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts")
    public ResponseEntity<Void> createPost(@RequestBody PostCreateRequest request, JwtAuthenticationToken token){
        UUID userId = UUID.fromString(token.getName());
        postService.createPost(request.content(), userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") Long postId, JwtAuthenticationToken token) {
        UUID userId = UUID.fromString(token.getName());
        boolean userIsAdmin = token.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        postService.deletePost(postId, userId, userIsAdmin);
        return ResponseEntity.ok().build();
    }
}
