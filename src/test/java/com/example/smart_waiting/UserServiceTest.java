package com.example.smart_waiting;

import com.example.smart_waiting.domain.ServiceResult;
import com.example.smart_waiting.user.User;
import com.example.smart_waiting.user.model.UserDto;
import com.example.smart_waiting.user.model.UserInput;
import com.example.smart_waiting.user.repository.UserRepository;
import com.example.smart_waiting.user.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

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

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        //when
        ServiceResult serviceResult = userService.createUser(userInput);

        //then
        verify(userRepository,times(1)).save(captor.capture());
        assertTrue(serviceResult.isSuccess());
    }

}
