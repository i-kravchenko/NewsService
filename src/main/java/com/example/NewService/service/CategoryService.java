package com.example.NewService.service;

import com.example.NewService.dro.Request;
import com.example.NewService.model.Category;
import com.example.NewService.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService 
{
    private final CategoryRepository repository;

    public List<Category> findAll(Request request) {
        return repository.findAll(PageRequest.of(request.getPageNumber(), request.getPageSize())).getContent();
    }

    public Category findById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
    }

    public Category save(Category category) {
        return repository.save(category);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
