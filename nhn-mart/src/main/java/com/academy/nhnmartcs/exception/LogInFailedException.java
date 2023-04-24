package com.academy.nhnmartcs.exception;

public class LogInFailedException extends RuntimeException {
    public LogInFailedException() {
        super("아이디 또는 비밀번호를 확인해주세요.");
    }
}
