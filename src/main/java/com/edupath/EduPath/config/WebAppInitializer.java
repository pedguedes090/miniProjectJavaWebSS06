package com.edupath.EduPath.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        // Cau hinh tang goc (Service, Repository)
        // Tra ve null neu chua tach rieng
        return null;
    }
    @Override
    protected Class<?>[] getServletConfigClasses() {
        // Dang ky cac config cho Spring MVC (Controller + View)
        return new Class[]{WebMvcConfig.class, ThymeleafConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        // Map tat ca request vao DispatcherServlet
        return new String[]{"/"};
    }
}