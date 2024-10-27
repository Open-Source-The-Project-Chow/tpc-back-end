package com.theprojectchow.backend.forum.domain.services;

import com.theprojectchow.backend.forum.domain.model.aggregates.Post;
import com.theprojectchow.backend.forum.domain.model.commands.CreatePostCommand;
import com.theprojectchow.backend.forum.domain.model.commands.DeletePostCommand;
import com.theprojectchow.backend.forum.domain.model.commands.UpdatePostCommand;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface PostCommandService {
    Long handle(CreatePostCommand command);
    Optional<Post> handle(UpdatePostCommand command);
    void handle(DeletePostCommand command);
}
