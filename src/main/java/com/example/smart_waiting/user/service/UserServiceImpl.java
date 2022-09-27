package com.example.smart_waiting.user.service;

import com.example.smart_waiting.components.MailSenderAdapter;
import com.example.smart_waiting.exception.UserException;
import com.example.smart_waiting.security.TokenUtil;
import com.example.smart_waiting.type.ErrorCode;
import com.example.smart_waiting.type.UserStatus;
import com.example.smart_waiting.user.User;
import com.example.smart_waiting.user.UserRepository;
import com.example.smart_waiting.user.model.UserDto;
import com.example.smart_waiting.user.model.UserInput;
import com.example.smart_waiting.user.model.UserLoginInput;
import com.example.smart_waiting.user.model.UserPasswordResetInput;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final MailSenderAdapter mailComponents;

    @Transactional
    @Override
    public void createUser(UserInput userInput) {

        String encryptPassword = passwordEncoder.encode(userInput.getPassword());
        String uuid = UUID.randomUUID().toString();
        List<String> userRoleList = new ArrayList<>();
        userRoleList.add("normal");

        User user = User.builder()
                .name(userInput.getName())
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
    public void emailAuth(String uuid) {

        User user = userRepository.findByAuthKey(uuid).orElseThrow(
                ()-> new UserException(ErrorCode.REG_KEY_INVALIDATE));

        if(user.getAuthDate().isBefore(LocalDateTime.now())){
            throw new UserException(ErrorCode.REG_KEY_EXPIRED);
        }

        user.setUserStatus(UserStatus.APPROVED);
        user.setAuthKey(null);
        user.setAuthDate(null);
        userRepository.save(user);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByEmail(username).orElseThrow(
                ()-> new UsernameNotFoundException("이메일이 없습니다. -> "+username));
    }

    @Override
    public UserDto login(UserLoginInput parameter) {
        User user = userRepository.findByEmail(parameter.getEmail()).orElseThrow(
                ()-> new UsernameNotFoundException("이메일이 없습니다. -> "+parameter.getEmail()));

        if(!passwordEncoder.matches(parameter.getPassword(), user.getPassword())){
            throw new UserException(ErrorCode.PASSWORD_NOT_MATCH);
        }
        System.out.println("로그인을 하였습니다.");
        return UserDto.of(user);
    }

    @Override
    public String findEmailFromRequest(HttpServletRequest request) {
        return TokenUtil.getEmail(TokenUtil.resolveTokenFromRequest(request));
    }

    @Override
    public UserDto findFromEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                ()-> new UsernameNotFoundException("이메일이 없습니다. -> "+email));
        return UserDto.of(user);
    }

    @Override
    public void updateInfo(String email, UserInput parameter) {

        User user = userRepository.findByEmail(email).orElseThrow(
                ()->new UsernameNotFoundException("이메일이 없습니다. -> "+email));
        user.setPhone(parameter.getPhone());
        userRepository.save(user);
    }


    @Override
    public void updatePassword(String email, UserPasswordResetInput parameter) {

        User user = userRepository.findByEmail(email).orElseThrow(
                ()->new UsernameNotFoundException("이메일이 없습니다. -> "+email));

        if(!passwordEncoder.matches(parameter.getPassword(), user.getPassword())){
            throw new UserException(ErrorCode.PASSWORD_NOT_MATCH);
        }

        String encryptedPassword = passwordEncoder.encode(parameter.getNewPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);
    }
}