package com.chulung.conf;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
@EnableConfigurationProperties(DBCPProperties.class)
public class DBCPConfiguration {

    @Autowired
    private DBCPProperties dBCPProperties;

    @Bean
    public DataSource dataSource(){
        DataSource dataSource=new BasicDataSource();

        BeanUtils.copyProperties(dBCPProperties,dataSource);
        return  dataSource;
    }


}
