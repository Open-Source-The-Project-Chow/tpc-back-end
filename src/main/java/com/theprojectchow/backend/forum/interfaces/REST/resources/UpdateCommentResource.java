package com.theprojectchow.backend.forum.interfaces.REST.resources;

import com.theprojectchow.backend.forum.domain.model.aggregates.Post;

public record UpdateCommentResource(String author, String content, Long postId) {
}
