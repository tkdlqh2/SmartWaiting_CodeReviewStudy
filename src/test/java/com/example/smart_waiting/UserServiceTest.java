package com.example.smart_waiting;

import com.example.smart_waiting.components.MailComponents;
import com.example.smart_waiting.domain.ServiceResult;
import com.example.smart_waiting.security.TokenUtil;
import com.example.smart_waiting.type.UserStatus;
import com.example.smart_waiting.user.User;
import com.example.smart_waiting.user.UserRepository;
import com.example.smart_waiting.user.model.UserDto;
import com.example.smart_waiting.user.model.UserInput;
import com.example.smart_waiting.user.service.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private MailComponents mailComponents;

    @Mock
    private TokenUtil tokenUtil;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void createUserSuccess(){
        //given
        UserInput userInput = UserInput.builder()
                .email("yhj7124@naver.com")
                .password("1111")
                .phone("010-1111-2222")
                .build();

        given(passwordEncoder.encode("1111")).willReturn("2222");
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        //when
        ServiceResult result = userService.createUser(userInput);

        //then
        assertTrue(result.isSuccess());
        verify(userRepository,times(1)).save(captor.capture());
    }

    @Test
    void existsByEmailExist(){
        //given
        given(userRepository.existsByEmail("yhj7124@naver.com")).willReturn(true);
        //when
        //then
        assertTrue(userService.existEmail("yhj7124@naver.com"));
    }

    @Test
    void existsByEmailNotExist(){
        //given
        //when
        //then
        assertFalse(userService.existEmail("yhj7124@naver.com"));
    }

    @Test
    void existsByPhoneExists(){
        //given
        given(userRepository.existsByPhone("010-1111-2222")).willReturn(true);

        //when
        //then
        assertTrue(userService.existPhone("010-1111-2222"));
    }

    @Test
    void existsByPhoneNotExist(){
        //given
        //when
        //then
        assertFalse(userService.existPhone("010-1111-2222"));
    }

    @Test
    void emailAuthSuccess(){
        //given
        given(userRepository.findByAuthKey("인증키~"))
                .willReturn(Optional.of(User.builder()
                        .id(1L)
                        .userStatus(UserStatus.UNAPPROVED)
                        .authKey("인증키~")
                        .authDate(LocalDateTime.now().plusDays(1))
                        .build()));

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        //when
        ServiceResult result = userService.emailAuth("인증키~");

        //then
        assertTrue(result.isSuccess());
        verify(userRepository,times(1)).save(captor.capture());
    }

    @Test
    @DisplayName("이메일 인증 실패 - 인증키가 존재하지 않음")
    void emailAuthFailure_noneKey(){
        //given
        //when
        ServiceResult result = userService.emailAuth("인증키~");

        //then
        assertFalse(result.isSuccess());
        assertEquals("인증키가 유효하지 않습니다.",result.getMessage());
    }

    @Test
    @DisplayName("이메일 인증 실패 - 인증키가 만료됨")
    void emailAuthFailure_keyExpired(){
        //given
        given(userRepository.findByAuthKey("인증키~"))
                .willReturn(Optional.of(User.builder()
                        .id(1L)
                        .userStatus(UserStatus.UNAPPROVED)
                        .authKey("인증키~")
                        .authDate(LocalDateTime.now().minusDays(1))
                        .build()));


        //when
        ServiceResult result = userService.emailAuth("인증키~");

        //then
        assertFalse(result.isSuccess());
        assertEquals("인증키가 만료되었습니다.",result.getMessage());
    }

    @Test
    void findFromRequestSuccess(){
        //given

        HttpServletRequest request = mock(HttpServletRequest.class);
        given(tokenUtil.getEmail(any())).willReturn("yhj7124@naver.com");

        User user = User.builder()
                .id(1L)
                .email("yhj7124@naver.com")
                .name("유형진").build();

        given(userRepository.findByEmail("yhj7124@naver.com"))
                .willReturn(Optional.of(user));
        //when

        UserDto userDto = userService.findFromRequest(request);

        //then
        assertEquals("yhj7124@naver.com",userDto.getEmail());
        assertEquals("유형진",userDto.getName());

    }

    @Test
    void findFromRequestFail_NoUser(){
        //given
        HttpServletRequest request = mock(HttpServletRequest.class);
        given(tokenUtil.getEmail(any())).willReturn("yhj7124@naver.com");

        //when
        //then
        assertThrows(UsernameNotFoundException.class,()->userService.findFromRequest(request));
    }


    @Test
    void updateInfoSuccess(){
        //given
        UserInput userInput = new UserInput();
        userInput.setPhone("010-2222-3333");

        User user = User.builder()
                .name("유형진")
                .email("yhj7124@naver.com")
                .phone("010-1111-2222")
                .build();

        given(userRepository.findByEmail("yhj7124@naver.com"))
                .willReturn(Optional.of(user));


        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        //when
        ServiceResult result = userService.updateInfo("yhj7124@naver.com",userInput);

        //then
        assertTrue(result.isSuccess());
        verify(userRepository,times(1)).save(captor.capture());
    }

    @Test
    void updateInfoFail_NoUser(){
        //given
        UserInput userInput = new UserInput();
        userInput.setPhone("010-2222-3333");

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        //when
        ServiceResult result = userService.updateInfo("yhj7124@naver.com",userInput);

        //then
        assertFalse(result.isSuccess());
        verify(userRepository,times(0)).save(captor.capture());
    }

    @Test
    void updatePasswordSuccess(){
        //given
        //when
        //then
    }


}
