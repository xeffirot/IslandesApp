package com.islandesapp.service;

import com.islandesapp.dao.ILeccionDAO;
import com.islandesapp.model.Leccion;

import java.util.List;

/**
 * Servicio para gestionar las operaciones relacionadas con las lecciones.
 * Actúa como intermediario entre el controlador/vista y el DAO de Lección.
 */
public class LeccionService {

    /**
     * DAO para acceso a datos de lecciones.
     */
    private final ILeccionDAO leccionDAO;

    /**
     * Constructor que recibe el DAO de lección para permitir la inyección de dependencias.
     *
     * @param leccionDAO implementación de ILeccionDAO para acceso a datos
     */
    public LeccionService(ILeccionDAO leccionDAO) {
        this.leccionDAO = leccionDAO;
    }

    /**
     * Crea una nueva lección validando que el objeto y su título sean válidos.
     *
     * @param leccion lección a crear
     * @throws IllegalArgumentException si la lección o su título son nulos o vacíos
     */
    public void crearLeccion(Leccion leccion) {
        if (leccion == null || leccion.getTitulo() == null || leccion.getTitulo().isEmpty()) {
            throw new IllegalArgumentException("La lección debe tener un título válido");
        }
        leccionDAO.insertarLeccion(leccion);
    }

    /**
     * Obtiene una lección por su identificador.
     *
     * @param id identificador único de la lección
     * @return la lección encontrada o null si no existe
     * @throws IllegalArgumentException si el id es nulo o vacío
     */
    public Leccion obtenerLeccionPorId(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("El ID no puede estar vacío");
        }
        return leccionDAO.buscarLeccionPorId(id);
    }

    /**
     * Busca una lección por su título.
     *
     * @param titulo título de la lección a buscar
     * @return la lección encontrada o null si no existe
     * @throws IllegalArgumentException si el título es nulo o vacío
     */
    public Leccion buscarPorTitulo(String titulo) {
        if (titulo == null || titulo.isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío");
        }
        return leccionDAO.buscarPorTitulo(titulo);
    }

    /**
     * Actualiza una lección existente.
     *
     * @param leccion lección con datos actualizados
     * @throws IllegalArgumentException si la lección o su id son nulos o vacíos
     */
    public void actualizarLeccion(Leccion leccion) {
        if (leccion == null || leccion.getId() == null || leccion.getId().isEmpty()) {
            throw new IllegalArgumentException("La lección debe tener un ID válido para actualizar");
        }
        leccionDAO.actualizarLeccion(leccion);
    }

    /**
     * Elimina una lección por su id.
     *
     * @param id identificador de la lección a eliminar
     * @throws IllegalArgumentException si el id es nulo o vacío
     */
    public void eliminarLeccion(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("El ID no puede estar vacío");
        }
        leccionDAO.eliminarLeccion(id);
    }

    /**
     * Obtiene la lista de todas las lecciones disponibles.
     *
     * @return lista de todas las lecciones
     */
    public List<Leccion> listarTodasLasLecciones() {
        return leccionDAO.obtenerTodas();
    }
}