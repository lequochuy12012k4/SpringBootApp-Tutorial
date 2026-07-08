package com.springboot.spring_boot_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.spring_boot_app.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {   
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username); 
}
