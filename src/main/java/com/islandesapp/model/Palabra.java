package com.islandesapp.model;

/**
 * Representa una palabra en islandés dentro de la aplicación IslandesApp.
 * Cada palabra incluye su traducción, categoría gramatical o temática,
 * un ejemplo de uso y la referencia a la lección a la que pertenece.
 */
public class Palabra {

    private String id;
    private String islandes;
    private String traduccion;
    private String categoria;
    private String ejemplo;
    private String leccionId;

    /**
     * Constructor vacío necesario para frameworks como MongoDB.
     */
    public Palabra() {}

    /**
     * Constructor para crear una nueva palabra sin ID asignado.
     * Se usa normalmente antes de almacenar en la base de datos.
     *
     * @param islandes   Palabra original en islandés.
     * @param traduccion Traducción de la palabra al español.
     * @param categoria  Categoría (gramatical o temática) como "verbo", "saludos", etc.
     * @param ejemplo    Frase que muestra la palabra en contexto.
     * @param leccionId  ID de la lección a la que pertenece esta palabra.
     */
    public Palabra(String islandes, String traduccion, String categoria, String ejemplo, String leccionId) {
        this.islandes = islandes;
        this.traduccion = traduccion;
        this.categoria = categoria;
        this.ejemplo = ejemplo;
        this.leccionId = leccionId;
    }

    // ==================== Getters y Setters ====================

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIslandes() {
        return islandes;
    }

    public void setIslandes(String islandes) {
        this.islandes = islandes;
    }

    public String getTraduccion() {
        return traduccion;
    }

    public void setTraduccion(String traduccion) {
        this.traduccion = traduccion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEjemplo() {
        return ejemplo;
    }

    public void setEjemplo(String ejemplo) {
        this.ejemplo = ejemplo;
    }

    public String getLeccionId() {
        return leccionId;
    }

    public void setLeccionId(String leccionId) {
        this.leccionId = leccionId;
    }

    /**
     * Representación en texto de la palabra, útil para debugging y logs.
     *
     * @return Cadena que representa la palabra con todos sus atributos.
     */
    @Override
    public String toString() {
        return "Palabra{" +
                "id='" + id + '\'' +
                ", islandes='" + islandes + '\'' +
                ", traduccion='" + traduccion + '\'' +
                ", categoria='" + categoria + '\'' +
                ", ejemplo='" + ejemplo + '\'' +
                ", leccionId='" + leccionId + '\'' +
                '}';
    }
}
