package com.example.NewService.dro;

import lombok.Data;

@Data
public class CategoryResponse
{
    private Long categoryId;
    private String name;
    private Long newsCount;
}
