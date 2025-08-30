package com.eduardoinacio.javaspring_socialmedia.controller.dto.post;

import java.util.List;

public record FeedResponse(List<FeedPosts> feedPostsList, int totalPages, long totalElements, int pageSize,  int page, boolean firstPage, boolean lastPage) {
}
