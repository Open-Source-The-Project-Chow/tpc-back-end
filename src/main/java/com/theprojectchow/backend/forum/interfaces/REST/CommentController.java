package com.theprojectchow.backend.forum.interfaces.REST;

import com.theprojectchow.backend.forum.domain.model.commands.DeleteCommentCommand;
import com.theprojectchow.backend.forum.domain.model.commands.UpdateCommentCommand;
import com.theprojectchow.backend.forum.domain.model.queries.GetAllCommentQuery;
import com.theprojectchow.backend.forum.domain.model.queries.GetCommentByIdQuery;
import com.theprojectchow.backend.forum.domain.model.queries.GetCommentsByPostIdQuery;
import com.theprojectchow.backend.forum.domain.services.CommentCommandService;
import com.theprojectchow.backend.forum.domain.services.CommentQueryService;
import com.theprojectchow.backend.forum.interfaces.REST.resources.CommentResource;
import com.theprojectchow.backend.forum.interfaces.REST.resources.CreateCommandResource;
import com.theprojectchow.backend.forum.interfaces.REST.resources.UpdateCommentResource;
import com.theprojectchow.backend.forum.interfaces.REST.transform.CommentResourceFromEntityAssembler;
import com.theprojectchow.backend.forum.interfaces.REST.transform.CreateCommentCommandFromAssembler;
import com.theprojectchow.backend.forum.interfaces.REST.transform.UpdateCommentCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/comments", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Comments", description = "Comments Management Endpoints")
public class CommentController {
    private final CommentCommandService commentCommandService;
    private final CommentQueryService commentQueryService;

    public CommentController(CommentCommandService commentCommandService, CommentQueryService commentQueryService) {
        this.commentCommandService = commentCommandService;
        this.commentQueryService = commentQueryService;
    }

    @PostMapping
    public ResponseEntity<CommentResource> createComment(@RequestBody CreateCommandResource createCommentResource) {
        var createCommentCommand = CreateCommentCommandFromAssembler.toCommandFromResource(createCommentResource);
        var commentId = commentCommandService.handle(createCommentCommand);
        if(commentId == null) {
            return ResponseEntity.badRequest().build();
        }
        var getCommentByIdQuery = new GetCommentByIdQuery(commentId);
        var comment = commentQueryService.handle(getCommentByIdQuery);
        if(comment.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var commentResource = CommentResourceFromEntityAssembler.toResourceFromEntity(comment.get());
        return new ResponseEntity<>(commentResource, HttpStatus.CREATED);
    }
    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResource> getCommentById(@PathVariable Long commentId) {
        var getCommentByIdQuery = new GetCommentByIdQuery(commentId);
        var comment = commentQueryService.handle(getCommentByIdQuery);
        if(comment.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var commentResource = CommentResourceFromEntityAssembler.toResourceFromEntity(comment.get());
        return new ResponseEntity<>(commentResource, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<CommentResource>> getAllComments() {
        var getAllCommentsQuery = new GetAllCommentQuery();
        var comments = commentQueryService.handle(getAllCommentsQuery);
        if(comments.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var commentResources = comments.stream()
                .map(CommentResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return new ResponseEntity<>(commentResources, HttpStatus.OK);
    }
    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId) {
        var deleteCommentCommand = new DeleteCommentCommand(commentId);
        commentCommandService.handle(deleteCommentCommand);
        return ResponseEntity.ok("Comment deleted successfully");
    }
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResource> updateComment(@PathVariable Long commentId, @RequestBody UpdateCommentResource commentResource) {
        var updateCommentCommand = UpdateCommentCommandFromResourceAssembler.toCommandFromResource(commentId, commentResource);
        var updatedComment = commentCommandService.handle(updateCommentCommand);
        if(updatedComment.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var commentResourceUpdated = CommentResourceFromEntityAssembler.toResourceFromEntity(updatedComment.get());
        return new ResponseEntity<>(commentResourceUpdated, HttpStatus.OK);
    }
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentResource>> getCommentsByPostId(@PathVariable Long postId) {
        var getCommentsByPostIdQuery = new GetCommentsByPostIdQuery(postId);
        var comments = commentQueryService.handle(getCommentsByPostIdQuery);
        if(comments.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var commentResources = comments.stream()
                .map(CommentResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return new ResponseEntity<>(commentResources, HttpStatus.OK);
    }
}
