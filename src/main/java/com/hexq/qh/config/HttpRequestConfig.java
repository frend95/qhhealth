package com.hexq.qh.config;

import com.hexq.qh.common.constant.ContentTypeEnum;
import com.hexq.qh.common.util.RequestBodyWrapper;
import org.springframework.boot.web.servlet.filter.OrderedHiddenHttpMethodFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 重新封装请求配置
 * @author caoyong
 * @date 2017年9月11日 下午7:16:53
 */
@Configuration
public class HttpRequestConfig {

    @Bean
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