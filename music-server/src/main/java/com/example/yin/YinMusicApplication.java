package com.example.yin;

import org.mybatis.spring.annotation.MapperScan;
//MapperScan注解用于指定MyBatis的映射器接口所在的包。通过使用这个注解，
//        你可以告诉MyBatis在哪些包中寻找映射器接口。
import org.springframework.boot.SpringApplication;
//这个类是Spring Boot框架中的一个核心类。
//SpringApplication类是用于启动Spring Boot应用程序的入口点。它提供了各种静态方法来简化应用程序的启动和配置。
import org.springframework.boot.autoconfigure.SpringBootApplication;
//这个注解是Spring Boot框架中的一个核心注解。
//@SpringBootApplication注解是一个组合注解，它结合了多个注解，用于简化Spring Boot应用程序的配置和启动。
// 它包括了以下三个注解的功能：
//@Configuration：表明该类是一个配置类，用于定义应用程序的配置。
//@EnableAutoConfiguration：启用自动配置，根据应用程序的依赖和配置，自动配置Spring和第三方库。
//@ComponentScan：指定要扫描的组件包，用于自动发现并注册Spring组件，如控制器、服务、存储库等。


@SpringBootApplication
//通过在主应用程序类上使用@SpringBootApplication注解，可以将其作为Spring Boot应用程序的入口点。
//Spring Boot会自动扫描该类所在包及其子包，并根据自动配置规则加载和配置应用程序所需的各种组件。
//这样，你可以更轻松地启动和管理Spring Boot应用程序。
@MapperScan("com.example.yin.dao")
//可以告诉MyBatis在哪些包中寻找映射器接口
public class YinMusicApplication {

    public static void main(String[] args) {
        SpringApplication.run(YinMusicApplication.class, args);
//        可以在Java应用程序的main方法中使用SpringApplication.run()方法来启动Spring Boot应用程序。
//        这个方法将加载应用程序的配置并启动嵌入式的Tomcat服务器（或其他Web服务器）使应用程序能够响应HTTP请求。

    }

}

