package com.islandesapp.dao;

import com.islandesapp.model.Palabra;
import java.util.List;

/**
 * Interfaz DAO que define las operaciones CRUD para la entidad {@link Palabra}.
 */
public interface IPalabraDAO {

    /**
     * Inserta una nueva palabra en la base de datos.
     * 
     * @param palabra La palabra a insertar.
     */
    void insertarPalabra(Palabra palabra);

    /**
     * Busca una palabra por su identificador único.
     * 
     * @param id Identificador de la palabra.
     * @return La palabra encontrada, o {@code null} si no existe.
     */
    Palabra buscarPalabraPorId(String id);

    /**
     * Busca todas las palabras que pertenecen a una categoría específica.
     * 
     * @param categoria Categoría deseada.
     * @return Lista de palabras dentro de esa categoría.
     */
    List<Palabra> buscarPorCategoria(String categoria);

    /**
     * Actualiza la información de una palabra existente.
     * 
     * @param palabra Palabra con datos actualizados.
     */
    void actualizarPalabra(Palabra palabra);

    /**
     * Elimina una palabra mediante su identificador.
     * 
     * @param id Identificador de la palabra a eliminar.
     */
    void eliminarPalabra(String id);

    /**
     * Recupera todas las palabras almacenadas.
     * 
     * @return Lista completa de palabras.
     */
    List<Palabra> obtenerTodas();

    /**
     * Obtiene todas las palabras asociadas a una lección específica.
     * 
     * @param leccionId Identificador de la lección.
     * @return Lista de palabras correspondientes a esa lección.
     */
    List<Palabra> obtenerPorLeccion(String leccionId);

    /**
     * Busca una palabra por su forma escrita en islandés.
     * 
     * @param islandes Palabra en islandés.
     * @return La palabra encontrada, o {@code null} si no existe.
     */
    Palabra buscarPorIslandes(String islandes);

    /**
     * Busca una palabra por su forma en islandés y su lección asociada.
     * 
     * @param islandes Palabra en islandés.
     * @param leccionId ID de la lección.
     * @return La palabra encontrada o {@code null}.
     */
    Palabra buscarPorIslandesYLeccion(String islandes, String leccionId);
}