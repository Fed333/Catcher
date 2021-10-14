package com.example.catcher.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Клас з конфігурацією веб шару

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Value("${upload.path}")    //само дістане потрібне значення
    private String uploadPath;

    @Value("${basic.path}")
    private String basicPath;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/basic/**")
                .addResourceLocations("file://" + basicPath + "/");
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file://" + uploadPath + "/" );
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}
