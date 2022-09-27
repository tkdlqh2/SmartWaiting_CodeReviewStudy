package com.example.smart_waiting.market.service;

import com.example.smart_waiting.market.FoodDto;
import com.example.smart_waiting.market.model.FoodInfoInput;
import com.example.smart_waiting.market.model.MarketDto;
import com.example.smart_waiting.market.model.MarketInfoInput;
import com.example.smart_waiting.market.model.MarketRegInput;
import org.springframework.stereotype.Service;

@Service
public interface MarketService {

    void regMarket(String email, MarketRegInput parameter);

    void editInfo(String email, MarketInfoInput parameter);

    MarketDto findDtoFromEmail(String email);

    FoodDto addFood(String email, FoodInfoInput parameter);
}
