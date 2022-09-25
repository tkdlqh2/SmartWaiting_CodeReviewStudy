package com.example.smart_waiting.market.model;

import com.example.smart_waiting.market.Food;
import com.example.smart_waiting.market.Market;
import com.example.smart_waiting.type.MarketStatus;
import com.example.smart_waiting.type.MarketType;
import com.example.smart_waiting.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MarketDto {

    private User owner;

    private String name;
    private String registrationNum;
    private Long zipCode;
    private String detailAddress;
    private MarketType marketType;
    private MarketStatus marketStatus;

    private Long openHour;
    private Long closeHour;
    private List<String> openWeekDay;
    private Integer maximumRegNum;
    private String imagePath;
    private boolean isOpen;

    private List<Food> menu;

    public static MarketDto of(Market market){
        return MarketDto.builder()
                .owner(market.getOwner())
                .name(market.getName())
                .registrationNum(market.getRegistrationNum())
                .zipCode(market.getZipCode())
                .detailAddress(market.getDetailAddress())
                .marketType(market.getMarketType())
                .marketStatus(market.getMarketStatus())
                .openHour(market.getOpenHour())
                .closeHour(market.getCloseHour())
                .openWeekDay(market.getOpenWeekDay())
                .maximumRegNum(market.getMaximumRegNum())
                .imagePath(market.getImagePath())
                .isOpen(market.isOpen())
                .menu(market.getMenu())
                .build();
    }
}
