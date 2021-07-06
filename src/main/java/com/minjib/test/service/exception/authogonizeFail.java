package com.minjib.test.service.exception;

public class authogonizeFail extends RuntimeException {
    public static final String MESSAGE = "인증이 실패하였습니다.";

    public authogonizeFail() {
        super(MESSAGE);
    }
}
