package com.theprojectchow.backend.forum.domain.model.commands;

public record CreatePostCommand(String title, String content, String image) {
}
