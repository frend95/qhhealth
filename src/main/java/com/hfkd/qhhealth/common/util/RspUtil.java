package com.hfkd.qhhealth.common.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 响应工具类
 * @author hexq
 * @date 2018/6/5 17:51
 */
public class RspUtil {

    /**OK，表示请求被服务器正常处理*/
    public final static int CODE_OK = 200;
    /**No Content，表示请求已成功处理，但是没有内容返回*/
    public final static int CODE_NO_CONTENT = 204;
    /**Bad Request，表示请求报文存在语法错误或参数错误*/
    public final static int CODE_BAD_REQUEST = 400;
    /**Unauthorized，表示发送的请求需要有HTTP认证信息或者是认证失败了*/
    public final static int CODE_UNAUTHORIZED = 401;
    /**Forbidden，表示对请求资源的访问被服务器拒绝了*/
    public final static int CODE_FORBIDDEN = 403;
    /**Not Found，表示服务器找不到你请求的资源*/
    public final static int CODE_NOT_FOUND = 404;
    /**Internal Server Error，表示服务器执行请求的时候出错了*/
    public final static int CODE_ERROR = 500;
    /**Service Unavailable，表示服务器超负载或正停机维护，无法处理请求*/
    public final static int CODE_UNAVAILABLE = 503;

    /**
     * 初始化返回Map
     * @return 返回默认请求成功的Map
     */
    public static Map<String,Object> ok(){
        Map<String,Object> map = new HashMap<>(4);
        map.put("code", CODE_OK);
        map.put("msg", "Request success");
        return map;
    }

    public static Map<String,Object> emptyList(){
        Map<String,Object> map = new HashMap<>(4);
        map.put("code", CODE_OK);
        map.put("msg", "Request success");
        map.put("result", Collections.EMPTY_LIST);
        return map;
    }

    public static Map<String,Object> ok(String msg){
        Map<String,Object> map = new HashMap<>(4);
        map.put("code", CODE_OK);
        map.put("msg", msg);
        return map;
    }

    /**
     * 处理异常添加默认异常信息
     * @return 含有默认错误信息
     */
    public static Map<String,Object> error() {
        Map<String,Object> map = new HashMap<>(4);
        map.put("code", CODE_ERROR);
        map.put("msg", "Request error");
        return map;
    }

    public static Map<String,Object> error(String msg) {
        Map<String,Object> map = new HashMap<>(4);
        map.put("code", CODE_ERROR);
        map.put("msg", msg);
        return map;
    }

    public static Map<String,Object> error(Map<String,Object> resultMap, String msg) {
        resultMap.put("code", CODE_ERROR);
        resultMap.put("msg", msg);
        return resultMap;
    }

    public static Map<String,Object> badRequest(Map<String,Object> resultMap) {
        resultMap.put("code", CODE_BAD_REQUEST);
        resultMap.put("msg", "Bad request parameters");
        return resultMap;
    }

    public static Map<String,Object> badRequest(Map<String,Object> resultMap, String msg) {
        resultMap.put("code", CODE_BAD_REQUEST);
        resultMap.put("msg", msg);
        return resultMap;
    }

    public static Map<String,Object> unauthorized(String msg) {
        Map<String,Object> resultMap = new HashMap<>(4);
        resultMap.put("code", CODE_UNAUTHORIZED);
        resultMap.put("msg", msg);
        return resultMap;
    }

    public static Map<String,Object> code(String code, String errorMess) {
        Map<String,Object> resultMap = new HashMap<>(4);
        resultMap.put("code", code);
        resultMap.put("msg", errorMess);
        return resultMap;
    }

}
