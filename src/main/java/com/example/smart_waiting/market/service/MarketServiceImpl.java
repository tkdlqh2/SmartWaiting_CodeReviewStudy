package com.example.smart_waiting.market.service;

import com.example.smart_waiting.market.FoodRepository;
import com.example.smart_waiting.market.MarketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarketServiceImpl implements MarketService{

    private final MarketRepository marketRepository;
    private final FoodRepository foodRepository;

}
