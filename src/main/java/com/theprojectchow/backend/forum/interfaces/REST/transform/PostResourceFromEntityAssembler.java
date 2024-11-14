package com.theprojectchow.backend.forum.interfaces.REST.transform;

import com.theprojectchow.backend.forum.domain.model.aggregates.Post;
import com.theprojectchow.backend.forum.interfaces.REST.resources.PostResource;

public class PostResourceFromEntityAssembler {
        public static PostResource toResourceFromEntity(Post post) {
                return new PostResource(post.getId(),post.getTitle(), post.getContent(), post.getImage());
        }
}
