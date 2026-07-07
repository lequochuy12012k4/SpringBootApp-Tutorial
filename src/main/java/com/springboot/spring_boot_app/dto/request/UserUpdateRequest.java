package com.springboot.spring_boot_app.dto.request;

import java.time.LocalDate;

import com.springboot.spring_boot_app.constraint.DobConstraint;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    String password;
    String firstName;
    String lastName;
    @DobConstraint(min=2,message = "INVALID_DOB")
    LocalDate doB;
    
}

