package com.example.smart_waiting.market;

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
    private String location;
    private String type;
    private String registrationNum;
    private Long zipCode;
    private String detailAddress;
    @Enumerated(EnumType.STRING)
    private MarketType marketType;

    private Long openHour;
    private Long closeHour;
    private List<String> openWeekDay;
    private Integer maximumRegNum;
    private String imagePath;
    private MarketStatus marketStatus;
    private boolean isOpen;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Food> menu = new ArrayList<>();

}
