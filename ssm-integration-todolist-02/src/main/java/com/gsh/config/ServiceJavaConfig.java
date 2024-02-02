package com.gsh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * ClassName: ServiceJavaConfig
 * Package: com.gsh.config
 * Description:
 *
 * @Author Gush
 * @Create 2024-02-01 11:32
 */
/*
* 业务层配置类
*   1.service
*   2. 开启aop注解的支持 aspect: @Before @After @AfterReturning @AfterThrowing @Around @Aspect @Order
*   3. tx声明式事务管理
*       3.1 对应的事务管理器实现 [最大的接口TransactionManager 实现有DataSourceTransactionManager HibernateTransactionManager JpaTransactionManager]
*       3.2 开启事务注解支持 @Transactional
* */
@Configuration
@EnableAspectJAutoProxy // 2
@EnableTransactionManagement // 3
@ComponentScan("com.gsh.service")
public class ServiceJavaConfig {
    @Bean
    public DataSourceTransactionManager transaction(DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }

}
