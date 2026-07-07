package com.springboot.spring_boot_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.spring_boot_app.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {

}
