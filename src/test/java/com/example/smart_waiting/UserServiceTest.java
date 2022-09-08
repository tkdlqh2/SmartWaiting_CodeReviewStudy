package com.example.smart_waiting;

import com.example.smart_waiting.domain.ServiceResult;
import com.example.smart_waiting.user.User;
import com.example.smart_waiting.user.model.UserDto;
import com.example.smart_waiting.user.model.UserInput;
import com.example.smart_waiting.user.repository.UserRepository;
import com.example.smart_waiting.user.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setup(){
        User user = User.builder()
                .email("yhj1234@naver.com")
                .phone("010-1111-1111")
                .build();

        given(userRepository.findById("yhj1234@naver.com")).willReturn(Optional.of(user));
    }

    @Test
    void createUserSuccess(){
        //given
        UserInput userInput = UserInput.builder()
                .email("yhj7124@naver.com")
                .password("1111")
                .phone("010-1111-2222")
                .build();

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        //when
        ServiceResult serviceResult = userService.createUser(userInput);

        //then
        verify(userRepository,times(1)).save(captor.capture());
        assertTrue(serviceResult.isSuccess());
    }

    @Test
    @DisplayName("유저 생성 실패 - 이미 등록된 이메일")
    void createUser_UserExist(){
        //given
        UserInput userInput = UserInput.builder()
                .email("yhj1234@naver.com")
                .password("1111")
                .phone("010-1111-2222")
                .build();

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        //when
        ServiceResult serviceResult = userService.createUser(userInput);

        //then
        verify(userRepository,times(0)).save(captor.capture());
        assertFalse(serviceResult.isSuccess());
    }
}
