package com.example.smart_waiting.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserPasswordResetInput {
    private String password;
    private String newPassword;
}
