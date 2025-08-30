package com.eduardoinacio.javaspring_socialmedia.service;

import com.eduardoinacio.javaspring_socialmedia.entity.User;
import com.eduardoinacio.javaspring_socialmedia.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> findAll(){
        return userRepository.findAll();
    }
}
