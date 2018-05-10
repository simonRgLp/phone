package com.example.phone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

@Configuration
public class MongoConfig {
	
	public @Bean MongoClient mongoClient() {
		return MongoClients.create("mongodb://localhost");
	}
	
}
