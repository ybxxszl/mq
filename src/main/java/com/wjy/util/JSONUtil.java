package com.wjy.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * @author ybxxszl
 * @date 2018年9月9日
 * @description JSON工具类
 */
public class JSONUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * @param object Object
     * @return String JSON字符串
     * @date 2018年9月9日
     * @author ybxxszl
     * @description Object转JSON字符串
     */
    public static String objectToJson(Object object) {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param jsonData 对象类型
     * @param beanType JSON字符串
     * @return T 对象
     * @date 2018年9月9日
     * @author ybxxszl
     * @description JSON字符串转对象
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            return MAPPER.readValue(jsonData, beanType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param jsonData JSON字符串
     * @param beanType 集合类型
     * @return List<T> 对象集合
     * @date 2018年9月9日
     * @author ybxxszl
     * @description JSON字符串转对象集合
     */
    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            return MAPPER.readValue(jsonData, javaType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
