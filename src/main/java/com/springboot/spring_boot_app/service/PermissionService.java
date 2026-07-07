package com.springboot.spring_boot_app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.spring_boot_app.dto.request.PermissionRequest;
import com.springboot.spring_boot_app.dto.response.PermissionResponse;
import com.springboot.spring_boot_app.entity.Permission;
import com.springboot.spring_boot_app.mapper.PermissionMapper;
import com.springboot.spring_boot_app.repository.PermissionRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {
    PermissionRepository permissionRepository;
    public Permission createPermission(PermissionRequest request){
        Permission permission = new Permission();
        permission.setName(request.getName());
        permission.setDescription(request.getDescription());
        return permissionRepository.save(permission);
    }
    public List<Permission> getAllPermissions(){
        return permissionRepository.findAll();
    }
    public void deletePermission(String permission){
        permissionRepository.deleteById(permission);
    }
}
