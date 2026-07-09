package com.springboot.spring_boot_app.controller;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.springboot.spring_boot_app.dto.request.UserCreationRequest;
import com.springboot.spring_boot_app.entity.User;
import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
@Testcontainers
class UserControllerIntergrationTest  {

    @Container
    static final MySQLContainer<?> MY_SQL_CONTAINER = new MySQLContainer<>("mysql:latest");

    @DynamicPropertySource
    static void configureDataSource(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", MY_SQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", MY_SQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", MY_SQL_CONTAINER::getPassword);
        registry.add("spring.datasource.driver-class-name", ()->"com.mysql.cj.jdbc.Driver");
        registry.add("spring.jpa.hibernate.ddl-auto", ()->"update");
        registry.add("spring.jpa.show-sql", ()->"auto");
    }
    
    @Autowired
    private MockMvc mockMvc;
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
}
