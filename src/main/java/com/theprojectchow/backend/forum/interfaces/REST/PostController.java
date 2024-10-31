package com.theprojectchow.backend.forum.interfaces.REST;

import com.theprojectchow.backend.forum.domain.model.commands.DeletePostCommand;
import com.theprojectchow.backend.forum.domain.model.queries.GetAllPostsQuery;
import com.theprojectchow.backend.forum.domain.model.queries.GetPostByIdQuery;
import com.theprojectchow.backend.forum.domain.services.PostCommandService;
import com.theprojectchow.backend.forum.domain.services.PostQueryService;
import com.theprojectchow.backend.forum.interfaces.REST.resources.CreatePostResource;
import com.theprojectchow.backend.forum.interfaces.REST.resources.PostResource;
import com.theprojectchow.backend.forum.interfaces.REST.transform.CreatePostCommandFromAssembler;
import com.theprojectchow.backend.forum.interfaces.REST.transform.PostResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/posts", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Posts", description = "Posts Management Endpoints")
public class PostController {
    private final PostCommandService postCommandService;
    private final PostQueryService postQueryService;

    public PostController(PostCommandService postCommandService, PostQueryService postQueryService) {
        this.postCommandService = postCommandService;
        this.postQueryService = postQueryService;
    }

    @PostMapping
    public ResponseEntity<PostResource> createPost(@RequestBody CreatePostResource createPostResource) {
        var createPostCommand = CreatePostCommandFromAssembler.toCommandFromResource(createPostResource);
        var postId = postCommandService.handle(createPostCommand);
        if(postId == null) {
            return ResponseEntity.badRequest().build();
        }
        var getPostByIdQuery = new GetPostByIdQuery(postId);
        var post = postQueryService.handle(getPostByIdQuery);
        if(post.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var postResource = PostResourceFromEntityAssembler.toResourceFromEntity(post.get());
        return new ResponseEntity<>(postResource, HttpStatus.CREATED);
    }
    @GetMapping("/{postId}")
    public ResponseEntity<PostResource> getPostById(@PathVariable Long postId) {
        var getPostByIdQuery = new GetPostByIdQuery(postId);
        var post = postQueryService.handle(getPostByIdQuery);
        if(post.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var postResource = PostResourceFromEntityAssembler.toResourceFromEntity(post.get());
        return new ResponseEntity<>(postResource, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<PostResource>> getAllPosts() {
        var getAllPostsQuery = new GetAllPostsQuery();
        var posts = postQueryService.handle(getAllPostsQuery);
        var postResources = posts.stream().map(PostResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(postResources);
    }
    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        var deletePostCommand = new DeletePostCommand(postId);
        postCommandService.handle(deletePostCommand);
        return ResponseEntity.ok("Post deleted successfully");
    }
}
