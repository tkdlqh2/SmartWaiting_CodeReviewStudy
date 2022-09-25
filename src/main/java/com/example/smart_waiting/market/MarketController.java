package com.example.smart_waiting.market;

import com.example.smart_waiting.market.model.MarketRegInput;
import com.example.smart_waiting.market.service.MarketService;
import com.example.smart_waiting.user.model.UserDto;
import com.example.smart_waiting.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Controller
@RequestMapping("/market")
public class MarketController {

//    private final UserService userService;
    private final MarketService marketService;

    @GetMapping("/reg-form")
    public String regForm(){
        return "market/reg_form";
    }

    @PostMapping("/reg")
    public String reg(HttpServletRequest request, MarketRegInput parameter){
//        UserDto userDto = userService.findFromRequest(request);
        marketService.regMarket(userDto, parameter);

        return "";
    }
}
