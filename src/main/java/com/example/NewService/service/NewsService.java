package com.example.NewService.service;

import com.example.NewService.aop.ValidateAccess;
import com.example.NewService.dro.NewsRequest;
import com.example.NewService.model.News;
import com.example.NewService.repository.NewsRepository;
import com.example.NewService.repository.NewsSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsService 
{
    private final NewsRepository repository;

    public List<News> findAll(NewsRequest request) {
        return repository.findAll(
                NewsSpecification.withRequest(request),
                PageRequest.of(request.getPageNumber(), request.getPageSize())
        ).getContent();
    }

    public Long getCount(NewsRequest request) {
        return repository.count(NewsSpecification.withRequest(request));
    }

    public News findById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("News not found"));
    }

    @ValidateAccess
    public News save(News news) {
        return repository.save(news);
    }

    @ValidateAccess
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
