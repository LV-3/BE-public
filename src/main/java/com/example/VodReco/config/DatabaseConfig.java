//package com.example.VodReco.config;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.mongodb.MongoDatabaseFactory;
//import org.springframework.data.mongodb.core.MongoTemplate;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DatabaseConfig {
//
//
//    @Bean
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource.main-mysql") // Main MySQL database properties in application.yml
//    public DataSource mainMysqlDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource.backup-mysql") // Backup MySQL database properties in application.yml
//    public DataSource backupMysqlDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean
//    @Qualifier("mainMongoTemplate")
//    public MongoTemplate mainMongoTemplate() {
//        // Setup and return the main MongoDB configuration
//        return new MongoTemplate(mainMongoDbFactory());
//    }
//
//    @Bean
//    public MongoDatabaseFactory mainMongoDbFactory() {
//        MongoClientURI uri = new MongoClientURI("mongodb://main-mongodb-host:27017/main_db");
//        return new SimpleMongoClientDbFactory(uri);
//    }
//
//    @Bean
//    @Qualifier("backupMongoTemplate")
//    public MongoTemplate backupMongoTemplate() {
//        // Setup and return the backup MongoDB configuration
//        return new MongoTemplate(backupMongoDbFactory());
//    }
//
//    @Bean
//    public MongoDatabaseFactory backupMongoDbFactory() {
//        MongoClientURI uri = new MongoClientURI("mongodb://backup-mongodb-host:27017/backup_db");
//        return new SimpleMongoClientDbFactory(uri);
//    }
//}

