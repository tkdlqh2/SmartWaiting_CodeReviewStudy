package com.example.smart_waiting.user.model;

import com.example.smart_waiting.type.UserStatus;
import com.example.smart_waiting.user.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String email;
    private String phone;

    private UserStatus userStatus;

    private boolean isAdmin;
    private boolean isReserving;

    public static UserDto of(User user){
        return UserDto.builder()
                .email(user.getEmail())
                .phone(user.getPhone())
                .userStatus(user.getUserStatus())
                .isAdmin(user.isAdmin())
                .isReserving(user.isReserving())
                .build();
    }
}
