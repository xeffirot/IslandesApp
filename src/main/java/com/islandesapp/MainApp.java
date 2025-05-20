package com.islandesapp;

import com.islandesapp.dao.LeccionDAO;
import com.islandesapp.dao.MongoConnection;
import com.islandesapp.model.Leccion;
import com.islandesapp.service.LeccionService;
import com.mongodb.client.MongoDatabase;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        System.out.println("IslandesApp - LeccionService test");

        // Conectar a MongoDB
        MongoConnection.connect();
        MongoDatabase database = MongoConnection.getDatabase();

        LeccionDAO leccionDAO = new LeccionDAO(database);
        LeccionService leccionService = new LeccionService(leccionDAO);

        // Crear una nueva lección
        Leccion leccion = new Leccion("Saludos", "Lección sobre saludos básicos en islandés.");
        leccionService.crearLeccion(leccion);
        System.out.println("Lección creada: " + leccion.getTitulo());

        // Listar todas las lecciones
        List<Leccion> lecciones = leccionService.listarTodasLasLecciones();
        for (Leccion l : lecciones) {
            System.out.println("Lección encontrada: " + l.getTitulo() + " - " + l.getDescripcion());
        }

        // Cerrar conexión
        MongoConnection.close();

        System.out.println("Fin del test de LeccionService.");
    }
}