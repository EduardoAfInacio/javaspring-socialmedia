package com.eduardoinacio.javaspring_socialmedia.controller.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(@NotBlank String email,@NotBlank String password) {
}
