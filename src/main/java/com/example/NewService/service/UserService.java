package com.example.NewService.service;

import com.example.NewService.dro.Request;
import com.example.NewService.model.User;
import com.example.NewService.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService
{
    private final UserRepository repository;

    public List<User> findAll(Request request) {
        return repository.findAll(PageRequest.of(request.getPageNumber(), request.getPageSize())).getContent();
    }

    public User findById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public User save(User user) {
        return repository.save(user);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
