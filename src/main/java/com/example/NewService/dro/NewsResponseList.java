package com.example.NewService.dro;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class NewsResponseList extends AbstractNewsResponse
{
    private Integer commentsCount;
}
