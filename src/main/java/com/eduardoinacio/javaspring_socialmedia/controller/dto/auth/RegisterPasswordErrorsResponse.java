package com.eduardoinacio.javaspring_socialmedia.controller.dto.auth;

import java.util.List;

public record RegisterPasswordErrorsResponse(List<String> errors) {
}
