package com.springboot.spring_boot_app.controller;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.springboot.spring_boot_app.dto.request.UserCreationRequest;
import com.springboot.spring_boot_app.entity.User;
import com.springboot.spring_boot_app.service.UserService;

import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
@TestPropertySource("/test.properties")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    private UserCreationRequest request;
    private User userResponse;

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
    }

    @Test
    void createUser_validRequest_success() throws Exception{
        //GIVEN
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(request);

        Mockito.when(userService.createUser(ArgumentMatchers.any())).thenReturn(userResponse); 
        //WHEN
        mockMvc.perform(MockMvcRequestBuilders
            .post("/users")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(content))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("code").value(1000))
            .andExpect(MockMvcResultMatchers.jsonPath("result.id").value("97f340ef"))
            .andExpect(MockMvcResultMatchers.jsonPath("result.username").value("lequochuy")
        );  
            
        //THEN
    }

    @Test
    void createUser_username_validRequest_fail() throws Exception{
        //GIVEN
        request.setUsername("leq");
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(request);
 
        //WHEN
        mockMvc.perform(MockMvcRequestBuilders
            .post("/users")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(content))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andExpect(MockMvcResultMatchers.jsonPath("code").value(1003))
            .andExpect(MockMvcResultMatchers.jsonPath("message").value("The username length must be from 8")
        );  
            
        //THEN
    }
}
