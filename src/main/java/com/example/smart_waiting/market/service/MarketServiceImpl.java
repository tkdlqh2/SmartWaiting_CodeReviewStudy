package com.example.smart_waiting.market.service;

import com.example.smart_waiting.domain.ServiceResult;
import com.example.smart_waiting.exception.MarketNotFoundException;
import com.example.smart_waiting.market.FoodRepository;
import com.example.smart_waiting.market.Market;
import com.example.smart_waiting.market.MarketRepository;
import com.example.smart_waiting.market.model.MarketDto;
import com.example.smart_waiting.market.model.MarketInfoInput;
import com.example.smart_waiting.market.model.MarketRegInput;
import com.example.smart_waiting.type.MarketStatus;
import com.example.smart_waiting.type.MarketType;
import com.example.smart_waiting.user.User;
import com.example.smart_waiting.user.UserRepository;
import com.example.smart_waiting.user.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MarketServiceImpl implements MarketService{

    private final MarketRepository marketRepository;
    private final FoodRepository foodRepository;
    private final UserRepository userRepository;

    @Override
    public ServiceResult regMarket(MarketDto marketDto, MarketRegInput parameter) {
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if(optionalUser.isEmpty()){
            return ServiceResult.fail("유저가 존재하지 않습니다. -> "+userDto.getEmail());
        }


        Market market = Market.builder()
                .owner(optionalUser.get())
                .name(parameter.getName())
                .registrationNum(parameter.getRegistrationNum())
                .zipCode(parameter.getZipCode())
                .marketType(MarketType.of(parameter.getMarketType()))
                .marketStatus(MarketStatus.UNAPPROVED)
                .build();

        marketRepository.save(market);

        return ServiceResult.success();
    }

    @Override
    public ServiceResult editInfo(MarketDto marketDto, MarketInfoInput parameter) {
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if(optionalUser.isEmpty()){return ServiceResult.fail("유저가 존재하지 않습니다. ->"+userDto.getEmail());}

        Optional<Market> optionalMarket = marketRepository.findByUser(optionalUser.get());
        if(optionalMarket.isEmpty()){return ServiceResult.fail("음식점을 등록하지 않은 유저입니다.");}

        Market market = optionalMarket.get();
        market.setOpenHour(parameter.getOpenHour());
        market.setCloseHour(parameter.getCloseHour());
        market.setOpenWeekDay(parameter.getOpenWeekDay());
        market.setMaximumRegNum(parameter.getMaximumRegNum());
        market.setImagePath(parameter.getImagePath());
        market.setOpen(parameter.isOpen());

        return ServiceResult.success();
    }


    public MarketDto findFromRequest(HttpServletRequest request) {

        return null;
    }
}
