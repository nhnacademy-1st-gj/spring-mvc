package com.academy.score.exception;

public class StudentNotExistException extends RuntimeException{
    public StudentNotExistException(long studentId) {
        super("Student does not exist. Student Id: "+ studentId);
    }
}
