package com.theprojectchow.backend.forum.interfaces.REST.resources;


public record CreateCommandResource(String author, String content, Long postId) {
}
