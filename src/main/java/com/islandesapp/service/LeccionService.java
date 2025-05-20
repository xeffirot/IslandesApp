package com.islandesapp.service;

import com.islandesapp.dao.LeccionDAO;
import com.islandesapp.model.Leccion;

import java.util.List;

public class LeccionService {

    private final LeccionDAO leccionDAO;

    public LeccionService(LeccionDAO leccionDAO) {
        this.leccionDAO = leccionDAO;
    }

    // Crear una nueva lección
    public void crearLeccion(Leccion leccion) {
        // Aquí podrías validar la lección antes de insertarla
        if (leccion == null || leccion.getTitulo() == null || leccion.getTitulo().isEmpty()) {
            throw new IllegalArgumentException("La lección debe tener un título válido");
        }
        leccionDAO.insertarLeccion(leccion);
    }

    // Obtener una lección por ID
    public Leccion obtenerLeccionPorId(String id) {
        return leccionDAO.buscarLeccionPorId(id);
    }

    // Actualizar lección
    public void actualizarLeccion(Leccion leccion) {
        if (leccion == null || leccion.getId() == null || leccion.getId().isEmpty()) {
            throw new IllegalArgumentException("La lección debe tener un ID válido para actualizar");
        }
        leccionDAO.actualizarLeccion(leccion);
    }

    // Eliminar lección por ID
    public void eliminarLeccion(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("El ID no puede estar vacío");
        }
        leccionDAO.eliminarLeccion(id);
    }

    // Obtener todas las lecciones
    public List<Leccion> listarTodasLasLecciones() {
        return leccionDAO.obtenerTodas();
    }
}