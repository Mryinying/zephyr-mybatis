package com.it.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author wang_zy
 * @Date 2019/12/16 11:56
 */
@Configuration
@MapperScan("com.it.plus.*.mapper")
public class DBConfig {

    @Value("${mysql.datasource.type-aliases-package}")
    private String typeAliasesPackage;

    @Value("${mysql.datasource.mapper-locations}")
    private String mapperLocation;

    @Value("${mysql.datasource.config-location}")
    private String configLocation;

    /**
     * 写数据源
     *
     * @Primary 标志这个 Bean 如果在多个同类 Bean 候选时，该 Bean 优先被考虑。
     * 多数据源配置的时候注意，必须要有一个主数据源，用 @Primary 标志该 Bean
     */
    @Primary
    @Bean
    @ConfigurationProperties(prefix = "mysql.datasource.master")
    public DataSource masterDataSource() {
        return new DruidDataSource();
    }

    @Bean
    @ConfigurationProperties(prefix = "mysql.datasource.slave")
    public DataSource slaveDataSource1() {
        return new DruidDataSource();
    }

    /**
     * 设置数据源路由，通过该类中的determineCurrentLookupKey决定使用哪个数据源
     */
    @Bean
    public AbstractRoutingDataSource routingDataSource() {
        MyAbstractRoutingDataSource proxy = new MyAbstractRoutingDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>(2);
        targetDataSources.put(DbContextHolder.MASTER, masterDataSource());
        targetDataSources.put(DbContextHolder.SLAVE +"1", slaveDataSource1());
        proxy.setDefaultTargetDataSource(masterDataSource());
        proxy.setTargetDataSources(targetDataSources);
        return proxy;
    }

    /**
     * 多数据源需要自己设置sqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(routingDataSource());
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        // 实体类对应的位置
        bean.setTypeAliasesPackage(typeAliasesPackage);
        // mybatis的XML的配置
        bean.setMapperLocations(resolver.getResources(mapperLocation));
        bean.setConfigLocation(resolver.getResource(configLocation));
        return bean.getObject();
    }

    /**
     * 设置事务，事务需要知道当前使用的是哪个数据源才能进行事务处理
     */
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager() {
        return new DataSourceTransactionManager(routingDataSource());
    }

    @Bean
    @Primary//容器中会有两个此组件，一个是读取配置文件中的属性，当然也就是全部都是空的。还有一个就是下面这个配置。所以，标注@Primary，指定使用
    public MybatisProperties mybatisProperties() {
        MybatisProperties mybatisProperties = new MybatisProperties();
        //设置xml文件所在位置，各种sql的xml
        mybatisProperties.setMapperLocations(new String[]{mapperLocation});
        //设置mybatis-config文件位置。mybatis的配置文件可以设置缓存配置，插件设置，log级别等。。
        mybatisProperties.setConfigLocation(configLocation);
        return mybatisProperties;
    }
}
