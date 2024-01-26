package com.example.NewService.controller;

import com.example.NewService.dro.NewsRequest;
import com.example.NewService.dro.NewsResponse;
import com.example.NewService.dro.UpsertNewsRequest;
import com.example.NewService.mapper.NewsMapper;
import com.example.NewService.dro.NewsResponseList;
import com.example.NewService.model.News;
import com.example.NewService.service.NewsService;
import com.example.NewService.utils.BeanUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/news")
public class NewsController
{
    private final NewsService service;
    private final NewsMapper mapper;

    @GetMapping
    public ResponseEntity<List<NewsResponseList>> findAll(@RequestBody @Valid NewsRequest request) {
        return ResponseEntity.ok(service
                .findAll(request)
                .stream()
                .map(mapper::newsToResponseList)
                .toList()
        );
    }

    @GetMapping("/{news}")
    public ResponseEntity<NewsResponse> findById(@PathVariable News news) {
        return ResponseEntity.ok(mapper.newsToResponse(news));
    }

    @PostMapping
    public ResponseEntity<NewsResponse> create(@RequestBody @Valid UpsertNewsRequest request) {
        News news = service.save(mapper.requestToNews(request));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.newsToResponse(news));
    }

    @PutMapping("/{news}")
    public ResponseEntity<NewsResponse> update(@PathVariable News news, @RequestBody UpsertNewsRequest request) {
        BeanUtils.copyNonNullProperties(mapper.requestToNews(request), news);
        service.save(news);
        return ResponseEntity.ok(mapper.newsToResponse(news));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
