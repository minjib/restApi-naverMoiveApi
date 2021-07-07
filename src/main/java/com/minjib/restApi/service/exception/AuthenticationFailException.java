package com.minjib.restApi.service.exception;

public class AuthenticationFailException extends RuntimeException {
    public static final String MESSAGE = "인증이 실패하였습니다.";

    public AuthenticationFailException() {
        super(MESSAGE);
    }
}
