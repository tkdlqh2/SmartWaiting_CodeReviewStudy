package com.example.smart_waiting.user.service;

import com.example.smart_waiting.components.MailComponents;
import com.example.smart_waiting.domain.ServiceResult;
import com.example.smart_waiting.type.UserStatus;
import com.example.smart_waiting.user.User;
import com.example.smart_waiting.user.model.UserDto;
import com.example.smart_waiting.user.model.UserInput;
import com.example.smart_waiting.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final MailComponents mailComponents;

    @Override
    public ServiceResult createUser(UserInput userInput) {

        Optional<User> optionalUser = userRepository.findById(userInput.getEmail());

        if(optionalUser.isPresent()){
            return ServiceResult.fail("이미 존재하는 이메일입니다.");
        }

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

//        String email = userInput.getEmail();
//        String subject = "Smart Waiting 사이트 가입을 축하드립니다. ";
//        String text = "<p>Smart Waiting 사이트 가입을 축하드립니다.<p><p>아래 링크를 클릭하셔서 가입을 완료 하세요.</p>"
//                + "<div><a target='_blank' href='http://localhost:8080/member/email-auth?id=" + uuid + "'> 가입 완료 </a></div>";
//        mailComponents.sendMail(email, subject, text);

        return ServiceResult.success();
    }
}
