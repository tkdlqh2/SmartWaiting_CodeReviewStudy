package com.example.smart_waiting.market.service;

import com.example.smart_waiting.exception.MarketException;
import com.example.smart_waiting.market.FoodRepository;
import com.example.smart_waiting.market.Market;
import com.example.smart_waiting.market.MarketRepository;
import com.example.smart_waiting.market.model.MarketDto;
import com.example.smart_waiting.market.model.MarketInfoInput;
import com.example.smart_waiting.market.model.MarketRegInput;
import com.example.smart_waiting.type.ErrorCode;
import com.example.smart_waiting.type.MarketStatus;
import com.example.smart_waiting.type.MarketType;
import com.example.smart_waiting.user.User;
import com.example.smart_waiting.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarketServiceImpl implements MarketService{

    private final MarketRepository marketRepository;
    private final FoodRepository foodRepository;
    private final UserRepository userRepository;


    @Override
    public void regMarket(String email, MarketRegInput parameter) {

        User user = userRepository.findByEmail(email).orElseThrow(
                ()-> new UsernameNotFoundException("유저가 존재하지 않습니다. -> "+ email));

        Market market = Market.builder()
                .owner(user)
                .name(parameter.getName())
                .registrationNum(parameter.getRegistrationNum())
                .zipCode(parameter.getZipCode())
                .marketType(MarketType.of(parameter.getMarketType()))
                .marketStatus(MarketStatus.UNAPPROVED)
                .build();

        marketRepository.save(market);

    }

    @Override
    public void editInfo(String email, MarketInfoInput parameter) {
        Market market = findFromEmail(email);

        market.setOpenHour(parameter.getOpenHour());
        market.setCloseHour(parameter.getCloseHour());
        market.setOpenWeekDay(parameter.getOpenWeekDay());
        market.setMaximumRegNum(parameter.getMaximumRegNum());
        market.setImagePath(parameter.getImagePath());
        market.setOpen(parameter.isOpen());
    }

    @Override
    public MarketDto findDtoFromEmail(String email) {
        return MarketDto.of(findFromEmail(email));
    }

    private Market findFromEmail(String email){
        User user = userRepository.findByEmail(email).orElseThrow(
                ()-> new UsernameNotFoundException("유저가 존재하지 않습니다. ->"+ email));

        return marketRepository.findByUser(user).orElseThrow(
                ()-> new MarketException(ErrorCode.NO_MARKET_USER));
    }
}
