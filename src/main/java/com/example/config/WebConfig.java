package com.example.config;

import com.example.controller.Interceptor.ProjectInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private ProjectInterceptor projectInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(projectInterceptor).addPathPatterns("/**")
        .excludePathPatterns("/pages/ProfileForm.html","/pages/login.html","/pages/StaffPage.html","/staff/**","/staff",
                "/students/login","/students/logout","/css/**","/element-ui/**","/images/**","/js/**");
    }
}
