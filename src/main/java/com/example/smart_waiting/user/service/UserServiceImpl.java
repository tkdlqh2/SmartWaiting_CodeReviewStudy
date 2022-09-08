package com.example.smart_waiting.user.service;

import com.example.smart_waiting.type.UserStatus;
import com.example.smart_waiting.user.User;
import com.example.smart_waiting.user.model.UserDto;
import com.example.smart_waiting.user.model.UserInput;
import com.example.smart_waiting.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public UserDto createUser(UserInput userInput) {

        String encryptPassword = BCrypt.hashpw(userInput.getPassword(),BCrypt.gensalt());
        String uuid = UUID.randomUUID().toString();

        User user = User.builder()
                .email(userInput.getEmail())
                .password(encryptPassword)
                .phone(userInput.getPhone())
                .authKey(uuid)
                .authDate(LocalDateTime.now().plusDays(3))
                .userStatus(UserStatus.UNAPPROVED)
                .build();

        userRepository.save(user);

        return UserDto.of(user);
    }
}
