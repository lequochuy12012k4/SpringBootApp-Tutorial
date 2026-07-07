package com.springboot.spring_boot_app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.spring_boot_app.dto.request.ApiResponse;
import com.springboot.spring_boot_app.dto.request.RoleRequest;
import com.springboot.spring_boot_app.entity.Role;
import com.springboot.spring_boot_app.service.RoleService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleController {
    RoleService roleService;
    @PostMapping
    ApiResponse<Role> createRole(@RequestBody RoleRequest request){
        ApiResponse<Role> apiResponse = new ApiResponse<>();
        apiResponse.setResult((roleService.createRole(request)));
        return apiResponse;
    }

    @GetMapping
    ApiResponse<List<Role>> getAllPermission(){
       ApiResponse<List<Role>> apiResponse = new ApiResponse<>();
       apiResponse.setResult(roleService.getAllRoles());
       return apiResponse;
    }
    
    @DeleteMapping("/{role}")
    ApiResponse<String> deletePermission(@PathVariable("role") String role){
        roleService.deleteRole(role);
        return ApiResponse.<String>builder().result("Role has been deleted").build();
    }
}
