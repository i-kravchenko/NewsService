package com.example.NewService.controller;

import com.example.NewService.dro.CommentResponse;
import com.example.NewService.dro.UpsertCommentRequest;
import com.example.NewService.mapper.CommentMapper;
import com.example.NewService.model.Comment;
import com.example.NewService.service.CommentService;
import com.example.NewService.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
public class CommentController 
{
    private final CommentService service;
    private final CommentMapper mapper;

    @GetMapping("/{comment}")
    public ResponseEntity<CommentResponse> findById(@PathVariable Comment comment) {
        return ResponseEntity.ok(mapper.commentToResponse(comment));
    }

    @PostMapping
    public ResponseEntity<CommentResponse> create(@RequestBody UpsertCommentRequest request) {
        Comment comment = service.save(mapper.requestToComment(request));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.commentToResponse(comment));
    }

    @PutMapping("/{comment}")
    public ResponseEntity<CommentResponse> update(
            @PathVariable Comment comment,
            @RequestBody UpsertCommentRequest request
    ) {
        BeanUtils.copyNonNullProperties(mapper.requestToComment(request), comment);
        comment = service.save(comment);
        return ResponseEntity.ok(mapper.commentToResponse(comment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
