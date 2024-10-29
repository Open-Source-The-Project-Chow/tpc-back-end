package com.theprojectchow.backend.forum.domain.model.commands;


public record CreateCommentCommand( String author, String content, Long postId) {
}
