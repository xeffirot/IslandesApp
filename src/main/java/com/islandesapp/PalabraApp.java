package com.islandesapp;

import com.islandesapp.dao.MongoConnection;
import com.islandesapp.dao.PalabraDAO;
import com.islandesapp.model.Palabra;

public class PalabraApp {
    public static void main(String[] args) {
        System.out.println("IslandesApp - PalabraDAO test");

        // Conectar a MongoDB
        MongoConnection.connect();

        PalabraDAO palabraDAO = new PalabraDAO();

        // Crear una palabra nueva
        Palabra palabra = new Palabra("hundur", "perro", "animales");

        // Insertar la palabra en la base de datos
        palabraDAO.insertar(palabra);
        System.out.println("Palabra insertada: " + palabra);

        // Buscar la palabra por islandés
        Palabra encontrada = palabraDAO.buscarPorIslandes("hundur");
        System.out.println("Palabra encontrada: " + encontrada);

        // Actualizar la palabra
        encontrada.setEspanol("can");
        palabraDAO.actualizar(encontrada);
        System.out.println("Palabra actualizada: " + palabraDAO.buscarPorIslandes("hundur"));

        // Borrar la palabra
        palabraDAO.borrar("hundur");
        System.out.println("Palabra borrada.");

        // Cerrar conexión
        MongoConnection.close();

        System.out.println("Fin del test de PalabraDAO.");
    }
}