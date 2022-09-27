package com.example.smart_waiting.user.service;

import com.example.smart_waiting.user.model.UserDto;
import com.example.smart_waiting.user.model.UserInput;
import com.example.smart_waiting.user.model.UserLoginInput;
import com.example.smart_waiting.user.model.UserPasswordResetInput;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public interface UserService extends UserDetailsService {

    void createUser(UserInput userInput);

    boolean existEmail(String email);

    boolean existPhone(String phone);

    void emailAuth(String uuid);

    UserDto login(UserLoginInput parameter);

    UserDto findFromEmail(String email);

    void updateInfo(String email,UserInput parameter);

    void updatePassword(String email, UserPasswordResetInput parameter);
}
