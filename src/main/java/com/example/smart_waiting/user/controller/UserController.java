package com.example.smart_waiting.user.controller;

import com.example.smart_waiting.domain.ServiceResult;
import com.example.smart_waiting.user.model.UserInput;
import com.example.smart_waiting.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/member/register")
    public String register(){

        return "member/register";
    }

    @PostMapping("/member/register")
    public String registerSubmit(Model model,UserInput userInput){

        ServiceResult serviceResult = userService.createUser(userInput);
        if(!serviceResult.isSuccess()){
            model.addAttribute("errorCode", serviceResult.getErrorCode());
            return "member/register_error";
        }

        return "member/register_complete";
    }

}
