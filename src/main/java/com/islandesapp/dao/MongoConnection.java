package com.islandesapp.dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 * Clase para gestionar la conexión a una base de datos MongoDB.
 * Utiliza un patrón singleton para mantener una única conexión abierta.
 */
public class MongoConnection {

    private static MongoClient mongoClient;
    private static MongoDatabase database;

    /**
     * Establece una conexión con la base de datos MongoDB.
     * Utiliza la URL por defecto para una instancia local.
     */
    public static void connect() {
        String connectionString = "mongodb://localhost:27017";
        mongoClient = MongoClients.create(connectionString);
        database = mongoClient.getDatabase("islandesdb");
        System.out.println("Conectado a MongoDB: " + database.getName());
    }

    /**
     * Devuelve la instancia activa de la base de datos.
     * Si no hay conexión previa, la establece automáticamente.
     * 
     * @return instancia de {@link MongoDatabase} conectada.
     */
    public static MongoDatabase getDatabase() {
        if (database == null) {
            connect();
        }
        return database;
    }

    /**
     * Cierra la conexión activa con la base de datos.
     */
    public static void close() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("Conexión cerrada.");
        }
    }
}