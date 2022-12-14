package com.example.smart_waiting.user.model;

import com.example.smart_waiting.type.UserStatus;
import com.example.smart_waiting.user.User;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String email;
    private String phone;
    private String name;

    private UserStatus userStatus;

    private List<String> userRoles;
    private boolean isReserving;

    public static UserDto of(User user){
        return UserDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .userStatus(user.getUserStatus())
                .userRoles(user.getUserRoles())
                .isReserving(user.isReserving())
                .build();
    }
}
