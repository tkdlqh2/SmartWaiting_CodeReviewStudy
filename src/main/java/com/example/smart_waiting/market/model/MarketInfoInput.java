package com.example.smart_waiting.market.model;

import com.example.smart_waiting.domain.InputWithFile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MarketInfoInput extends InputWithFile {
    private Long openHour;
    private Long closeHour;
    private List<String> openWeekDay;
    private Integer maximumRegNum;
    private boolean isOpen;
}
