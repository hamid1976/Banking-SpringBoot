package com.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@Slf4j
public class ConnectionUtil {

    private static HikariDataSource dataSource;

    @Bean
    public DataSource dataSource() throws Exception {
        Properties p=new Properties();
        FileInputStream fis=new FileInputStream("G:\\1.Hamid\\14.SpringBoot\\Banking\\src\\main\\resources\\application.properties");
        p.load(fis);

        //Datasource Configuration
        String url = p.getProperty("url");
        String driver = p.getProperty("driver_class");
        String user = p.getProperty("user");
        String pwd = p.getProperty("pwd");


        //Hikari Connection
        String mPS = p.getProperty("maximumPoolSize");
        int maximumPoolSize=Integer.parseInt(mPS);
        String mI = p.getProperty("minimumIdle");
        int minimumIdle=Integer.parseInt(mI);
        String cT = p.getProperty("connectionTimeout");
        int connectionTimeout=Integer.parseInt(cT);
        String iT = p.getProperty("idleTimeout");
        int idleTimeout=Integer.parseInt(iT);



        dataSource=new HikariDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(pwd);
        dataSource.setMaximumPoolSize(maximumPoolSize);
        dataSource.setMinimumIdle(minimumIdle);
        dataSource.setConnectionTimeout(connectionTimeout);
        dataSource.setIdleTimeout(idleTimeout);



        return  dataSource;
    }
}
