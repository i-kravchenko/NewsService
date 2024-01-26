package com.example.NewService.dro;

import lombok.Data;

@Data
public class CommentResponse
{
    private Long commentId;
    private String text;
    private Long userId;
}
