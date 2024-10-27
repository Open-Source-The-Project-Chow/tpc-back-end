package com.theprojectchow.backend.forum.interfaces.REST.transform;

import com.theprojectchow.backend.forum.domain.model.commands.UpdatePostCommand;
import com.theprojectchow.backend.forum.interfaces.REST.resources.UpdatePostResource;

public class UpdatePostCommandFromResourceAssembler {
    public static UpdatePostCommand toCommandFromResource(Long  postId, UpdatePostResource resource) {
        return new UpdatePostCommand(postId, resource.content(), resource.image());
    }
}
