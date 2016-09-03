package com.shuntai;

import com.shuntai.model.bean.WeixinConf;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by hadoop on 2016/8/30.
 */
@SpringBootApplication
@MapperScan(value = "com.shuntai.mapper") //加载包内的Mapper接口
@EnableConfigurationProperties(WeixinConf.class)
public class ApplicationMain {
    public static final org.slf4j.Logger log = LoggerFactory.getLogger(ApplicationMain.class);

    public static void main(String[] args) {

        SpringApplication.run(ApplicationMain.class, args);
        log.info("server is running!");
    }

    @Bean
    @ConfigurationProperties(prefix="spring.datasource")  //加载properties文件中spring.datasource的属性
    public DataSource dataSource(){
        return new org.apache.tomcat.jdbc.pool.DataSource();
    }

    //提供SqlSeesion
    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        //加载mybatis目录项的所有mapper文件
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis/*.xml"));

        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

}
