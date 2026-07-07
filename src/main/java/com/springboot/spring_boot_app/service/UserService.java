package com.springboot.spring_boot_app.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.spring_boot_app.dto.request.UserCreationRequest;
import com.springboot.spring_boot_app.dto.request.UserUpdateRequest;
import com.springboot.spring_boot_app.entity.User;
import com.springboot.spring_boot_app.enums.Role;
import com.springboot.spring_boot_app.exception.AppException;
import com.springboot.spring_boot_app.exception.ErrorCode;
import com.springboot.spring_boot_app.repository.RoleRepository;
import com.springboot.spring_boot_app.repository.UserRepositoty;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class UserService {
    UserRepositoty userRepositoty;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;
    public User createUser(UserCreationRequest request){
        User user = new User();
        if(userRepositoty.existsByUsername(request.getUsername())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDoB(request.getDoB());
        HashSet<String> roles = new HashSet<String>();
        roles.add(Role.USER.name());
        // user.setRoles(roles);
        return userRepositoty.save(user);
    }

    // @PreAuthorize("hasAuthority('APPROVE_POST')")
    @PreAuthorize("hasRole('ADMIN')") // Kiểm tra trc khi vào method
    public List<User>getUsers(){
        log.info("In method get users");
        return userRepositoty.findAll();
    }

    @PostAuthorize("returnObject.username == authentication.name") //Chi User lấy được thông tin chính mình
    public User getUser(String userId){
        log.info("in method getUser by id");
        return userRepositoty.findById(userId).orElseThrow(()->new AppException(ErrorCode.USER_NOT_EXISTED));
    }

    
    public User getMyInfo(){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        User user = userRepositoty.findByUsername(name).orElseThrow(()->new AppException(ErrorCode.USER_NOT_EXISTED));
        return user;
    }

    public User updateUser(String userId, UserUpdateRequest request){
        User user = getUser(userId);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDoB(request.getDoB());
        var roles = roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));
        return userRepositoty.save(user);
    }
    public void deleteUser(String userId){
        getUser(userId);
        userRepositoty.deleteById(userId);
    }
}
