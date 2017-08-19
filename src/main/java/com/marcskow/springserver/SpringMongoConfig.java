package com.marcskow.springserver;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.marcskow.springserver.repositories")
public class SpringMongoConfig extends AbstractMongoConfiguration {
    @Override
    protected String getDatabaseName() {
        return "cellar";
    }

    @Bean
    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient("127.0.0.1");
    }

    @Override
    protected String getMappingBasePackage() {
        return "com.marcskow.springserver";
    }
}