package com.eduardoinacio.javaspring_socialmedia.controller;

import com.eduardoinacio.javaspring_socialmedia.controller.dto.Auth.LoginRequest;
import com.eduardoinacio.javaspring_socialmedia.controller.dto.Auth.LoginResponse;
import com.eduardoinacio.javaspring_socialmedia.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private JwtEncoder jwtEncoder;
    private AuthService authService;

    public AuthController(JwtEncoder jwtEncoder, AuthService authService){
        this.jwtEncoder = jwtEncoder;
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        LoginResponse response = authService.login(request.email(), request.password());
        return ResponseEntity.ok(response);
    }
}
