package com.islandesapp.dao;

import com.islandesapp.model.Palabra;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación simulada de {@link IPalabraDAO} para pruebas unitarias.
 * Emula el comportamiento de persistencia en memoria.
 */
public class PalabraDAOMock implements IPalabraDAO {

    private final List<Palabra> palabras = new ArrayList<>();

    /**
     * Inserta una palabra en la lista de prueba.
     * 
     * @param palabra objeto Palabra a agregar.
     */
    public void insertarPalabra(Palabra palabra) {
        palabras.add(palabra);
    }

    /**
     * Busca una palabra por ID.
     * 
     * @param id identificador de la palabra.
     * @return palabra encontrada o null si no existe.
     */
    public Palabra buscarPalabraPorId(String id) {
        return palabras.stream()
                .filter(p -> p.getId() != null && p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Filtra las palabras por categoría.
     * 
     * @param categoria categoría deseada.
     * @return lista de palabras coincidentes.
     */
    public List<Palabra> buscarPorCategoria(String categoria) {
        return palabras.stream()
                .filter(p -> categoria.equals(p.getCategoria()))
                .collect(Collectors.toList());
    }

    /**
     * Actualiza una palabra: elimina la antigua y añade la nueva.
     * 
     * @param palabra palabra con datos actualizados.
     */
    public void actualizarPalabra(Palabra palabra) {
        eliminarPalabra(palabra.getIslandes());
        insertarPalabra(palabra);
    }

    /**
     * Elimina una palabra por su texto islandés.
     * 
     * @param id texto en islandés.
     */
    public void eliminarPalabra(String id) {
        palabras.removeIf(p -> id.equals(p.getIslandes()));
    }

    /**
     * Devuelve todas las palabras simuladas.
     * 
     * @return copia de la lista de palabras.
     */
    public List<Palabra> obtenerTodas() {
        return new ArrayList<>(palabras);
    }

    /**
     * Obtiene palabras asociadas a una lección.
     * 
     * @param leccionId identificador de la lección.
     * @return lista de palabras.
     */
    public List<Palabra> obtenerPorLeccion(String leccionId) {
        return palabras.stream()
                .filter(p -> leccionId.equals(p.getLeccionId()))
                .collect(Collectors.toList());
    }

    /**
     * Busca una palabra por su texto en islandés.
     * 
     * @param islandes texto buscado.
     * @return palabra encontrada o null.
     */
    public Palabra buscarPorIslandes(String islandes) {
        return palabras.stream()
                .filter(p -> p.getIslandes().equals(islandes))
                .findFirst()
                .orElse(null);
    }

    /**
     * Busca una palabra por texto y lección (no implementado).
     * 
     * @param islandes palabra en islandés.
     * @param leccionId ID de la lección.
     * @return null (funcionalidad aún no desarrollada).
     */
    @Override
    public Palabra buscarPorIslandesYLeccion(String islandes, String leccionId) {
        // TODO Auto-generated method stub
        return null;
    }
}