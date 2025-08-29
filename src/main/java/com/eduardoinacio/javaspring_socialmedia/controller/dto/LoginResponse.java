package com.eduardoinacio.javaspring_socialmedia.controller.dto;

public record LoginResponse(String accessToken, Long expiresIn) {
}
