package com.sola.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.io.IOException;

public class MybatisConfig {

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) throws IOException {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        // 设置数据源
        factoryBean.setDataSource(dataSource);
        // 设置mybatis核心配置文件路径
        // factoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        // 设置类型别名
        factoryBean.setTypeAliasesPackage("com.sola.pojo");
        // 设置Mapper映射文件路径
        // factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return factoryBean;
    }

    // 扫描Mapper接口（装配MapperScannerConfigurer管理Mapper代理对象）
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("com.sola.mapper");
        return configurer;
    }

}
