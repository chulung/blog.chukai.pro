package com.chulung.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;

/**
 * Created by chukai on 2017/3/5.
 */
public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toJsonString(Object obj){
        try {
            return  objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeJsonMappingException(JsonMappingException.fromUnexpectedIOE(e));
        }
    }
}
