package com.islandesapp.dao;

import com.islandesapp.model.Leccion;
import java.util.List;

/**
 * Interfaz DAO que define las operaciones CRUD para la entidad {@link Leccion}.
 */
public interface ILeccionDAO {

    /**
     * Inserta una nueva lección en la base de datos.
     * 
     * @param leccion La lección a insertar.
     */
    void insertarLeccion(Leccion leccion);

    /**
     * Busca una lección por su identificador único.
     * 
     * @param id Identificador de la lección.
     * @return La lección encontrada, o {@code null} si no existe.
     */
    Leccion buscarLeccionPorId(String id);

    /**
     * Busca una lección por su título exacto.
     * 
     * @param titulo Título de la lección.
     * @return La lección correspondiente, o {@code null} si no se encuentra.
     */
    Leccion buscarPorTitulo(String titulo);

    /**
     * Actualiza los datos de una lección existente.
     * 
     * @param leccion La lección con la información actualizada.
     */
    void actualizarLeccion(Leccion leccion);

    /**
     * Elimina una lección por su identificador.
     * 
     * @param id Identificador de la lección a eliminar.
     */
    void eliminarLeccion(String id);

    /**
     * Recupera todas las lecciones almacenadas.
     * 
     * @return Lista de todas las lecciones.
     */
    List<Leccion> obtenerTodas();
}