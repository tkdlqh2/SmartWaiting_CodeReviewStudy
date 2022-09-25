package com.example.smart_waiting.market.model;

import com.example.smart_waiting.user.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MarketRegInput {

    private String name;
    private String registrationNum;
    private Long zipCode;
    private String detailAddress;
    private String marketType;
}
