package com.springboot.spring_boot_app.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.spring_boot_app.dto.request.RoleRequest;
import com.springboot.spring_boot_app.entity.Role;
import com.springboot.spring_boot_app.repository.PermissionRepository;
import com.springboot.spring_boot_app.repository.RoleRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    public Role createRole(RoleRequest request){
        Role role = new Role();
        role.setName(request.getName());
        role.setDescription(request.getDescription());

        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));
        return roleRepository.save(role);
    }
    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }
    public void deleteRole(String role){
        roleRepository.deleteById(role);
    }
}
