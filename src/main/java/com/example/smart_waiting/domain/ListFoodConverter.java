package com.example.smart_waiting.domain;

import com.example.smart_waiting.market.Food;
import com.example.smart_waiting.market.FoodRepository;
import lombok.RequiredArgsConstructor;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
@RequiredArgsConstructor
public class ListFoodConverter implements AttributeConverter<List<Food>,String> {

    private static final String SPLIT_CHAR = ";";
    private final FoodRepository foodRepository;

    @Override
    public String convertToDatabaseColumn(List<Food> attribute) {
        StringBuffer sb = new StringBuffer();
        attribute.stream().mapToLong(Food::getId).forEach(x -> sb.append(x + SPLIT_CHAR));
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();

    }

    @Override
    public List<Food> convertToEntityAttribute(String dbData) {
        String[] stringArray = dbData.split(SPLIT_CHAR);

        // 이후에 abstract exception으로 예외 처리를 해주어야 한다.
        return Arrays.stream(stringArray).map(x->foodRepository.findById(Long.parseLong(x))
                .orElseThrow(()->new RuntimeException("Food entity를 찾을 수 없습니다.")))
                .collect(Collectors.toList());
    }
}
