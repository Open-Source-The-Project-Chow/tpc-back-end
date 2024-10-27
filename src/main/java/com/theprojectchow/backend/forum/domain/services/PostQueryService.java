package com.theprojectchow.backend.forum.domain.services;

import com.theprojectchow.backend.forum.domain.model.aggregates.Post;
import com.theprojectchow.backend.forum.domain.model.queries.GetAllPostsQuery;
import com.theprojectchow.backend.forum.domain.model.queries.GetPostByIdQuery;

import java.util.List;
import java.util.Optional;

public interface PostQueryService {
    Optional<Post> handle(GetPostByIdQuery query);
    List<Post> handle(GetAllPostsQuery query);
}
