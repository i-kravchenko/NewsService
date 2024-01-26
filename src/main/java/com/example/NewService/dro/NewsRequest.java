package com.example.NewService.dro;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class NewsRequest extends Request
{
    private Long userId;
    private Long categoryId;
}
