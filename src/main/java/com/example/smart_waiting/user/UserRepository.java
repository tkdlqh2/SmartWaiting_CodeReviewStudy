package com.example.smart_waiting.user;

import com.example.smart_waiting.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}
