package com.theprojectchow.backend.forum.interfaces.REST.transform;

import com.theprojectchow.backend.forum.domain.model.commands.CreatePostCommand;
import com.theprojectchow.backend.forum.interfaces.REST.resources.CreatePostResource;

public class CreatePostCommandFromAssembler {
        public static CreatePostCommand toCommandFromResource(CreatePostResource resource) {
                return new CreatePostCommand(resource.title() ,resource.content(), resource.image());
        }
}
