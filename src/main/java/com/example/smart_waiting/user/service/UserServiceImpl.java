package com.example.smart_waiting.user.service;

import com.example.smart_waiting.components.MailComponents;
import com.example.smart_waiting.type.UserStatus;
import com.example.smart_waiting.user.User;
import com.example.smart_waiting.user.UserRepository;
import com.example.smart_waiting.user.model.UserInput;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final MailComponents mailComponents;

    @Override
    public User createUser(UserInput userInput) {

        String encryptPassword = passwordEncoder.encode(userInput.getPassword());
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

        String email = userInput.getEmail();
        String subject = "Smart Waiting 사이트 가입을 축하드립니다. ";
        String text = "<p>Smart Waiting 사이트 가입을 축하드립니다.<p><p>아래 링크를 클릭하셔서 가입을 완료 하세요.</p>"
                + "<div><a target='_blank' href='http://localhost:8080/user/email-auth?id=" + uuid + "'> 가입 완료 </a></div>";
        mailComponents.sendMail(email, subject, text);

        return user;
    }

    @Override
    public boolean existEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existPhone(String phone) {
        return userRepository.existsByPhone(phone);
    }

    // 회원 가입 신청을 넣고 인증을 하지 않은 사용자에 대한 처리 필요!
    @Override
    public User emailAuth(String uuid) {
        Optional<User> optionalUser = userRepository.findByAuthKey(uuid);
        if(!optionalUser.isPresent()){
            // 인증키가 유효하지 않음!
        }

        User user = optionalUser.get();

        if(user.getAuthDate().isAfter(LocalDateTime.now())){
            // 만료된 인증키!
        }

        user.setUserStatus(UserStatus.APPROVED);
        user.setAuthKey(null);
        user.setAuthDate(null);
        userRepository.save(user);

        return user;
    }
}
