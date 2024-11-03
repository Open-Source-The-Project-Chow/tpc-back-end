package com.theprojectchow.backend.forum.application.queryservices;

import com.theprojectchow.backend.forum.domain.model.aggregates.Post;
import com.theprojectchow.backend.forum.domain.model.queries.GetAllPostsQuery;
import com.theprojectchow.backend.forum.domain.model.queries.GetPostByIdQuery;
import com.theprojectchow.backend.forum.domain.services.PostQueryService;
import com.theprojectchow.backend.forum.infrastructure.persistence.jpa.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PostQueryServiceImpl implements PostQueryService {

    private final PostRepository postRepository;

    public PostQueryServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Optional<Post> handle(GetPostByIdQuery query) {
        return postRepository.findById(query.postId());
    }

    @Override
    public List<Post> handle(GetAllPostsQuery query) {
        return  postRepository.findAll();
    }
}
