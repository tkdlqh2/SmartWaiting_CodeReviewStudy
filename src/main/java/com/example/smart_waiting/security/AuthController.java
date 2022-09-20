package com.example.smart_waiting.security;

import com.example.smart_waiting.user.model.UserDto;
import com.example.smart_waiting.user.model.UserLoginInput;
import com.example.smart_waiting.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {

    private final UserService userService;
    private final TokenProvider tokenProvider;

    @GetMapping("/loginForm")
    public String loginForm(HttpServletRequest request){
        return "user/loginForm";
    }

    @PostMapping("/login-proc")
    public String loginProc(UserLoginInput parameter){
        UserDto user = userService.login(parameter);
        tokenProvider.generateToken(user.getEmail(),user.getUserRoles());
        return"redirect:/";
    }
}
