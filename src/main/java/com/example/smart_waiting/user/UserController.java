package com.example.smart_waiting.user;

import com.example.smart_waiting.user.model.UserInput;
import com.example.smart_waiting.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원 가입 폼 이동
    @GetMapping("/user/userJoinForm.do")
    public String memberJoinForm() {
        return "/user/userJoinForm";
    }

    // 이메일 중복 검사(AJAX)
    @PostMapping("/user/check-email.do")
    public void checkEmail(@RequestParam("email") String email, HttpServletResponse response){
        userService.checkEmail(email, response);
    }

    // 전화번호 검사(AJAX)
    @PostMapping("/user/check-phone.do")
    public void checkPhone(@RequestParam("phone") String phone, HttpServletResponse response) {
        userService.checkPhone(phone, response);
    }

    // 회원 가입
    @PostMapping("/user/create-user.do")
    public String createUser(UserInput userInput) {
        userService.createUser(userInput);
        return "redirect:./";
    }
}
