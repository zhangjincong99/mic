package com.example.yin.config;

import org.springframework.context.annotation.Configuration;
//@Configuration是一个Spring注解，用于将类标记为配置类。
//可以告诉Spring容器该类是一个配置类，并且应该被用于创建和管理bean。
import org.springframework.http.converter.HttpMessageConverter;
//用于处理HTTP请求和响应的消息转换。它允许在处理HTTP请求和响应时进行消息的读取、写入和转换操作
import org.springframework.http.converter.StringHttpMessageConverter;
//它负责将字符串转换为字节流进行传输，并在接收到字节流时将其转换回字符串。
import org.springframework.web.servlet.config.annotation.CorsRegistry;
//可以在代码中使用该类及其相关方法，以自定义和配置CORS规则，实现跨域通信的控制和管理。
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//用于启用Spring MVC的WebMvc配置
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//用于配置Spring MVC的WebMvc行为。它提供了一组回调方法，允许开发人员自定义和扩展Spring MVC的配置。

import java.nio.charset.StandardCharsets;
//它提供了标准字符集的常量定义。字符集用于定义字符编码方案，例如UTF-8、UTF-16等。
import java.util.List;
/**
 * 说明：解决以下问题
 * <p>
 *     因使用WebMvcConfigurer加载静态时urlencode编码无法解析为正常中文问题
 * </p>
 *
 * @Author: Winston.Li
 * @Create: 2022-03-13 13:50
 * @version: V 0.0.1
 */
@EnableWebMvc
//用于在Spring应用程序中配置和启用Web MVC功能。
@Configuration
//用于将类标记为配置类。并且应该被用于创建和管理bean。
public class WebCharacterEncodingFilter implements WebMvcConfigurer {

    /**
     * 乱码处理
     */

    public HttpMessageConverter<String> responseBodyConverter() {
//        并配置其使用UTF-8字符集。
        final StringHttpMessageConverter converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        converter.setWriteAcceptCharset(false);
        return converter;
        //    它返回一个HttpMessageConverter<String>对象，用于处理响应体中的字符串内容。
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        /*
        该方法用于配置跨域资源共享（CORS）设置。它允许所有的来源（allowedOrigins("*")）、
        所有的HTTP方法（allowedMethods("*")）和所有的请求头（allowedHeaders("*")）。
         */
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*");
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        if (converters.size() > 0) {
            converters.add(converters.get(0));
            converters.set(0, responseBodyConverter());
        } else {
            converters.add(responseBodyConverter());
        }
//        该方法用于扩展或修改消息转换器列表。它首先判断转换器列表是否已经存在转换器，
//        如果存在，则将第一个转换器添加到列表末尾如果列表为空，则直接将自定义的转换器添加到列表中。
    }
}

