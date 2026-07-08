package com.springboot.spring_boot_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999,"Uncategorized error",HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001,"Invalid message key",HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002,"User existed",HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003,"The username length must be from {min}",HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1004,"The password length must be from {min}",HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005,"User not found",HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006,"Unauthentcated",HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007,"You do not have permission",HttpStatus.FORBIDDEN),
    INVALID_DOB(1008,"Your age must be at least {min}",HttpStatus.BAD_REQUEST);
    ;

    private int code;
    private String message;
    private HttpStatusCode statusCode;
    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}