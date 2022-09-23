package com.example.smart_waiting.user.service;

import com.example.smart_waiting.domain.ServiceResult;
import com.example.smart_waiting.user.User;
import com.example.smart_waiting.user.model.UserDto;
import com.example.smart_waiting.user.model.UserInput;
import com.example.smart_waiting.user.model.UserLoginInput;
import com.example.smart_waiting.user.model.UserPasswordResetInput;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public interface UserService extends UserDetailsService {

    ServiceResult createUser(UserInput userInput);

    boolean existEmail(String email);

    boolean existPhone(String phone);

    ServiceResult emailAuth(String uuid);

    UserDto login(UserLoginInput parameter);

    UserDto findFromRequest(HttpServletRequest request);

    ServiceResult updateInfo(String email,UserInput parameter);

    ServiceResult updatePassword(String email, UserPasswordResetInput parameter);
}
