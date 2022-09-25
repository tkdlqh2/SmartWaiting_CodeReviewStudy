package com.example.smart_waiting.market.service;

import com.example.smart_waiting.domain.ServiceResult;
import com.example.smart_waiting.market.FoodRepository;
import com.example.smart_waiting.market.Market;
import com.example.smart_waiting.market.MarketRepository;
import com.example.smart_waiting.market.model.MarketRegInput;
import com.example.smart_waiting.type.MarketType;
import com.example.smart_waiting.user.User;
import com.example.smart_waiting.user.UserRepository;
import com.example.smart_waiting.user.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MarketServiceImpl implements MarketService{

    private final MarketRepository marketRepository;
    private final FoodRepository foodRepository;
    private final UserRepository userRepository;

    @Override
    public ServiceResult regMarket(UserDto userDto, MarketRegInput parameter) {
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if(optionalUser.isEmpty()){
            return ServiceResult.fail("유저가 존재하지 않습니다. Email -> "+userDto.getEmail());
        }


        Market market = Market.builder()
                .owner(optionalUser.get())
                .name(parameter.getName())
                .registrationNum(parameter.getRegistrationNum())
                .zipCode(parameter.getZipCode())
                .marketType(MarketType.of(parameter.getMarketType()))
                .build();

        marketRepository.save(market);

        return ServiceResult.success();
    }
}
