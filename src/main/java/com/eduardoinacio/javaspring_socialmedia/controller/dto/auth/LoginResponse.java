package com.eduardoinacio.javaspring_socialmedia.controller.dto.auth;

public record LoginResponse(String accessToken, Long expiresIn) {
}
