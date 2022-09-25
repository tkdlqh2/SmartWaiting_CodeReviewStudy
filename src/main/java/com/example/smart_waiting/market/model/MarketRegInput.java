package com.example.smart_waiting.market.model;

import com.example.smart_waiting.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MarketRegInput {

    private String name;
    private String location;
    private String type;
    private String registrationNum;
    private Long zipCode;
    private String detailAddress;

}
