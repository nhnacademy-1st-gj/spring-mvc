package com.academy.score.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class StudentNotExistException extends RuntimeException {
    public StudentNotExistException(long studentId) {
        super("Student does not exist. Student Id: " + studentId);
    }
}
