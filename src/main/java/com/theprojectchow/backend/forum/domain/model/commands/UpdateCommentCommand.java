package com.theprojectchow.backend.forum.domain.model.commands;

import com.theprojectchow.backend.forum.domain.model.aggregates.Post;

public record UpdateCommentCommand(Long id, String author, String content, Long postId) {
}
