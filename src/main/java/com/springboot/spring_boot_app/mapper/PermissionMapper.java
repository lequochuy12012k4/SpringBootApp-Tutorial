package com.springboot.spring_boot_app.mapper;

import org.mapstruct.Mapper;

import com.springboot.spring_boot_app.dto.request.PermissionRequest;
import com.springboot.spring_boot_app.dto.response.PermissionResponse;
import com.springboot.spring_boot_app.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);    
}
