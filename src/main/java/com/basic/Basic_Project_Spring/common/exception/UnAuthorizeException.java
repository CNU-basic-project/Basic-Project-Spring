package com.basic.Basic_Project_Spring.common.exception;

public class UnAuthorizeException extends RuntimeException {

    public UnAuthorizeException(String message) {
        super(message);
    }
}
