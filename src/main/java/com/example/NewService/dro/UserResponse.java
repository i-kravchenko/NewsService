package com.example.NewService.dro;

import lombok.Data;

import java.util.List;

@Data
public class UserResponse
{
    private Long userId;
    private String name;
    private List<NewsResponse> news;
}
