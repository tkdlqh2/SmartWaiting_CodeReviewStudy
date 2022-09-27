package com.example.smart_waiting.user;

import com.example.smart_waiting.domain.ListStringConverter;
import com.example.smart_waiting.type.UserStatus;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Email
    @Column(name = "email", nullable = false,unique = true)
    private String email;

    private String name;
    private String password;

    @Column(name = "phone", nullable = false,unique = true)
    private String phone;

    @Column(name = "auth_key",unique = true)
    private String authKey;
    private LocalDateTime authDate;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @Column
    @Convert(converter = ListStringConverter.class)
    private List<String> userRoles;
    private boolean isReserving;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> collection = new ArrayList<>();
        for (String e : userRoles) {
            collection.add(() -> e);
        }

        return collection;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
