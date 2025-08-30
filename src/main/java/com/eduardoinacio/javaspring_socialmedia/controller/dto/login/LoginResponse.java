package com.eduardoinacio.javaspring_socialmedia.controller.dto.login;

public record LoginResponse(String accessToken, Long expiresIn) {
}
