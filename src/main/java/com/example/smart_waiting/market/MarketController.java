package com.example.smart_waiting.market;

import com.example.smart_waiting.market.model.MarketDto;
import com.example.smart_waiting.market.model.MarketInfoInput;
import com.example.smart_waiting.market.model.MarketRegInput;
import com.example.smart_waiting.market.service.MarketService;
import com.example.smart_waiting.security.TokenUtil;
import com.example.smart_waiting.util.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

// user를 가져오는 것을 request에서 market을 가져오는 것으로 refactoring 필요
@RequiredArgsConstructor
@Controller
@RequestMapping("/market")
public class MarketController {

    private final MarketService marketService;

    @GetMapping("/reg-form")
    public String regForm(){
        return "market/reg_form";
    }

    @PostMapping("/reg")
    public String reg(Model model, HttpServletRequest request, MarketRegInput parameter){
        String email = TokenUtil.findEmailFromRequest(request);
        marketService.regMarket(email, parameter);
        return "redirect:/market/reg";
    }

    @GetMapping("/info")
    public String getInfo(Model model, HttpServletRequest request){
        String email = TokenUtil.findEmailFromRequest(request);
        MarketDto marketDto = marketService.findDtoFromEmail(email);
        model.addAttribute("info",marketDto);
        return "market/info";
    }

    @PatchMapping("/info")
    public String editInfo(Model model, HttpServletRequest request,
                           MultipartFile file, MarketInfoInput parameter){

        String email = TokenUtil.findEmailFromRequest(request);
        FileUtils.SetInputFileNames(file,parameter);
        marketService.editInfo(email,parameter);

        return "redirect:/market/info";
    }

//    @PostMapping
//    public String addFood(Model model, HttpServletRequest request,
//                          MultipartFile file, FoodInfoInput parameter){
//
//        UserDto userDto = userService.findFromRequest(request);
//
//
//
//    }

}
