package com.gsh.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * ClassName: WebMvcJavaConfig
 * Package: com.gsh.config
 * Description:
 *
 * @Author Gush
 * @Create 2024-02-01 11:11
 */
/*
* 控制层的配置类 (controller,springmvc)
    1. controller
    2. 全局异常处理器
    3. handlerMapping handlerAdapter
    4. 静态资源处理
    5. jsp视图解析器前后缀
    6. json转化器
    7. 拦截器
 */
@Configuration
@ComponentScan({"com.gsh.controller"})
@EnableWebMvc // 3、6

public class WebMvcJavaConfig implements WebMvcConfigurer {
    // 4
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    // 5
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

    // 7
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(new xx()).addPathPatterns().excludePathPatterns();
    }
}


