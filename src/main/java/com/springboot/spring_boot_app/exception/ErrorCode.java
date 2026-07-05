package com.springboot.spring_boot_app.exception;

public enum ErrorCode {
    USER_EXISTED(1002,"User existed"),
    USER_NOT_EXISTED(1005,"User not found"),
    UNCATEGORIZED_EXCEPTION(9999,"Uncategorized error"),
    USERNAME_INVALID(1003,"The username length must be from 8"),
    PASSWORD_INVALID(1004,"The password length must be from 8"),
    INVALID_KEY(1001,"Invalid message key")
    ;

    private int code;
    private String message;
    
    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    
}