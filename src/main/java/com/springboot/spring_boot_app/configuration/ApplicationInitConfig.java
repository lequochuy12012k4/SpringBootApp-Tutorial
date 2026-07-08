package com.springboot.spring_boot_app.configuration;

import java.util.HashSet;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.springboot.spring_boot_app.entity.User;
import com.springboot.spring_boot_app.enums.Role;
import com.springboot.spring_boot_app.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;

    @Bean
    @ConditionalOnProperty(prefix = "spring",value = "datasource.driver-class-name",havingValue = "com.mysql.cj.jdbc.Driver")
    ApplicationRunner applicationRunner(UserRepository userRepositoty){
        log.info("Init application");
        return args ->{
            if(userRepositoty.findByUsername("admin").isEmpty()){
                var roles = new HashSet<String>();
                roles.add(Role.ADMIN.name());
                User user = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin"))
                    // .roles(roles)
                    .build();
                userRepositoty.save(user);
                log.warn("admin user has been created with default password admin, please change it");
            }
        };
    }
}