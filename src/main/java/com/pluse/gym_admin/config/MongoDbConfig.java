package com.pluse.gym_admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class MongoDbConfig {

    @Bean
    public PlatformTransactionManager platformTransactionManager(MongoDatabaseFactory databaseFactory){
        return new MongoTransactionManager(databaseFactory);
    }


}
