package com.theprojectchow.backend.forum.domain.model.commands;

public record UpdatePostCommand(Long id, String content, String image) {
}
