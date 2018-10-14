package com.personal.book.library.config.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories(basePackages = "com.personal.book.library.datalayer.repository.mongo")
@PropertySource("classpath:application.properties")
public class MongoSpringConfiguration extends AbstractMongoConfiguration{

	@Autowired
	private Environment env;
	
    @Override
    protected String getDatabaseName() {
      return env.getProperty("mongodb.databasename");
    }

    @Override
    public Mongo mongo() throws Exception {
    		String dbUrl = env.getProperty("mongodb.url");
    		int dbPort = Integer.parseInt(env.getProperty("mongodb.port"));
    		return new MongoClient(dbUrl, dbPort);
    }
    
    @Override
    protected String getMappingBasePackage() {
      return env.getProperty("mongo.base.package");
    }

}
