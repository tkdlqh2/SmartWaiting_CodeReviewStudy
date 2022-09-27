package com.example.smart_waiting.security;

import com.example.smart_waiting.user.model.UserDto;
import com.example.smart_waiting.user.model.UserLoginInput;
import com.example.smart_waiting.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public String loginProc(Model model, HttpServletResponse response, UserLoginInput parameter){
        UserDto user;
        try {
            user = userService.login(parameter);
        } catch (Exception e){
            model.addAttribute("error",e.getMessage());
            return "user/loginForm";
        }

        var jwtToken = tokenProvider.generateToken(user.getEmail(),user.getUserRoles());
        response.addHeader("Authorization","Bearer "+jwtToken);
        return"redirect:/";
    }
}
