package com.eduardoinacio.javaspring_socialmedia.controller;

import com.eduardoinacio.javaspring_socialmedia.controller.dto.auth.LoginRequest;
import com.eduardoinacio.javaspring_socialmedia.controller.dto.auth.LoginResponse;
import com.eduardoinacio.javaspring_socialmedia.controller.dto.auth.UserRegisterRequest;
import com.eduardoinacio.javaspring_socialmedia.service.AuthService;
import jakarta.validation.Valid;
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
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request){
        LoginResponse response = authService.login(request.email(), request.password());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid UserRegisterRequest request){
        authService.register(request.document() ,request.name(), request.email(), request.password());
        return ResponseEntity.ok().build();
    }
}
