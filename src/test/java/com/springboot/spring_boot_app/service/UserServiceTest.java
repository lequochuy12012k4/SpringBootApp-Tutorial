package com.springboot.spring_boot_app.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.springboot.spring_boot_app.dto.request.UserCreationRequest;
import com.springboot.spring_boot_app.entity.User;
import com.springboot.spring_boot_app.exception.AppException;
import com.springboot.spring_boot_app.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
@TestPropertySource("/test.properties")
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockitoBean
    private UserRepository userRepository;

    private UserCreationRequest request;
    private User userResponse;
    private User user;

    @BeforeEach
    void initData(){
        LocalDate dob = LocalDate.of(2004, 01, 12);
        request = UserCreationRequest.builder()
            .username("lequochuy")
            .firstName("LeQuoc")
            .lastName("Huy")
            .password("123456789")
            .doB(dob)
            .build();
            
        userResponse = userResponse.builder()
            .id("97f340ef")
            .username("lequochuy")
            .firstName("LeQuoc")
            .lastName("Huy")
            .doB(dob)
            .build();
        
        user = User.builder()
            .id("97f340ef")
            .username("lequochuy")
            .firstName("LeQuoc")
            .lastName("Huy")
            .doB(dob)
            .build();
    }

    @Test
    void createUser_validRequest_success(){
        Mockito.when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.save(any())).thenReturn(user);

        var response = userService.createUser(request);

        Assertions.assertThat(response.getId()).isEqualTo("97f340ef");
        Assertions.assertThat(response.getUsername()).isEqualTo("lequochuy");
    }

    @Test
    void createUser_userExisted_fail(){
        //GIVEN
        Mockito.when(userRepository.existsByUsername(anyString())).thenReturn(true);

        //WHEN
        var exception = assertThrows(AppException.class, ()->userService.createUser(request));
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1002);
        Assertions.assertThat(exception.getErrorCode().getMessage()).isEqualTo("User existed");
    }

    @Test
    @WithMockUser(username = "john")
    void getMyInfo_success(){
        //GIVEN
        Mockito.when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user)); 

        //WHEN
        var response = userService.getMyInfo();
        Assertions.assertThat(response.getUsername()).isEqualTo("lequochuy");
        Assertions.assertThat(response.getId()).isEqualTo("97f340ef");
    }

     
    @Test
    @WithMockUser(username = "lequochuy")
    void getMyInfo_userNotFound(){
        //GIVEN
        Mockito.when(userRepository.findByUsername(anyString())).thenReturn(Optional.ofNullable(user)); 

        //WHEN
        var exception = assertThrows(AppException.class, ()->userService.getMyInfo());
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1005);
        Assertions.assertThat(exception.getErrorCode().getMessage()).isEqualTo("User not found");
    }
}
