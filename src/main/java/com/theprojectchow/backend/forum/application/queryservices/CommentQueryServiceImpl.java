package com.theprojectchow.backend.forum.application.queryservices;

import com.theprojectchow.backend.forum.domain.model.aggregates.Comment;
import com.theprojectchow.backend.forum.domain.model.queries.GetAllCommentQuery;
import com.theprojectchow.backend.forum.domain.model.queries.GetCommentByIdQuery;
import com.theprojectchow.backend.forum.domain.model.queries.GetCommentsByPostIdQuery;
import com.theprojectchow.backend.forum.domain.services.CommentQueryService;
import com.theprojectchow.backend.forum.infrastructure.persistence.jpa.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentQueryServiceImpl implements CommentQueryService {

    private final CommentRepository commentRepository;

    public CommentQueryServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Optional<Comment> handle(GetCommentByIdQuery query) {
        return commentRepository.findById(query.commentId());
    }

    @Override
    public List<Comment> handle(GetAllCommentQuery query) {
        return commentRepository.findAll();
    }

    @Override
    public List<Comment> handle(GetCommentsByPostIdQuery query) {
        return commentRepository.findByPostId(query.postId());
    }
}