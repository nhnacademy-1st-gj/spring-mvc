package com.academy.score.exception;

public class UserNotExistException extends RuntimeException {
    public UserNotExistException() {
        super("User does not exist. Please check email or password");
    }
}
