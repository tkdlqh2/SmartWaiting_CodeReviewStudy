package com.example.smart_waiting.user.model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class UserLoginInput {

    private String email;
    private String password;
}
