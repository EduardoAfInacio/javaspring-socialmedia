package com.eduardoinacio.javaspring_socialmedia.controller.dto.auth;

import com.eduardoinacio.javaspring_socialmedia.controller.dto.auth.Annotations.StrongPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRegisterRequest(@NotBlank String name, @NotBlank @Email String email, @NotBlank @StrongPassword String password) {
}
