package com.islandesapp;

import com.islandesapp.dao.MongoConnection;
import com.islandesapp.dao.PalabraDAO;
import com.islandesapp.model.Palabra;

/**
 * Aplicación de prueba para PalabraDAO.
 * Realiza operaciones CRUD sobre palabras usando MongoDB.
 */
public class PalabraApp {

    /**
     * Método principal que prueba las funcionalidades
     * de inserción, búsqueda, actualización y eliminación
     * de palabras en la base de datos MongoDB.
     *
     * @param args argumentos de línea de comandos (no usados)
     */
    public static void main(String[] args) {
        System.out.println("IslandesApp - PalabraDAO test");

        // Conectar a MongoDB
        MongoConnection.connect();

        PalabraDAO palabraDAO = new PalabraDAO();

        // Crear una nueva palabra
        Palabra palabra = new Palabra("hundur", "perro", "animales", "El hundur es muy amigable.", "leccion1");

        // Insertar palabra en la base de datos
        palabraDAO.insertarPalabra(palabra);
        System.out.println("Palabra insertada: " + palabra);

        // Buscar palabra por islandés
        Palabra encontrada = palabraDAO.buscarPorIslandes("hundur");
        System.out.println("Palabra encontrada: " + encontrada);

        // Actualizar traducción de la palabra
        encontrada.setTraduccion("can");
        palabraDAO.actualizarPalabra(encontrada);
        System.out.println("Palabra actualizada: " + palabraDAO.buscarPorIslandes("hundur"));

        // Eliminar palabra de la base de datos
        palabraDAO.eliminarPalabra("hundur");
        System.out.println("Palabra borrada.");

        // Cerrar conexión con MongoDB
        MongoConnection.close();

        System.out.println("Fin del test de PalabraDAO.");
    }
}