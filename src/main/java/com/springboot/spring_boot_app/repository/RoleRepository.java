package com.springboot.spring_boot_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.spring_boot_app.entity.Role;

public interface RoleRepository extends JpaRepository<Role,String> {
    
}
