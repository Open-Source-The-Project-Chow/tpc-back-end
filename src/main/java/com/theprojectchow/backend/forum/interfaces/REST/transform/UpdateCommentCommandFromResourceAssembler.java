package com.theprojectchow.backend.forum.interfaces.REST.transform;

import com.theprojectchow.backend.forum.domain.model.commands.UpdateCommentCommand;
import com.theprojectchow.backend.forum.interfaces.REST.resources.UpdateCommentResource;

public class UpdateCommentCommandFromResourceAssembler {
    public static UpdateCommentCommand toCommandFromResource(Long id,UpdateCommentResource resource) {
        return new UpdateCommentCommand(id ,resource.author(), resource.content(), resource.postId());
    }
}
