package com.example.smart_waiting.user;

import com.example.smart_waiting.domain.ServiceResult;
import com.example.smart_waiting.user.model.UserInput;
import com.example.smart_waiting.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @GetMapping("/register")
    public String registerView(){
        return "user/register";
    }

    @GetMapping("/check_email.do")
    public @ResponseBody ResponseEntity<?> existEmail(@RequestParam String email){
        boolean result = userService.existEmail(email);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/check_phone.do")
    public @ResponseBody ResponseEntity<?> existPhone(@RequestParam String phone){
        boolean result = userService.existPhone(phone);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/register")
    public String register(UserInput userInput){
        userService.createUser(userInput);
        return "redirect:/";
    }

    @GetMapping("/email-auth")
    public String emailAuth(Model model, HttpServletRequest request){
        String uuid = request.getParameter("id");

        ServiceResult result = userService.emailAuth(uuid);

        return "user/email_auth";
    }

}
