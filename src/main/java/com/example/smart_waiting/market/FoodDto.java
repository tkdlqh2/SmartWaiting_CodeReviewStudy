package com.example.smart_waiting.market;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodDto {

    private Market market;

    private String name;
    private String price;
    private String imagePath;

    public FoodDto of(Food food){
        return FoodDto.builder()
                .name(food.getName())
                .price(food.getPrice())
                .imagePath(food.getImagePath())
                .build();
    }

}
