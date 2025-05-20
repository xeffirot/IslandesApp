package com.islandesapp.model;

public class Palabra {
    private String islandes;
    private String espanol;
    private String categoria;

    public Palabra() {
        // Constructor vacío necesario para MongoDB
    }

    public Palabra(String islandes, String espanol, String categoria) {
        this.islandes = islandes;
        this.espanol = espanol;
        this.categoria = categoria;
    }

    public String getIslandes() {
        return islandes;
    }

    public void setIslandes(String islandes) {
        this.islandes = islandes;
    }

    public String getEspanol() {
        return espanol;
    }

    public void setEspanol(String espanol) {
        this.espanol = espanol;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Palabra{" +
                "islandes='" + islandes + '\'' +
                ", espanol='" + espanol + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}