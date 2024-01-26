package com.example.NewService.controller;

import com.example.NewService.dro.CategoryResponse;
import com.example.NewService.dro.Request;
import com.example.NewService.dro.UpsertCategoryRequest;
import com.example.NewService.mapper.CategoryMapper;
import com.example.NewService.model.Category;
import com.example.NewService.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/news-categories")
public class CategoryController
{
    private final CategoryService service;
    private final CategoryMapper mapper;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> findAll(@RequestBody @Valid Request request) {
        return ResponseEntity.ok(service.findAll(request).stream().map(mapper::categoryToResponse).toList());
    }

    @GetMapping("/{category}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable Category category) {
        return ResponseEntity.ok(mapper.categoryToResponse(category));
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@RequestBody @Valid UpsertCategoryRequest request) {
        Category category = service.save(mapper.requestToCategory(request));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.categoryToResponse(category));
    }

    @PutMapping("/{category}")
    public ResponseEntity<CategoryResponse> update(
            @PathVariable Category category,
            @RequestBody UpsertCategoryRequest request
    ) {
        category.setName(request.getName());
        category = service.save(category);
        return ResponseEntity.ok(mapper.categoryToResponse(category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
