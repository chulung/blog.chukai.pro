package com.chulung.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by chulung on 2017/3/5.
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
    public static <T> T readValue(InputStream inputStream, Class<T> clazz){
        try {
            return  objectMapper.readValue(inputStream,clazz);
        } catch (Exception e){
            throw  new RuntimeException(e);
        }
    }

    public  static  <T> T readValue(byte[] bytes,Class<T> clazz){
        try {
            return  objectMapper.readValue(bytes,clazz);
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }
    }

    public static byte[] writeBytes(Object object){
        try {
            return  objectMapper.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
