package com.example.VodReco.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://1.220.201.108:3001", "http://1.220.201.108:3000", "http://1.223.55.43:8000", "http://1.220.201.108:8000",
                        "http://lv3-s3.s3-website.ap-northeast-2.amazonaws.com", "lv3-loadbalancer-f-725358857.ap-northeast-2.elb.amazonaws.com",
                        "http://hellogptv.com", "https://front.hellogptv.com",
                        "http://192.168.123.105:3000",
                        "http://175.117.217.50:3000", "http://172.26.208.1:3000",
                        "https://d225nwg9l5o274.cloudfront.net",
                        "https://1.220.201.108:3000", "https://1.220.201.108:3001")
                .allowedMethods("OPTIONS", "GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }

