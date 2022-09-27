package com.example.smart_waiting.market.model;

import com.example.smart_waiting.domain.InputWithFile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodInfoInput extends InputWithFile {

    private String name;
    private String price;

}
