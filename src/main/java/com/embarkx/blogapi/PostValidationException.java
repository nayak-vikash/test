package com.embarkx.blogapi;

public class PostValidationException extends RuntimeException {

    public PostValidationException(String message) {
        super(message);
    }
}
