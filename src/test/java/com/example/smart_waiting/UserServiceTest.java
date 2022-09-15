package com.example.smart_waiting;

import com.example.smart_waiting.user.User;
import com.example.smart_waiting.user.UserRepository;
import com.example.smart_waiting.user.model.UserInput;
import com.example.smart_waiting.user.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

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
        User user = userService.createUser(userInput);

        //then
        assertEquals("yhj7124@naver.com",user.getEmail());
        assertEquals("2222",user.getPassword());
        assertEquals("010-1111-2222",user.getPhone());
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
}
