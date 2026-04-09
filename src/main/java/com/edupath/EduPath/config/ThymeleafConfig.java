package com.edupath.EduPath.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

@Configuration
public class ThymeleafConfig {
    @Bean
    public SpringResourceTemplateResolver templateResolver(ApplicationContext applicationContext) {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();

        // Gan ApplicationContext de Thymeleaf truy cap tai nguyen trong webapp
        resolver.setApplicationContext(applicationContext);

        //  Sai pho bien: /WEB-INF/views (dung cho JSP)
        //  Dung cho Thymeleaf:
        resolver.setPrefix("/WEB-INF/templates/");

        // Dinh dang file Thymeleaf la .html
        resolver.setSuffix(".html");

        // Che do xu ly HTML (bat buoc cho Thymeleaf)
        resolver.setTemplateMode("HTML");

        // Encoding tranh loi font tieng Viet
        resolver.setCharacterEncoding("UTF-8");

        // Tat cache de code xong reload la thay doi ngay (dev)
        resolver.setCacheable(false);

        return resolver;
    }
    @Bean
    public SpringTemplateEngine templateEngine(SpringResourceTemplateResolver templateResolver) {
        SpringTemplateEngine engine = new SpringTemplateEngine();

        // Gan templateResolver o tren vao engine
        engine.setTemplateResolver(templateResolver);

        // Bat Spring Expression Language (cho ${}, *{}, #numbers...)
        engine.setEnableSpringELCompiler(true);

        return engine;
    }
    @Bean
    public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();

        // Noi Thymeleaf vao Spring MVC de render view
        resolver.setTemplateEngine(templateEngine);

        // Encoding UTF-8
        resolver.setCharacterEncoding("UTF-8");

        return resolver;
    }
}