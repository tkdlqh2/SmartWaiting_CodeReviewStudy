package com.example.smart_waiting.user;

import com.example.smart_waiting.type.UserStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @Email
    @Column(name = "email", nullable = false)
    private String email;

    private String password;
    private String phone;

    private String authKey;
    private LocalDateTime authDate;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    private boolean isAdmin;
    private boolean isReserving;

}
