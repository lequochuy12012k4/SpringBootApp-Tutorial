package com.springboot.spring_boot_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.spring_boot_app.entity.User;

@Repository
public interface UserRepositoty extends JpaRepository<User, String> {   
    boolean existsByUsername(String username);
}
