package com.springboot.spring_boot_app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.springboot.spring_boot_app.dto.request.ApiResponse;
import org.junit.jupiter.api.Test;

class ApiResponseBuilderTest {

    @Test
    void builderShouldKeepDefaultCodeWhenNotSet() {
        ApiResponse<String> response = ApiResponse.<String>builder()
                .result("ok")
                .build();

        assertEquals(1000, response.getCode());
    }
}
