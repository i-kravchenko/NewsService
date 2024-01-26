package com.example.NewService.dro;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Request
{
    @NotNull(message = "pageSize is required")
    protected Integer pageSize;
    @NotNull(message = "pageNumber is required")
    protected Integer pageNumber;
}
