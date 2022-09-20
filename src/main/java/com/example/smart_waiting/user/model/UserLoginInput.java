package com.example.smart_waiting.user.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserLoginInput {

    private String email;
    private String password;
}
