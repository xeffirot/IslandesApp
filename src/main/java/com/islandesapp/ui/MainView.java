package com.islandesapp.ui;

import com.islandesapp.model.Leccion;
import com.islandesapp.service.LeccionService;
import com.islandesapp.service.PalabraService;

import javax.swing.*;

/**
 * Vista principal de la aplicación que contiene
 * la interfaz para gestionar Lecciones y Palabras.
 *
 * Combina dos paneles (LeccionView y PalabraView)
 * usando un JSplitPane para mostrar ambas vistas.
 */
public class MainView extends JFrame {

    /**
     * Panel con la lista y gestión de Lecciones.
     */
    private LeccionView leccionView;

    /**
     * Panel con la lista y gestión de Palabras.
     */
    private PalabraView palabraView;

    /**
     * Constructor que inicializa la ventana principal
     * con las vistas de lecciones y palabras.
     *
     * @param leccionService servicio para gestionar lecciones
     * @param palabraService servicio para gestionar palabras
     */
    public MainView(LeccionService leccionService, PalabraService palabraService) {
        setTitle("IslandesApp");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        leccionView = new LeccionView(leccionService, palabraService);
        palabraView = new PalabraView(palabraService);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leccionView, palabraView);
        splitPane.setDividerLocation(300);

        getContentPane().add(splitPane);

        // Escuchar selección de lecciones para actualizar palabras
        leccionView.getListaLecciones().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int index = leccionView.getListaLecciones().getSelectedIndex();
                if (index != -1) {
                    Leccion seleccionada = leccionView.getLecciones().get(index);
                    palabraView.setLeccionId(
                        seleccionada.getId(),
                        palabraService.buscarPalabrasPorLeccion(seleccionada.getId())
                    );
                }
            }
        });
    }
}