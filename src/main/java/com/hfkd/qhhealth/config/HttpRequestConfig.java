package com.hfkd.qhhealth.config;

import com.hfkd.qhhealth.common.constant.ContentTypeEnum;
import com.hfkd.qhhealth.common.util.RequestBodyWrapper;
import org.springframework.boot.web.servlet.filter.OrderedHiddenHttpMethodFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 重新封装请求配置
 * @author hexq
 * @date 2017/11/15 10:26
 */
//@Configuration
public class HttpRequestConfig {

//    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new OrderedHiddenHttpMethodFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest req, HttpServletResponse rsp, FilterChain filterChain)
                    throws ServletException, IOException {
                String type = req.getContentType() == null ? "" : req.getContentType();
                String json = ContentTypeEnum.JSON.getType();
                String xml = ContentTypeEnum.XML.getType();
                if ("".equals(type) || json.equals(type)|| xml.equals(type)) {
                    req = new RequestBodyWrapper(req);
                }
                filterChain.doFilter(req, rsp);
            }
        };
    }
}