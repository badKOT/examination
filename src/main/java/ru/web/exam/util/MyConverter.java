package ru.web.exam.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Converter
public class MyConverter implements AttributeConverter<List<String>, String> {
    @Override
    public String convertToDatabaseColumn(List<String> stringList) {
        if (stringList == null || stringList.isEmpty())
            return "";
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, List<String>> map = new HashMap<>();
        map.put("answers", stringList);
        try {
            String json = objectMapper.writeValueAsString(map);
            return json;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> convertToEntityAttribute(String s) {
        if (s == null || s.isEmpty())
            return null;
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<HashMap<String,List<String>>> typeRef = new TypeReference<>() {};
        Map<String, List<String>> map;
        List<String> list;
        try {
            map = objectMapper.readValue(s, typeRef);
            list = map.get("answers");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
