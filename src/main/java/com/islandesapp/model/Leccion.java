package com.islandesapp.model;

import java.util.List;

public class Leccion {

    private String id;
    private String titulo;
    private String descripcion;
    private List<Palabra> palabras;

    public Leccion() {
        // Constructor vacío necesario para MongoDB
    }

    public Leccion(String id, String titulo, String descripcion, List<Palabra> palabras) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.palabras = palabras;
    }
    
    public Leccion(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

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

    @Override
    public String toString() {
        return "Leccion{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", palabras=" + palabras +
                '}';
    }
}