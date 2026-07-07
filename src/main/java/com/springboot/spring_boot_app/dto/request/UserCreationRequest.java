package com.springboot.spring_boot_app.dto.request;

import java.time.LocalDate;

import com.springboot.spring_boot_app.constraint.DobConstraint;

import jakarta.validation.constraints.Size;
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
public class UserCreationRequest {

    @Size(min=8,message = "USERNAME_INVALID")
    String username;

    @Size(min = 8,message = "PASSWORD_INVALID")
    String password;
    String firstName;
    String lastName;

    @DobConstraint(min = 18,message = "INVALID_DOB")
    LocalDate doB;    
}
