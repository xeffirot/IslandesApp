package com.islandesapp.service;

import com.islandesapp.dao.IPalabraDAO;
import com.islandesapp.model.Palabra;

import java.util.List;

/**
 * Servicio para gestionar operaciones relacionadas con las palabras.
 * Proporciona validaciones y coordina con el DAO correspondiente.
 */
public class PalabraService {

    /**
     * DAO para acceso a datos de palabras.
     */
    private final IPalabraDAO palabraDAO;

    /**
     * Constructor que inyecta la implementación del DAO de palabras.
     *
     * @param palabraDAO implementación de IPalabraDAO para acceso a datos
     */
    public PalabraService(IPalabraDAO palabraDAO) {
        this.palabraDAO = palabraDAO;
    }

    /**
     * Crea una palabra nueva, validando datos esenciales.
     *
     * @param palabra palabra a crear
     * @throws IllegalArgumentException si la palabra en islandés o el id de lección son nulos o vacíos
     */
    private void validarPalabra(Palabra palabra) {
        if (palabra == null || palabra.getIslandes() == null || palabra.getIslandes().isEmpty()) {
            throw new IllegalArgumentException("La palabra en islandés es obligatoria");
        }
        if (palabra.getLeccionId() == null || palabra.getLeccionId().isEmpty()) {
            throw new IllegalArgumentException("La palabra debe pertenecer a una lección válida");
        }
    }

    public void crearPalabra(Palabra palabra) {
        validarPalabra(palabra);
        palabraDAO.insertarPalabra(palabra);
    }

    public void crearOActualizarPalabra(Palabra palabra) {
        validarPalabra(palabra);

        Palabra existente = palabraDAO.buscarPorIslandes(palabra.getIslandes());
        if (existente != null) {
            palabra.setId(existente.getId());
            palabraDAO.actualizarPalabra(palabra);
        } else {
            palabraDAO.insertarPalabra(palabra);
        }
    }

    /**
     * Crea o actualiza una palabra dependiendo de si existe en la base de datos.
     *
     * @param palabra palabra a crear o actualizar
     * @throws IllegalArgumentException si los datos esenciales están vacíos
     */
    public void crearOPactualizarPalabra(Palabra palabra) {
        if (palabra == null || palabra.getIslandes() == null || palabra.getIslandes().isEmpty()) {
            throw new IllegalArgumentException("La palabra en islandés es obligatoria");
        }
        if (palabra.getLeccionId() == null || palabra.getLeccionId().isEmpty()) {
            throw new IllegalArgumentException("La palabra debe pertenecer a una lección válida");
        }

        Palabra existente = palabraDAO.buscarPorIslandes(palabra.getIslandes());
        if (existente != null) {
            palabra.setId(existente.getId()); // mantener ID original
            palabraDAO.actualizarPalabra(palabra);
        } else {
            palabraDAO.insertarPalabra(palabra);
        }
    }

    /**
     * Obtiene una palabra por su identificador.
     *
     * @param id identificador único de la palabra
     * @return la palabra encontrada o null si no existe
     */
    public Palabra obtenerPalabraPorId(String id) {
        return palabraDAO.buscarPalabraPorId(id);
    }

    /**
     * Busca palabras por categoría.
     *
     * @param categoria categoría de las palabras a buscar
     * @return lista de palabras que coinciden con la categoría
     * @throws IllegalArgumentException si la categoría es nula o vacía
     */
    public List<Palabra> buscarPorCategoria(String categoria) {
        if (categoria == null || categoria.isEmpty()) {
            throw new IllegalArgumentException("La categoría no puede estar vacía");
        }
        return palabraDAO.buscarPorCategoria(categoria);
    }

    /**
     * Actualiza una palabra existente.
     *
     * @param palabra palabra con datos actualizados
     * @throws IllegalArgumentException si la palabra o su id son nulos o vacíos
     */
    public void actualizarPalabra(Palabra palabra) {
        if (palabra == null || palabra.getId() == null || palabra.getId().isEmpty()) {
            throw new IllegalArgumentException("La palabra debe tener un ID válido para actualizar");
        }
        palabraDAO.actualizarPalabra(palabra);
    }

    /**
     * Elimina una palabra por su id.
     *
     * @param id identificador de la palabra a eliminar
     * @throws IllegalArgumentException si el id es nulo o vacío
     */
    public void eliminarPalabra(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("El ID no puede estar vacío");
        }
        palabraDAO.eliminarPalabra(id);
    }

    /**
     * Lista todas las palabras disponibles.
     *
     * @return lista de todas las palabras
     */
    public List<Palabra> listarTodasLasPalabras() {
        return palabraDAO.obtenerTodas();
    }

    /**
     * Lista las palabras que pertenecen a una lección específica.
     *
     * @param leccionId id de la lección
     * @return lista de palabras asociadas a la lección
     * @throws IllegalArgumentException si el id de la lección es nulo o vacío
     */
    public List<Palabra> buscarPalabrasPorLeccion(String leccionId) {
        if (leccionId == null || leccionId.isEmpty()) {
            throw new IllegalArgumentException("El ID de la lección no puede estar vacío");
        }
        return palabraDAO.obtenerPorLeccion(leccionId);
    }

    /**
     * Busca una palabra por su forma en islandés.
     *
     * @param islandes palabra en islandés a buscar
     * @return la palabra encontrada o null si no existe
     * @throws IllegalArgumentException si la palabra en islandés es nula o vacía
     */
    public Palabra buscarPorIslandes(String islandes) {
        if (islandes == null || islandes.isEmpty()) {
            throw new IllegalArgumentException("La palabra en islandés no puede estar vacía");
        }
        return palabraDAO.buscarPorIslandes(islandes);
    }
    
    
    
    
}
