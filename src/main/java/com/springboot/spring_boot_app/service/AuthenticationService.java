package com.springboot.spring_boot_app.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.spring_boot_app.dto.request.AuthenticationRequest;
import com.springboot.spring_boot_app.exception.AppException;
import com.springboot.spring_boot_app.exception.ErrorCode;
import com.springboot.spring_boot_app.repository.UserRepositoty;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepositoty userRepositoty;

    public boolean authenticate(AuthenticationRequest request){
        var user = userRepositoty.findByUsername(request.getUsername()).orElseThrow(()-> new AppException(ErrorCode.USERNAME_INVALID));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        return passwordEncoder.matches(request.getPassword(), user.getPassword());
    }
}
