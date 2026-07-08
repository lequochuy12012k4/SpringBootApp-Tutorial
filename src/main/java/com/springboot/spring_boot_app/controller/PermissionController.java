package com.springboot.spring_boot_app.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.spring_boot_app.dto.request.ApiResponse;
import com.springboot.spring_boot_app.dto.request.PermissionRequest;
import com.springboot.spring_boot_app.entity.Permission;
import com.springboot.spring_boot_app.service.PermissionService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionController {
    PermissionService permissionService;
    @PostMapping
    ApiResponse<Permission> createPermission(@RequestBody PermissionRequest request){
        ApiResponse<Permission> apiResponse = new ApiResponse<>();
        apiResponse.setResult(permissionService.createPermission(request));
        return apiResponse;
    }

    @GetMapping
    ApiResponse<List<Permission>> getAllPermission(){
       ApiResponse<List<Permission>> apiResponse = new ApiResponse<>();
       apiResponse.setResult(permissionService.getAllPermissions());
       return apiResponse;
    }
    
    @DeleteMapping("/{permission}")
    ApiResponse<String> deletePermission(@PathVariable("permission") String permission){
        permissionService.deletePermission(permission);
        return ApiResponse.<String>builder().result("Permission has been deleted").build();
    }
}
