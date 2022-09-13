package com.example.smart_waiting.user;

import com.example.smart_waiting.user.model.UserInput;
import com.example.smart_waiting.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/register")
    public String registerView(){
        return "user.register";
    }

    @GetMapping("/check_email.do")
    public @ResponseBody ResponseEntity<?> existEmail(@RequestParam String email){
        int result = userService.existEmail(email);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/check_phone.do")
    public @ResponseBody ResponseEntity<?> existPhone(@RequestParam String phone){
        int result = userService.existPhone(phone);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/register")
    public String register(UserInput userInput){
        var result = userService.createUser(userInput);
        return "redirect:/";
    }

}
