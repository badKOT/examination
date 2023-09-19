/*
package ru.web.exam.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMapConverter implements AttributeConverter<Map<String, List<String>>, String> {
    @Override
    public String convertToDatabaseColumn(Map<String, List<String>> stringListMap) {
        if (stringListMap == null || stringListMap.isEmpty())
            return "";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(stringListMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, List<String>> convertToEntityAttribute(String s) {
        if (s == null || s.isEmpty())
            return null;
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<HashMap<String,List<String>>> typeRef = new TypeReference<>() {};
        Map<String, List<String>> map = null;
        try {
            map = objectMapper.readValue(s, typeRef);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return map;
    }
}
// create database new_db with template old_db owner user;
Map<String, List<String>> map = new HashMap<>();
map.put("answers", List.of("a", "b", "c", "d"));
ObjectMapper objectMapper = new ObjectMapper();
String json = objectMapper.writeValueAsString(map);

TypeReference<HashMap<String,List<String>>> typeRef = new TypeReference<>() {};
Map<String, List<String>> map1 = objectMapper.readValue(json, typeRef);
List<String> answers = map1.get("answers");

 */