package com.theprojectchow.backend.forum.interfaces.REST.resources;


public record CommentResource(Long id, String author, String content, Long postId) {
}
