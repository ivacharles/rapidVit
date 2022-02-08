//package com.rapid.vit.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//import javax.sql.DataSource;
//
//
//@Configuration
//@ComponentScan("com.rapid.vit.config")
//public class RapidvitJdbcConfig {
//        @Bean
//        public DataSource mysqlDataSource() {
//            DriverManagerDataSource dataSource = new DriverManagerDataSource();
//            dataSource.setDriverClassName("org.postgresql.Driver");
//            dataSource.setUrl("jdbc:postgresql://localhost:5432/rapidvit_db");
//            dataSource.setUsername("rapidvit_u");
//            dataSource.setPassword("rapidvit_pwd");
//
//            return dataSource;
//        }
//}
