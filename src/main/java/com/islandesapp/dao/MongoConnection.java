package com.islandesapp.dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {
    
    private static MongoClient mongoClient;
    private static MongoDatabase database;
    
    // Connect to MongoDB running locally on default port 27017
    public static void connect() {
        // Replace "islandesdb" with your DB name
        String connectionString = "mongodb://localhost:27017";
        mongoClient = MongoClients.create(connectionString);
        database = mongoClient.getDatabase("islandesdb");
        System.out.println("Connected to MongoDB database: " + database.getName());
    }
    
    public static MongoDatabase getDatabase() {
        if (database == null) {
            connect();
        }
        return database;
    }
    
    public static void close() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
