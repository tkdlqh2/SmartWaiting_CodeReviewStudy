package com.example.smart_waiting.exception;

import org.springframework.http.HttpStatus;

public class MarketNotFoundException extends AbstractException{
    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getMessage() {
        return "음식점을 등록하지 않은 유저입니다.";
    }
}
