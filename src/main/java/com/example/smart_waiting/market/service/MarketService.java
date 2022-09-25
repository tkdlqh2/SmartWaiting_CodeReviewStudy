package com.example.smart_waiting.market.service;

import com.example.smart_waiting.domain.ServiceResult;
import com.example.smart_waiting.market.model.MarketDto;
import com.example.smart_waiting.market.model.MarketInfoInput;
import com.example.smart_waiting.market.model.MarketRegInput;
import com.example.smart_waiting.user.model.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface MarketService {

    ServiceResult regMarket(UserDto userDto, MarketRegInput parameter);

    MarketDto getInfo(UserDto userDto);

    ServiceResult editInfo(UserDto userDto, MarketInfoInput parameter);
}
