package com.templates.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;
import java.util.Properties;

@Configuration
@EnableConfigurationProperties(DruidSettings.class)
public class DruidDataSorceConfig {

    @Autowired
    private DruidSettings druidSettings;

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DruidDataSource dataSource(DataSourceProperties properties) throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(druidSettings.getDriverClaClassName());
        dataSource.setUrl(druidSettings.getUrl());
        dataSource.setUsername(druidSettings.getUsername());
        dataSource.setPassword(druidSettings.getPassword());

        dataSource.setInitialSize(druidSettings.getInitialSize());
        dataSource.setMinIdle(druidSettings.getMinIdle());
        dataSource.setMaxActive(druidSettings.getMaxActive());
        dataSource.setMaxWait(druidSettings.getMaxWait());
        dataSource.setTimeBetweenEvictionRunsMillis(druidSettings.getTimeBetweenEvictionRunsMillis());
        dataSource.setMinEvictableIdleTimeMillis(druidSettings.getMinEvictableIdleTimeMillis());
        String validationQuery = druidSettings.getValidationQuery();
        if(validationQuery != null && validationQuery.length() != 0) {
            dataSource.setValidationQuery(validationQuery);
        }

        dataSource.setTestWhileIdle(druidSettings.isTestWhileIdle());
        dataSource.setTestOnBorrow(druidSettings.isTestOnBorrow());
        dataSource.setTestOnReturn(druidSettings.isTestOnReturn());

        if(druidSettings.isPoolPreparedStatements()) {
            dataSource.setMaxPoolPreparedStatementPerConnectionSize(druidSettings.getMaxPoolPreparedStatementPerConnectionSize());
        }

        dataSource.setFilters(druidSettings.getFilters());

        String connectionProperties = druidSettings.getConnectionProperties();
        if(connectionProperties != null && connectionProperties.length() != 0)
        {
            Properties pros = new Properties();
            String[] propertiesList = connectionProperties.split(";");
            for(String pro : propertiesList) {
                String[] obj = pro.split("=");
                String key = obj[0];
                String value = obj[1];
                pros.put(key, value);
            }
            dataSource.setConnectProperties(pros);
        }

        dataSource.setUseGlobalDataSourceStat(druidSettings.isUserGlobalDataSourceStat());

        return dataSource;

    }

}