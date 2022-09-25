package com.example.smart_waiting.market;

import com.example.smart_waiting.domain.ListFoodConverter;
import com.example.smart_waiting.domain.ListStringConverter;
import com.example.smart_waiting.type.MarketStatus;
import com.example.smart_waiting.type.MarketType;
import com.example.smart_waiting.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Market {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User owner;

    private String name;
    private String registrationNum;
    private Long zipCode;
    private String detailAddress;
    @Enumerated(EnumType.STRING)
    private MarketType marketType;
    @Enumerated(EnumType.STRING)
    private MarketStatus marketStatus;

    private Long openHour;
    private Long closeHour;
    @Convert(converter = ListStringConverter.class)
    private List<String> openWeekDay;
    private Integer maximumRegNum;
    private String imagePath;
    private boolean isOpen;

    @OneToMany(fetch = FetchType.EAGER)
    @Convert(converter = ListFoodConverter.class)
    private List<Food> menu = new ArrayList<>();

}
