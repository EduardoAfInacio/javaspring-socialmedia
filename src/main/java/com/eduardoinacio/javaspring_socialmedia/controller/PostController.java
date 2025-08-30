package com.eduardoinacio.javaspring_socialmedia.controller;

import com.eduardoinacio.javaspring_socialmedia.controller.dto.post.PostCreateRequest;
import com.eduardoinacio.javaspring_socialmedia.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
