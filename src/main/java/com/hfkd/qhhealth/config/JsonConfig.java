package com.hfkd.qhhealth.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * json配置
 * @author hexq
 * @date 2018/6/7 09:41
 */
@Configuration
public class JsonConfig implements WebMvcConfigurer {

    /**
     * 更新SpringBoot 2.0.2后重写configureMessageConverters失效，原因不明，使用注入Bean更改默认JsonConverter
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverter(){
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteMapNullValue);
        fastConverter.setFastJsonConfig(fastJsonConfig);

        return new HttpMessageConverters((HttpMessageConverter)fastConverter);
    }

    /**
     * 使用fastJson
     */
    /*@Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //是否格式化返回Json
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        //一个对象的字符串属性中如果有特殊字符如双引号，将会在转成json时带有反斜杠转移符。如果不需要转义，可以使用这个属性。默认为false
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.DisableCheckSpecialChar);
        //输出key时是否使用双引号,默认为true
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.QuoteFieldNames);
        //是否输出值为null的字段,默认为false
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteMapNullValue);
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteNullListAsEmpty);
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteNullBooleanAsFalse);
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteNullNumberAsZero);
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteNullStringAsEmpty);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        //处理中文乱码问题
        *//*List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);*//*
        converters.add(fastConverter);
    }*/

    /**
     * 返回json字符串 null值转空字符串，jackson实现
     */
    /*@Bean
    @Primary
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
                jsonGenerator.writeString("");
            }
        });
        return objectMapper;
    }*/
}
