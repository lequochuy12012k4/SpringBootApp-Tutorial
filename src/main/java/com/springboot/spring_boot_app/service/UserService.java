package com.springboot.spring_boot_app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.spring_boot_app.dto.request.UserCreationRequest;
import com.springboot.spring_boot_app.dto.request.UserUpdateRequest;
import com.springboot.spring_boot_app.entity.User;
import com.springboot.spring_boot_app.exception.AppException;
import com.springboot.spring_boot_app.exception.ErrorCode;
import com.springboot.spring_boot_app.repository.UserRepositoty;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserService {
    UserRepositoty userRepositoty;
    public User createUser(UserCreationRequest request){
        User user = new User();

        if(userRepositoty.existsByUsername(request.getUsername())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDoB(request.getDoB());
        return userRepositoty.save(user);
    }

    public List<User>getUsers(){
        return userRepositoty.findAll();
    }
    public User getUser(String userId){
        return userRepositoty.findById(userId).orElseThrow(()->new AppException(ErrorCode.USER_NOT_FOUND));
    }
    public User updateUser(String userId, UserUpdateRequest request){
        User user = getUser(userId);
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDoB(request.getDoB());
        return userRepositoty.save(user);
    }
    public void deleteUser(String userId){
        getUser(userId);
        userRepositoty.deleteById(userId);
    }
}
