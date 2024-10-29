package com.theprojectchow.backend.forum.domain.services;

import com.theprojectchow.backend.forum.domain.model.aggregates.Comment;
import com.theprojectchow.backend.forum.domain.model.commands.CreateCommentCommand;
import com.theprojectchow.backend.forum.domain.model.commands.DeleteCommentCommand;
import com.theprojectchow.backend.forum.domain.model.commands.UpdateCommentCommand;

import java.util.Optional;

public interface CommentCommandService {
    Long handle(CreateCommentCommand command);
    Optional<Comment> handle(UpdateCommentCommand command);
    void handle(DeleteCommentCommand command);
}
