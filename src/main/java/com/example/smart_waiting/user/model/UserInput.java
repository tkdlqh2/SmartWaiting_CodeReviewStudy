package com.example.smart_waiting.user.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInput {

    private String email;
    private String password;
    private String phone;
}
