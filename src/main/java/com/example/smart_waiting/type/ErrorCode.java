package com.example.smart_waiting.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    REG_KEY_INVALIDATE("인증키가 유효하지 않습니다."),
    REG_KEY_EXPIRED("인증키가 만료되었습니다."),
    PASSWORD_NOT_MATCH("비밀번호가 일치하지 않습니다.");



    private final String description;

}
