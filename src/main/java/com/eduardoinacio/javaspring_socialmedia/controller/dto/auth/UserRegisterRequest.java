package com.eduardoinacio.javaspring_socialmedia.controller.dto.auth;

import com.eduardoinacio.javaspring_socialmedia.controller.dto.auth.Annotations.StrongPassword;
import com.eduardoinacio.javaspring_socialmedia.controller.dto.auth.Annotations.ValidCpfOrCnpj;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRegisterRequest(@NotBlank @ValidCpfOrCnpj String document, @NotBlank String name, @NotBlank @Email String email, @NotBlank @StrongPassword String password) {
}
