package com.example.smart_waiting.user;

import com.example.smart_waiting.domain.ServiceResult;
import com.example.smart_waiting.security.TokenUtil;
import com.example.smart_waiting.user.model.UserDto;
import com.example.smart_waiting.user.model.UserInput;
import com.example.smart_waiting.user.model.UserPasswordResetInput;
import com.example.smart_waiting.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
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

    @GetMapping("/check-email.do")
    public @ResponseBody ResponseEntity<?> existEmail(@RequestParam String email){
        boolean result = userService.existEmail(email);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/check-phone.do")
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
        model.addAttribute("result",result.isSuccess());
        model.addAttribute("errorMessage",result.getMessage());

        return "user/email_auth";
    }

    @GetMapping("/info")
    public String info(Model model, HttpServletRequest request){
        UserDto detail = userService.findFromRequest(request);

        model.addAttribute("detail",detail);
        return "user/info";
    }

    @PatchMapping("/info")
    public String infoEdit(Model model, HttpServletRequest request, UserInput parameter){
        UserDto user = userService.findFromRequest(request);
        ServiceResult result = userService.updateInfo(user.getEmail(), parameter);

        model.addAttribute("detail",user);

        return "redirect:/user/info";
    }

    @GetMapping("/password")
    public String password(){ return "user/password"; }

    @PatchMapping("/password")
    public String passwordEdit(Model model, HttpServletRequest request, UserPasswordResetInput parameter){
        UserDto user = userService.findFromRequest(request);
        ServiceResult result = userService.updatePassword(user.getEmail(),parameter);
        model.addAttribute("result",result.isSuccess());

        return "redirect:/user/password";
    }

}
