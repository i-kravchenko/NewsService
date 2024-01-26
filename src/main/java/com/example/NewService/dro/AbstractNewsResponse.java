package com.example.NewService.dro;

import lombok.Data;

@Data
public abstract class AbstractNewsResponse
{
    protected Long newsId;
    protected String title;
}
