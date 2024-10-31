package com.theprojectchow.backend.forum.application.commandservices;

import com.theprojectchow.backend.forum.domain.model.aggregates.Comment;
import com.theprojectchow.backend.forum.domain.model.aggregates.Post;
import com.theprojectchow.backend.forum.domain.model.commands.CreateCommentCommand;
import com.theprojectchow.backend.forum.domain.model.commands.DeleteCommentCommand;
import com.theprojectchow.backend.forum.domain.model.commands.UpdateCommentCommand;
import com.theprojectchow.backend.forum.domain.services.CommentCommandService;
import com.theprojectchow.backend.forum.infrastructure.persistence.jpa.repositories.CommentRepository;
import com.theprojectchow.backend.forum.infrastructure.persistence.jpa.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentCommandServiceImpl implements CommentCommandService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentCommandServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public Long handle(CreateCommentCommand command) {
        if(commentRepository.existsByContent(command.content())) {
            throw new IllegalArgumentException("Comment with content already exists");
        }
        var post = postRepository.findById(command.postId())
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));
        var comment = new Comment(command.author(), command.content(), post);
        try {
            commentRepository.save(comment);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving comment: " + e.getMessage());
        }
        return comment.getId();
    }

    @Override
    public Optional<Comment> handle(UpdateCommentCommand command) {
        if (commentRepository.existsByContent(command.content())) {
            throw new IllegalArgumentException("Comment with content already exists");
        }
        var result = commentRepository.findById(command.id());
        if (result.isEmpty()) {
            throw new IllegalArgumentException("Comment not found");
        }
        var post = postRepository.findById(command.postId())
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));
        var commentToUpdate = result.get();
        try {
            var updatedComment = commentRepository.save(commentToUpdate.updateInformation(post, command.author(), command.content()));
            return Optional.of(updatedComment);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error updating comment: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteCommentCommand command) {
        if(!commentRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Comment not found");
        }
        try {
            commentRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error deleting comment: " + e.getMessage());
        }
    }
}