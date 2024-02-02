package com.gsh.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * ClassName: SpringIoCInit
 * Package: com.gsh.config
 * Description:
 *
 * @Author Gush
 * @Create 2024-02-01 14:10
 */
public class SpringIoCInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    // root ioc容器的配置类
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ServiceJavaConfig.class, MapperJavaConfig.class, DataSourceJavaConfig.class};
    }

    // web ioc容器的配置类
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebMvcJavaConfig.class};
    }

    // dispatcherServlet的拦截路径
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}

