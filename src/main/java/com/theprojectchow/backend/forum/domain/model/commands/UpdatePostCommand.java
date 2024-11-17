package com.theprojectchow.backend.forum.domain.model.commands;

public record UpdatePostCommand(Long id, String title, String content, String image) {
}
