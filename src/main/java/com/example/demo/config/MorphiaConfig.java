/*
 * Company: 
 * Copyright (c) 2012-2032 
 * All Rights Reserved.
 */
package com.example.demo.config;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import dev.morphia.Datastore;
import dev.morphia.Morphia;

@Configuration
public class MorphiaConfig {
	
	@Autowired
	private MongoProperties properties;
	
	@Value("${morphia.mapperPackage}")
	private String mapperPackage;

    @Bean
    public Datastore datastore(){
    	Datastore datastore = Morphia.createDatastore(mongoClient(), properties.getDatabase());
        // 确定Mongo实体类的存放包名
    	datastore.getMapper().mapPackage(mapperPackage);
        //  为实体类创建索引
        datastore.ensureIndexes();
        return datastore;
    }
    
    
    private MongoClient mongoClient() {
		return MongoClients.create(new ConnectionString(getMongoURI()));
    }
    
    private String getMongoURI() {
    	String connectionString = "mongodb://localhost:27017";
    	try {
    		connectionString = "mongodb://username:password@host:port";
			connectionString = connectionString.replace("username", properties.getUsername())
					.replace("password", URLEncoder.encode(String.copyValueOf(properties.getPassword()), "utf-8"))
					.replace("host", properties.getHost())
					.replace("port", String.valueOf(properties.getPort()));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return connectionString;
    }
}
