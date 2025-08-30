package com.eduardoinacio.javaspring_socialmedia.controller.dto.Auth;

public record LoginResponse(String accessToken, Long expiresIn) {
}
