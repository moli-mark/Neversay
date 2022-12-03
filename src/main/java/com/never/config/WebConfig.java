package com.never.config;

import com.never.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author molimark<br />
 * @date: 2022/8/3 15:26<br/>
 * @description: <br/>
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/admin/*")
                .excludePathPatterns("/admin","/admin/login");

        WebMvcConfigurer.super.addInterceptors(registry);
    }

    //资源文件放行
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:static/","classpath:/");
        registry.addResourceHandler("/pdf/upload/**")
                .addResourceLocations("file:"+System.getProperty("user.dir")+"/src/main/resources/static/pdf/upload/");
        registry.addResourceHandler("/video/upload/**")
                .addResourceLocations("file:"+System.getProperty("user.dir")+"/src/main/resources/static/video/upload/");
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("classpath:/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
    }
}
