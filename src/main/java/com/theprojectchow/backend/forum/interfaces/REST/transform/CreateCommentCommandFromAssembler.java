package com.theprojectchow.backend.forum.interfaces.REST.transform;

import com.theprojectchow.backend.forum.domain.model.commands.CreateCommentCommand;
import com.theprojectchow.backend.forum.interfaces.REST.resources.CreateCommandResource;

public class CreateCommentCommandFromAssembler {
    public static CreateCommentCommand toCommandFromResource(CreateCommandResource resource) {
        return new CreateCommentCommand(resource.author(), resource.content(), resource.postId());
    }
}
