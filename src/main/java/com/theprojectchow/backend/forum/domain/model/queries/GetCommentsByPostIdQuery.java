package com.theprojectchow.backend.forum.domain.model.queries;

import com.theprojectchow.backend.forum.domain.model.aggregates.Post;

public record GetCommentsByPostIdQuery(Long postId) {
}
