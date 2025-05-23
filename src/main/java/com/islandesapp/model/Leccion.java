package com.islandesapp.model;

import java.util.List;
import java.util.Objects;

/**
 * Representa una lección dentro de la aplicación IslandesApp.
 * Cada lección tiene un título, una descripción y una lista de palabras asociadas.
 * Es una de las entidades principales del modelo de aprendizaje.
 */
public class Leccion {

    private String id;
    private String titulo;
    private String descripcion;
    private List<Palabra> palabras;

    /**
     * Constructor vacío requerido por frameworks como MongoDB o herramientas de serialización.
     */
    public Leccion() {}

    /**
     * Constructor completo que incluye el ID, útil para reconstruir lecciones desde la base de datos.
     *
     * @param id          Identificador único de la lección.
     * @param titulo      Título de la lección (ej. "Saludos básicos").
     * @param descripcion Breve resumen del contenido de la lección.
     */
    public Leccion(String id, String titulo, String descripcion) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    /**
     * Constructor sin ID, útil al crear nuevas lecciones antes de ser persistidas.
     *
     * @param titulo      Título de la lección.
     * @param descripcion Descripción de la lección.
     */
    public Leccion(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    // ==================== Getters y Setters ====================

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Palabra> getPalabras() {
        return palabras;
    }

    public void setPalabras(List<Palabra> palabras) {
        this.palabras = palabras;
    }

    /**
     * Devuelve una representación en texto de la lección, incluyendo título y número de palabras.
     * Útil para propósitos de depuración o logging.
     *
     * @return Cadena representando la lección.
     */
    @Override
    public String toString() {
        return "Leccion{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", palabras=" + (palabras != null ? palabras.size() : 0) +
                '}';
    }

    /**
     * Compara dos lecciones usando su ID. Se considera que dos lecciones son iguales
     * si tienen el mismo identificador.
     *
     * @param o Objeto a comparar.
     * @return true si los IDs coinciden, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Leccion)) return false;
        Leccion leccion = (Leccion) o;
        return Objects.equals(id, leccion.id);
    }

    /**
     * Calcula el código hash de la lección basándose en su ID.
     *
     * @return Valor hash de la lección.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}