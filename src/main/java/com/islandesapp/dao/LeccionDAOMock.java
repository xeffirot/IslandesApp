package com.islandesapp.dao;

import com.islandesapp.model.Leccion;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Implementación simulada de ILeccionDAO que almacena datos en memoria.
 * Útil para pruebas sin conexión a base de datos.
 */
public class LeccionDAOMock implements ILeccionDAO {

    private final List<Leccion> listaLecciones = new ArrayList<>();

    /**
     * Inserta una lección en la lista simulada.
     * Si no tiene id, se genera automáticamente.
     * @param leccion Objeto Leccion a insertar.
     */
    @Override
    public void insertarLeccion(Leccion leccion) {
        if (leccion.getId() == null || leccion.getId().isEmpty()) {
            leccion.setId(UUID.randomUUID().toString());
        }
        listaLecciones.add(leccion);
    }

    /**
     * Busca una lección por su identificador.
     * @param id Identificador de la lección.
     * @return Leccion encontrada o null si no existe.
     */
    @Override
    public Leccion buscarLeccionPorId(String id) {
        return listaLecciones.stream()
                .filter(l -> l.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Busca una lección por su título (sin distinguir mayúsculas/minúsculas).
     * @param titulo Título de la lección.
     * @return Leccion encontrada o null si no existe.
     */
    @Override
    public Leccion buscarPorTitulo(String titulo) {
        return listaLecciones.stream()
                .filter(l -> l.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null);
    }

    /**
     * Actualiza los datos de una lección existente.
     * @param leccion Objeto Leccion actualizado.
     */
    @Override
    public void actualizarLeccion(Leccion leccion) {
        for (int i = 0; i < listaLecciones.size(); i++) {
            if (listaLecciones.get(i).getId().equals(leccion.getId())) {
                listaLecciones.set(i, leccion);
                return;
            }
        }
    }

    /**
     * Elimina una lección de la lista usando su id.
     * @param id Identificador de la lección.
     */
    @Override
    public void eliminarLeccion(String id) {
        listaLecciones.removeIf(l -> l.getId().equals(id));
    }

    /**
     * Devuelve una copia de todas las lecciones simuladas.
     * @return Lista de Lecciones.
     */
    @Override
    public List<Leccion> obtenerTodas() {
        return new ArrayList<>(listaLecciones);
    }
}