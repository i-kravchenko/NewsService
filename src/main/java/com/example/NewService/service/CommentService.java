package com.example.NewService.service;

import com.example.NewService.aop.ValidateAccess;
import com.example.NewService.model.Comment;
import com.example.NewService.repository.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService 
{
    private final CommentRepository repository;

    public Comment findById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));
    }

    @ValidateAccess
    public Comment save(Comment comment) {
        return repository.save(comment);
    }

    @ValidateAccess
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Integer getCountByNewsId(Long id) {
        return repository.countByNewsId(id);
    }
}
