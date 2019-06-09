package com.hfkd.qhhealth.common.util;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * 短信工具类
 * @author hexq
 * @date 2018/6/29 15:14
 */
public class SmsUtil {

    private static String username;
    private static String password;
    private static String content;
    private static String timeout;

    static{
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        username = bundle.getString("sms.username");
        password = DigestUtil.md5(bundle.getString("sms.password"));
        timeout = bundle.getString("sms.timeout");
        try {
            content = new String(bundle.getString("sms.content").getBytes("ISO-8859-1"), "UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static String send(String phone, String code) {
        String httpUrl = "http://api.smsbao.com/sms";
        String str = content.replace("{code}", code).replace("{timeout}", timeout);
        StringBuffer httpArg = new StringBuffer();
        httpArg.append("u=").append(username).append("&");
        httpArg.append("p=").append(password).append("&");
        httpArg.append("m=").append(phone).append("&");
        httpArg.append("c=").append(encodeUrlString(str, "UTF-8"));
        return request(httpUrl, httpArg.toString());
    }

    private static String request(String httpUrl, String httpArg) {
        BufferedReader reader;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?" + httpArg;
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = reader.readLine();
            if (strRead != null) {
                sbf.append(strRead);
                while ((strRead = reader.readLine()) != null) {
                    sbf.append("\n");
                    sbf.append(strRead);
                }
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String encodeUrlString(String str, String charset) {
        if (StringUtils.isBlank(str)) {
            return str;
        }
        try {
            return java.net.URLEncoder.encode(str, charset);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String code = RandomUtil.getRandom();
        String send = SmsUtil.send("15674893583", code);
        System.out.println(send);

    }

}
