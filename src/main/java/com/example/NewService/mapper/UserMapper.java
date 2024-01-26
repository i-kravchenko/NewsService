package com.example.NewService.mapper;

import com.example.NewService.dro.UpsertUserRequest;
import com.example.NewService.dro.UserResponse;
import com.example.NewService.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {NewsMapper.class})
public interface UserMapper
{
    User requestToUser(UpsertUserRequest request);
    @Mapping(source = "id", target = "userId")
    UserResponse userToResponse(User user);
}
