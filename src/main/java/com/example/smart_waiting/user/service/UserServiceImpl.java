package com.example.smart_waiting.user.service;

import com.example.smart_waiting.components.MailComponents;
import com.example.smart_waiting.domain.ServiceResult;
import com.example.smart_waiting.exception.PasswordNotMatchException;
import com.example.smart_waiting.type.UserStatus;
import com.example.smart_waiting.user.User;
import com.example.smart_waiting.user.UserRepository;
import com.example.smart_waiting.user.model.UserInput;
import com.example.smart_waiting.user.model.UserLoginInput;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final MailComponents mailComponents;

    @Transactional
    @Override
    public ServiceResult createUser(UserInput userInput) {

        String encryptPassword = passwordEncoder.encode(userInput.getPassword());
        String uuid = UUID.randomUUID().toString();
        List<String> userRoleList = new ArrayList<>();
        userRoleList.add("normal");

        User user = User.builder()
                .email(userInput.getEmail())
                .password(encryptPassword)
                .phone(userInput.getPhone())
                .authKey(uuid)
                .authDate(LocalDateTime.now().plusDays(3))
                .userStatus(UserStatus.UNAPPROVED)
                .userRoles(userRoleList)
                .build();

        userRepository.save(user);

        String email = userInput.getEmail();
        String subject = "Smart Waiting 사이트 가입을 축하드립니다. ";
        String text = "<p>Smart Waiting 사이트 가입을 축하드립니다.<p><p>아래 링크를 클릭하셔서 가입을 완료 하세요.</p>"
                + "<div><a target='_blank' href='http://localhost:8080/user/email-auth?id=" + uuid + "'> 가입 완료 </a></div>";
        mailComponents.sendMail(email, subject, text);

        return ServiceResult.success();
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existPhone(String phone) {
        return userRepository.existsByPhone(phone);
    }

    // 회원 가입 신청을 넣고 인증기한이 만료된 사용자에 대한 처리 필요!
    @Transactional
    @Override
    public ServiceResult emailAuth(String uuid) {
        Optional<User> optionalUser = userRepository.findByAuthKey(uuid);
        if(optionalUser.isEmpty()){
            // 인증키가 유효하지 않음!
            return ServiceResult.fail("인증키가 유효하지 않습니다.");
        }

        User user = optionalUser.get();

        if(user.getAuthDate().isBefore(LocalDateTime.now())){
            // 만료된 인증키!
            return ServiceResult.fail("인증키가 만료되었습니다.");
        }

        user.setUserStatus(UserStatus.APPROVED);
        user.setAuthKey(null);
        user.setAuthDate(null);
        userRepository.save(user);

        return ServiceResult.success();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByEmail(username).orElseThrow(
                ()-> new UsernameNotFoundException("이메일이 없습니다. -> "+username));
    }

    @Override
    public User login(UserLoginInput parameter) {
        User user = userRepository.findByEmail(parameter.getEmail()).orElseThrow(
                ()-> new UsernameNotFoundException("이메일이 없습니다. -> "+parameter.getEmail()));

        if(!passwordEncoder.matches(parameter.getPassword(), user.getPassword())){
            throw new PasswordNotMatchException();
        }
        System.out.println("로그인을 하였습니다.");
        return user;
    }
}
