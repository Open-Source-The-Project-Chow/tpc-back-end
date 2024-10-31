package com.theprojectchow.backend.forum.interfaces.REST.transform;

import com.theprojectchow.backend.forum.domain.model.aggregates.Comment;
import com.theprojectchow.backend.forum.interfaces.REST.resources.CommentResource;

public class CommentResourceFromEntityAssembler {
    public static CommentResource toResourceFromEntity(Comment comment) {
        return new CommentResource(comment.getId(), comment.getAuthor(), comment.getContent(), comment.getPost().getId());
    }
}
