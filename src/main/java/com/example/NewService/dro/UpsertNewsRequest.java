package com.example.NewService.dro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpsertNewsRequest
{
    @NotBlank(message = "news title is required")
    private String title;
    @NotNull(message = "categoryId is required")
    private Long categoryId;
}
