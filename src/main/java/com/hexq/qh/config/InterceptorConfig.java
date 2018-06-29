package com.hexq.qh.config;

import com.hexq.qh.common.interceptor.LoginVerifyInterceptor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 * @author hexq
 * @date 2017/11/15  14:02
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	private final Log log = LogFactory.getLog(getClass());

	@Autowired
	private LoginVerifyInterceptor loginVerifyInspector;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginVerifyInspector)
				.addPathPatterns("/**");
		log.info("注册[session验证过滤器LoginVerifyInterceptor] --> /**");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")//设置允许跨域的路径
				.allowedOrigins("*")//设置允许跨域请求的域名
				.allowCredentials(true)//是否允许证书 不再默认开启
				.allowedMethods("GET", "POST", "PUT", "DELETE")//设置允许的方法
				.maxAge(3600)//跨域允许时间
				.allowedHeaders("*");//允许的header
	}
}