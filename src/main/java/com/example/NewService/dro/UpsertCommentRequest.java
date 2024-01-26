package com.example.NewService.dro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpsertCommentRequest
{
    @NotBlank(message = "comment text is required")
    private String text;
    @NotNull(message = "newsId is required")
    private Long newsId;
}
