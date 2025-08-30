package com.eduardoinacio.javaspring_socialmedia.controller;

import com.eduardoinacio.javaspring_socialmedia.controller.dto.login.LoginRequest;
import com.eduardoinacio.javaspring_socialmedia.controller.dto.login.LoginResponse;
import com.eduardoinacio.javaspring_socialmedia.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private JwtEncoder jwtEncoder;
    private LoginService loginService;

    public LoginController(JwtEncoder jwtEncoder, LoginService loginService){
        this.jwtEncoder = jwtEncoder;
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        LoginResponse response = loginService.login(request.email(), request.password());
        return ResponseEntity.ok(response);
    }
}
