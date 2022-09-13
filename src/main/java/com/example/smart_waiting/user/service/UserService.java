package com.example.smart_waiting.user.service;

import com.example.smart_waiting.domain.ServiceResult;
import com.example.smart_waiting.user.model.UserInput;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ServiceResult createUser(UserInput userInput);

    int existEmail(String email);

    int existPhone(String phone);

}
