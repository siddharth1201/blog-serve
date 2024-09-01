package com.example.blog_server.comments.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateCommentRequest {
    private String title;
    private String body;
}
