package com.example.smart_waiting.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MarketType {
    KOREAN("한식"), SNACK("분식"), WESTERN("양식"), INTERNATIONAL("세계음식"),
    DESSERT("디저트"), CAFE("카페"), PUB("술집"),BRUNCH("브런치");

    public final String details;

    public static MarketType of(String string){
        for (MarketType marketType:MarketType.values()){
            if(string.equals(marketType.getDetails())){
                return marketType;
            }
        }

        return null;
    }
}
