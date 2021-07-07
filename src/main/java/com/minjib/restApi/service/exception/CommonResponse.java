package com.minjib.restApi.service.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommonResponse {
    private String message;
    private int status;
}
