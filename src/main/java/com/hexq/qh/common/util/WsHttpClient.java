package com.hexq.qh.common.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 用于调用WebService的工具类
 * @author hexq
 * @date 2017/11/28  15:01
 */
public class WsHttpClient {
    /**请求地址（WSDL地址）**/
    private String url;
    /**WebService命名空间**/
    private String namespace;
    /**WebService方法名**/
    private String methodName;
    /**响应信息**/
    private String responseData;

    public WsHttpClient(String url, String namespace, String methodName) {
        this.url = url;
        this.namespace = namespace;
        this.methodName = methodName;
    }

    public WsHttpClient(String url) {
        this.url = url;
    }

    /**
     * 发送相应格式的报文
     * @param msg 报文
     * @param contentType 请求类型
     * @return 返回码
     * @throws Exception
     */
    private int send(String msg, String contentType) throws Exception {
        //使用POST请求
        PostMethod postMethod = new PostMethod(url);
        byte[] bytes = msg.getBytes("utf-8");
        int statusCode = 0;
        try(InputStream inputStream = new ByteArrayInputStream(bytes, 0, bytes.length)) {
            RequestEntity requestEntity = new InputStreamRequestEntity(inputStream, bytes.length, contentType);
            //设置请求
            postMethod.setRequestEntity(requestEntity);
            HttpClient httpClient = new HttpClient();
            //发送请求
            statusCode = httpClient.executeMethod(postMethod);
            //获取响应
            responseData = postMethod.getResponseBodyAsString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return statusCode;
    }

    public int sendGet() {
        //使用POST请求
        GetMethod getMethod = new GetMethod(url);
        int statusCode = 0;
        try {
            HttpClient httpClient = new HttpClient();
            //发送请求
            statusCode = httpClient.executeMethod(getMethod);
            //获取响应
            responseData = getMethod.getResponseBodyAsString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return statusCode;
    }


    /**
     * 发送JSON格式的报文
     * @param msg 报文
     * @return 返回码
     * @throws Exception
     */
    public int sendJson(String msg) throws Exception {
        String contentType = "application/json";
        return send(msg, contentType);
    }

    /**
     * 发送XML格式的报文
     * @param parameterMap 请求参数
     * @return 返回码
     * @throws Exception
     */
    public int sendSoapMsg(Map<String, String> parameterMap) throws Exception {
        //String contentType = "application/soap+xml; charset=utf-8";
        String contentType = "text/xml";
        String msg = buildSoapMsg(parameterMap);
        int rtnCode = send(msg, contentType);
        if (StringUtils.isNotBlank(responseData)) {
            responseData = getSoapBodyData(responseData);
        }
        return rtnCode;
    }

    /**
     * 获取响应信息
     * @return 响应信息
     */
    public String getResponseMsg() {
        return responseData;
    }

    /**
     * 拼装SOAP报文
     * @param parameterMap 请求参数
     * @return SOAP报文
     */
    private String buildSoapMsg(Map<String, String> parameterMap) {
        StringBuilder soapRequestData = new StringBuilder();
        Set<String> nameSet = parameterMap.keySet();

        /*soapRequestData.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        soapRequestData.append("<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
                + " xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\""
                + " xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">");
        soapRequestData.append("<soap:Body>");
        soapRequestData.append("<").append(methodName).append(" xmlns=\"").append(namespace).append("\">");
        soapRequestData.append("<").append(methodName).append("Request>");
        for (String name : nameSet) {
            soapRequestData.append("<").append(name).append(">").append(parameterMap.get(name)).append("</")
                    .append(name).append(">");
        }
        soapRequestData.append("</").append(methodName).append("Request>");
        soapRequestData.append("</").append(methodName).append(">");
        soapRequestData.append("</soap:Body>");
        soapRequestData.append("</soap:Envelope>");*/

        //报文头
        soapRequestData.append("<?xml version=\"1.0\"?>");
        soapRequestData.append("<S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\">");
        //以下是报文体
        soapRequestData.append("<S:Body>");
        //声明方法和方法路径(namespace)的标签
        soapRequestData.append("<ns2:").append(methodName).append(" xmlns:ns2=\"").append(namespace).append("\">");
        //方法的参数
        for (String name : nameSet) {
            soapRequestData.append("<").append(name).append(">").append(parameterMap.get(name)).append("</")
                    .append(name).append(">");
        }
        soapRequestData.append("</ns2:").append(methodName).append(">");
        soapRequestData.append("</S:Body>");
        soapRequestData.append("</S:Envelope>");

        return soapRequestData.toString();
        //return "<?xml version=\"1.0\" ?><S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\"><S:Body><ns2:sayHello xmlns:ns2=\"http://test.kpms.kayak.com/\"><arg0>Jack36</arg0></ns2:sayHello></S:Body></S:Envelope>";
    }

    /**
     * 获取SOAP响应报文中方法的返回值
     * @param msg 响应报文
     * @return 返回值
     */
    private String getSoapBodyData(String msg) {
        //SOAP响应报文的返回值在<return></return>标签中
        int i = msg.indexOf("<return>");
        int j = msg.indexOf("</return>");
        if (i == -1 || j == -1) {
            return "";
        }
        return msg.substring(i + 8, j);
    }



    public static void main(String[] args) throws Exception {
        WsHttpClient wsHttpclient = new WsHttpClient("http://10.7.3.85:8083/hello",
                "http://example.webservice.com/", "sayHello");

        Map<String, String> parameterMap = new HashMap<>();
        parameterMap.put("name", "Jack");

        int statusCode = wsHttpclient.sendSoapMsg(parameterMap);
        if (statusCode == 200) {
            System.out.println("调用成功！响应：" + wsHttpclient.getResponseMsg());
        } else {
            System.out.println("调用失败！错误码：" + statusCode);
        }

        /*WsHttpclient httpclient = new WsHttpclient("http://10.7.3.85:8080/kpms/demo/getUser");
        String reqJson = "{\"id\": \"c2d2815324d949908b7c84adbae103bd\"}";
        httpclient.sendJson(reqJson);
        String responseMsg = httpclient.getResponseMsg();
        System.out.println(responseMsg);*/

    }

}