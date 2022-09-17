package com.example.smart_waiting.user.service;

import com.example.smart_waiting.domain.ServiceResult;
import com.example.smart_waiting.user.User;
import com.example.smart_waiting.user.model.UserInput;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    ServiceResult createUser(UserInput userInput);

    boolean existEmail(String email);

    boolean existPhone(String phone);

    ServiceResult emailAuth(String uuid);

}
