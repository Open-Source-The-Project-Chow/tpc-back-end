package com.theprojectchow.backend.forum.domain.services;

import com.theprojectchow.backend.forum.domain.model.aggregates.Comment;
import com.theprojectchow.backend.forum.domain.model.queries.GetAllCommentQuery;
import com.theprojectchow.backend.forum.domain.model.queries.GetCommentByIdQuery;
import com.theprojectchow.backend.forum.domain.model.queries.GetCommentsByPostIdQuery;

import java.util.List;
import java.util.Optional;

public interface CommentQueryService {
    Optional<Comment> handle(GetCommentByIdQuery query);
    List<Comment> handle(GetAllCommentQuery query);
    List<Comment> handle(GetCommentsByPostIdQuery query);
}
