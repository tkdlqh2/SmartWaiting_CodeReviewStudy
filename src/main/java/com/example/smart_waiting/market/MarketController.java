package com.example.smart_waiting.market;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/market")
public class MarketController {

    @GetMapping("reg-form")
    public String regForm(){
        return "market/reg_form";
    }
}
