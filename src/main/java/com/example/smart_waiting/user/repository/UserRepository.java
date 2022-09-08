package com.example.smart_waiting.user.repository;

import com.example.smart_waiting.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
