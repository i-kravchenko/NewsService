package com.example.NewService.dro;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class NewsResponse extends AbstractNewsResponse
{
    private List<CommentResponse> comments;
}
