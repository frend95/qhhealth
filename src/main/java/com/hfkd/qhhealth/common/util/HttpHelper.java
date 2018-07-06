package com.hfkd.qhhealth.common.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * http工具类
 * @author hexq
 * @date 2017/11/15 14:02
 */
public class HttpHelper {

    /**
     * 获取http中请求参数
     * @param request ServletRequest
     * @return String 返回参数字符串
     */
    public static String getBodyString(ServletRequest request) {
        /*StringBuilder sb = new StringBuilder();
        try (InputStream inputStream = request.getInputStream();
             InputStreamReader isr = new InputStreamReader(inputStream, "UTF-8");
             BufferedReader reader = new BufferedReader(isr)) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();*/
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null){
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 获取request中的token值
     * @param request HttpServletRequest
     * @return String
     */
    public static String getTokenByRequest(HttpServletRequest request) {
        String token = null;
        try {
            String method = request.getMethod();
            String contentType = request.getContentType();
            if ("GET".equals(method)) {
                token = request.getParameter("token");
            } else if (contentType != null && contentType.startsWith("multipart/form-data")) {
                StandardMultipartHttpServletRequest multipartReq = (StandardMultipartHttpServletRequest) request;
                token = multipartReq.getParameter("token");
            } else {
                String paramStr = HttpHelper.getBodyString(request);
                if (StringUtils.isNotBlank(paramStr)) {
                    HashMap map = JSON.parseObject(paramStr, HashMap.class);
                    token = (String) map.get("token");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }
}