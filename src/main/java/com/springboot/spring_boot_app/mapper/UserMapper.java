package com.springboot.spring_boot_app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.springboot.spring_boot_app.dto.request.UserCreationRequest;
import com.springboot.spring_boot_app.dto.request.UserUpdateRequest;
import com.springboot.spring_boot_app.dto.response.UserResponse;
import com.springboot.spring_boot_app.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
    UserResponse toUserResponse(User user);
}
