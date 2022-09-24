package com.example.smart_waiting.domain;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.List;

public class ListStringConverter implements AttributeConverter<List<String>,String> {

    private static final String SPLIT_CHAR = ";";

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        return String.join(SPLIT_CHAR,attribute);
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        return Arrays.asList(dbData.split(SPLIT_CHAR));
    }
}
