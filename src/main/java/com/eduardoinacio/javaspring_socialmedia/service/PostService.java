package com.eduardoinacio.javaspring_socialmedia.service;

import com.eduardoinacio.javaspring_socialmedia.entity.Post;
import com.eduardoinacio.javaspring_socialmedia.repository.PostRepository;
import com.eduardoinacio.javaspring_socialmedia.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PostService {
    private PostRepository postRepository;
    private UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Boolean createPost(String content, UUID userId){
        var user = userRepository.findById(userId);
        if(user.isEmpty()){
            throw new IllegalArgumentException("User not found");
        }
        var post = new Post();
        post.setUser(user.get());
        post.setContent(content);
        postRepository.save(post);
        return true;
    }
}
