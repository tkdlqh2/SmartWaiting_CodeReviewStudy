package com.example.smart_waiting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void createUserSuccess(){
        //given
        UserInput userInput = UserInput.builder()
                .email("yhj7124@naver.com")
                .password("1111")
                .phone("010-1111-2222");

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        //when
        UserDto userDto = userService.createUser(userInput);

        //then
        verify(UserRepository,times(1)).save(captor.capture());
        assertEquals("yhj7124@naver.com",userDto.getEmail());
        assertEquals("1111",userDto.getPassword());
        assertEquals("010-1111-2222",userDto.getPhone());
    }

}
