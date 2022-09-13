package com.example.smart_waiting.user.service;

import com.example.smart_waiting.components.MailComponents;
import com.example.smart_waiting.domain.ServiceResult;
import com.example.smart_waiting.type.UserStatus;
import com.example.smart_waiting.user.User;
import com.example.smart_waiting.user.model.UserInput;
import com.example.smart_waiting.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    @Autowired
    private final MailComponents mailComponents;

    @Override
    public ServiceResult createUser(UserInput userInput) {

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
//
//        String email = userInput.getEmail();
//        String subject = "Smart Waiting 사이트 가입을 축하드립니다. ";
//        String text = "<p>Smart Waiting 사이트 가입을 축하드립니다.<p><p>아래 링크를 클릭하셔서 가입을 완료 하세요.</p>"
//                + "<div><a target='_blank' href='http://localhost:8080/member/email-auth?id=" + uuid + "'> 가입 완료 </a></div>";
//        mailComponents.sendMail(email, subject, text);

        return ServiceResult.success();
    }

    @Override
    public int existEmail(String email) {
        if(userRepository.existsByEmail(email)){
            return 1;
        }
        return 0;
    }

    @Override
    public int existPhone(String phone) {
        if(userRepository.existsByPhone(phone)){
            return 1;
        }
        return 0;
    }
}
