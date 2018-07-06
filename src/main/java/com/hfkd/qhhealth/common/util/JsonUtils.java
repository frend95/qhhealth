package com.hfkd.qhhealth.common.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * JSON处理
 * @author hexq
 * @date 2017/11/15 14:02
 */
public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * object转json
     * @param obj object
     * @return json string
     * @throws JsonProcessingException
     */
    public static String toJson(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    /**
     * json转object
     * @param json json string
     * @param cls obj.class
     * @return object
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public static <T> T json2Obj(String json, Class<T> cls) throws IOException {
        return objectMapper.readValue(json, cls);
    }

}
