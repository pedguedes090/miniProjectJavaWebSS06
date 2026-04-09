package com.edupath.EduPath.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.edupath.EduPath")
public class WebMvcConfig implements WebMvcConfigurer {
    // @EnableWebMvc:
    // Bat cac chuc nang Spring MVC (Controller, ViewResolver, Mapping...)
    // @ComponentScan:
    // Quet tất cả các class co annotation (@Controller, @Service, @Repository...)
    // trong package com.edupath.EduPath
}