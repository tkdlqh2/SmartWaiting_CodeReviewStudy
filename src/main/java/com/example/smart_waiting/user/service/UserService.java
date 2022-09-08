package com.example.smart_waiting.user.service;

import com.example.smart_waiting.user.model.UserDto;
import com.example.smart_waiting.user.model.UserInput;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDto createUser(UserInput userInput);
}
