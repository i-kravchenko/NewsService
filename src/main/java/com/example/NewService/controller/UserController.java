package com.example.NewService.controller;

import com.example.NewService.dro.Request;
import com.example.NewService.dro.UpsertUserRequest;
import com.example.NewService.dro.UserResponse;
import com.example.NewService.mapper.UserMapper;
import com.example.NewService.model.User;
import com.example.NewService.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController
{
    private final UserService service;
    private final UserMapper mapper;

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll(@RequestBody @Valid Request request) {
        return ResponseEntity.ok(service.findAll(request).stream().map(mapper::userToResponse).toList());
    }

    @GetMapping("/{user}")
    public ResponseEntity<UserResponse> findById(@PathVariable User user) {
        return ResponseEntity.ok(mapper.userToResponse(user));
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UpsertUserRequest request, HttpServletResponse response) {
        User user = service.save(mapper.requestToUser(request));
        response.addCookie(new Cookie("user_id", user.getId().toString()));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.userToResponse(user));
    }

    @PutMapping("/{user}")
    public ResponseEntity<UserResponse> update(@PathVariable User user, @RequestBody UpsertUserRequest request) {
        user.setName(request.getName());
        user = service.save(user);
        return ResponseEntity.ok(mapper.userToResponse(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
