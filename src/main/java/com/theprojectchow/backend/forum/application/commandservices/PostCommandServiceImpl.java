package com.theprojectchow.backend.forum.application.commandservices;

import com.theprojectchow.backend.forum.domain.model.aggregates.Post;
import com.theprojectchow.backend.forum.domain.model.commands.CreatePostCommand;
import com.theprojectchow.backend.forum.domain.model.commands.DeletePostCommand;
import com.theprojectchow.backend.forum.domain.model.commands.UpdatePostCommand;
import com.theprojectchow.backend.forum.domain.services.PostCommandService;
import com.theprojectchow.backend.forum.infrastructure.persistence.jpa.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class PostCommandServiceImpl  implements PostCommandService {

    private final PostRepository postRepository;

    public PostCommandServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    @Override
    public Long handle(CreatePostCommand command) {
        if(postRepository.existsByContent(command.content())) {
            throw new IllegalArgumentException("Post with content already exists");
        }
        var post = new Post(command);
        try {
            postRepository.save(post);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving post"+e.getMessage());
        }
        return post.getId();
    }

    @Override
    public Optional<Post> handle(UpdatePostCommand command) {
        if(postRepository.existsByContent(command.content())) {
            throw new IllegalArgumentException("Post with content already exists");
        }
        var result = postRepository.findById(command.id());
        if (result.isEmpty()) {
            throw new IllegalArgumentException("Post not found");
        }
        var postToUpdate = result.get();
        try {
            var updatedPost = postRepository.save(postToUpdate.updateInformation(command.title(), command.content(), command.image()));
            return Optional.of(updatedPost);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error updating post"+e.getMessage());
        }
    }

    @Override
    public void handle(DeletePostCommand command) {
        if(!postRepository.existsById(command.postId())) {
            throw new IllegalArgumentException("Post not found");
        }
        try {
            postRepository.deleteById(command.postId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error deleting post"+e.getMessage());
        }
    }
}
