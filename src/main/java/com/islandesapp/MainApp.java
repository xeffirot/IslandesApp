package com.islandesapp;

import com.islandesapp.dao.LeccionDAO;
import com.islandesapp.dao.PalabraDAO;
import com.islandesapp.dao.MongoConnection;
import com.islandesapp.service.LeccionService;
import com.islandesapp.service.PalabraService;
import com.islandesapp.ui.LeccionView;

import javax.swing.*;

/**
 * Clase principal que inicia la aplicación
 * mostrando la interfaz para gestionar lecciones.
 */
public class MainApp {

    /**
     * Método main que arranca la aplicación.
     * Configura la conexión y lanza la ventana principal.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MongoConnection.connect(); // Conecta a MongoDB

            LeccionDAO leccionDAO = new LeccionDAO();
            PalabraDAO palabraDAO = new PalabraDAO(); // Añadido para PalabraService

            LeccionService leccionService = new LeccionService(leccionDAO);
            PalabraService palabraService = new PalabraService(palabraDAO);

            JFrame frame = new JFrame("Gestor de Lecciones");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);

            // Pasamos ambos servicios al constructor
            LeccionView leccionView = new LeccionView(leccionService, palabraService);
            frame.setContentPane(leccionView);

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}