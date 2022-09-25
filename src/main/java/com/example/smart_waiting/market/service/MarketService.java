package com.example.smart_waiting.market.service;

import com.example.smart_waiting.domain.ServiceResult;
import com.example.smart_waiting.market.model.MarketDto;
import com.example.smart_waiting.market.model.MarketInfoInput;
import com.example.smart_waiting.market.model.MarketRegInput;
import com.example.smart_waiting.user.model.UserDto;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public interface MarketService {

    ServiceResult regMarket(MarketDto marketDto, MarketRegInput parameter);

    ServiceResult editInfo(MarketDto marketDto, MarketInfoInput parameter);

    MarketDto findFromRequest(HttpServletRequest request);
}
