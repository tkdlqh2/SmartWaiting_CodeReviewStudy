package com.example.smart_waiting.user.service;

import com.example.smart_waiting.user.User;
import com.example.smart_waiting.user.model.UserInput;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User createUser(UserInput userInput);

    boolean existEmail(String email);

    boolean existPhone(String phone);

    User emailAuth(String uuid);

}
