package com.example.NewService.dro;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpsertUserRequest
{
    @NotBlank(message = "user name is required")
    private String name;
}
