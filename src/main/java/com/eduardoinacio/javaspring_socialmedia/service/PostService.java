package com.eduardoinacio.javaspring_socialmedia.service;

import com.eduardoinacio.javaspring_socialmedia.controller.dto.post.FeedPosts;
import com.eduardoinacio.javaspring_socialmedia.controller.dto.post.FeedResponse;
import com.eduardoinacio.javaspring_socialmedia.entity.Post;
import com.eduardoinacio.javaspring_socialmedia.repository.PostRepository;
import com.eduardoinacio.javaspring_socialmedia.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class PostService {
    private PostRepository postRepository;
    private UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public FeedResponse getFeed(int page, int size, String sortField){
        Page<FeedPosts> pageOfFeedPosts = postRepository.findAll(PageRequest.of(page, size, Sort.by(sortField)))
                .map(post -> new FeedPosts(
                        post.getPostId(),
                        post.getContent(),
                        post.getUser().getName()
                ));

        return new FeedResponse(
                pageOfFeedPosts.getContent(),
                pageOfFeedPosts.getTotalPages(),
                pageOfFeedPosts.getTotalElements(),
                pageOfFeedPosts.getSize(),
                page,
                pageOfFeedPosts.isFirst(),
                pageOfFeedPosts.isLast()
        );
    }

    public Boolean createPost(String content, UUID userId){
        var user = userRepository.findById(userId);
        if(user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        var post = new Post();
        post.setUser(user.get());
        post.setContent(content);
        postRepository.save(post);
        return true;
    }

    public void deletePost(Long postId, UUID userId, boolean userIsAdmin){
        var post = postRepository.findById(postId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found")
        );

        if(post.getUser().getId().equals(userId) || userIsAdmin){
            postRepository.delete(post);
        }else{
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }
}
