package com.example.NewService.dro;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpsertCategoryRequest
{
    @NotBlank(message = "category name is required")
    private String name;
}
