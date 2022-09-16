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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(name = "email", nullable = false,unique = true)
    private String email;

    private String password;

    @Column(name = "phone", nullable = false,unique = true)
    private String phone;

    @Column(name = "auth_key",unique = true)
    private String authKey;
    private LocalDateTime authDate;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    private boolean isAdmin;
    private boolean isReserving;

}
