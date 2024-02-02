package com.gsh.config;

import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.apache.ibatis.session.AutoMappingBehavior;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * ClassName: MapperJavaConfig
 * Package: com.gsh.config
 * Description:
 *
 * @Author Gush
 * @Create 2024-02-01 13:47
 */
/*
* 不保留外部配置文件，全部mybatis的属性都在代码中设置！
* */
@Configuration
public class MapperJavaConfig {

    //TODO: 替代xml文件的java配置
        /*

            <settings>
                <!-- 开启驼峰式映射-->
                <setting name="mapUnderscoreToCamelCase" value="true"/>
                <!-- 开启logback日志输出-->
                <setting name="logImpl" value="SLF4J"/>
                <!--开启resultMap自动映射 -->
                <setting name="autoMappingBehavior" value="FULL"/>
            </settings>

            <typeAliases>
                <!-- 给实体类起别名 -->
                <package name="com.atguigu.pojo"/>
            </typeAliases>

            <plugins>
                <plugin interceptor="com.github.pagehelper.PageInterceptor">
                    <!--
                        helperDialect：分页插件会自动检测当前的数据库链接，自动选择合适的分页方式。
                        你可以配置helperDialect属性来指定分页插件使用哪种方言。配置时，可以使用下面的缩写值：
                        oracle,mysql,mariadb,sqlite,hsqldb,postgresql,db2,sqlserver,informix,h2,sqlserver2012,derby
                        （完整内容看 PageAutoDialect） 特别注意：使用 SqlServer2012 数据库时，
                        https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/zh/HowToUse.md#%E5%A6%82%E4%BD%95%E9%85%8D%E7%BD%AE%E6%95%B0%E6%8D%AE%E5%BA%93%E6%96%B9%E8%A8%80
                     -->
                    <property name="helperDialect" value="mysql"/>
                </plugin>
            </plugins>

         */
    // 把SqlSessionFactoryBean加入到IoC容器
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 指定数据库连接池对象
        sqlSessionFactoryBean.setDataSource(dataSource);
        // 使用代码形式完成mybatis配置文件的功能，可以省去mybatis-config.xml文件 <settings>
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true); // 开启驼峰式映射
        configuration.setLogImpl(Slf4jImpl.class);  // 开启日志
        configuration.setAutoMappingBehavior(AutoMappingBehavior.FULL);
        sqlSessionFactoryBean.setConfiguration(configuration);

        // 设置别名 <typeAliases>
        sqlSessionFactoryBean.setTypeAliasesPackage("com.gsh.pojo");

        // 插件设置 <plugins>
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("helperDialect","mysql");
        pageInterceptor.setProperties(properties);
        sqlSessionFactoryBean.addPlugins(pageInterceptor);

        return sqlSessionFactoryBean;
    }

    // 把Mapper映射器加入到IoC容器
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        // 这是Mapper代理对象的factoryBean -> 一个指定的包 -> 里面的mapper接口 -> 通过sqlSessionFactory -> get到SqlSession -> getMapper -> 生成mapper代理对象 -> ioc
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.gsh.mapper");
        return mapperScannerConfigurer;
    }
}
