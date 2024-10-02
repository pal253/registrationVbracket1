package com.api.exception;

public class ResourceNotfFoundException extends RuntimeException{
    public ResourceNotfFoundException(String msg) {
        super(msg);
    }
}
