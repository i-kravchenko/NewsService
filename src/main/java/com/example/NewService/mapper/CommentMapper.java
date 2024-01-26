package com.example.NewService.mapper;

import com.example.NewService.dro.CommentResponse;
import com.example.NewService.dro.UpsertCommentRequest;
import com.example.NewService.model.Comment;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@DecoratedWith(CommentMapperDelegate.class)
public interface CommentMapper
{
    Comment requestToComment(UpsertCommentRequest request);

    @Mapping(source = "id", target = "commentId")
    @Mapping(target = "userId", expression = "java(comment.getUser().getId())")
    CommentResponse commentToResponse(Comment comment);
}
