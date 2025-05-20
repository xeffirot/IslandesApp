package com.islandesapp.dao;

import com.islandesapp.model.Leccion;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.types.ObjectId;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

public class LeccionDAO {

    private final MongoCollection<Leccion> coleccion;

    public LeccionDAO(MongoDatabase database) {
        // Creamos o accedemos a la colección 'lecciones'
        coleccion = database.getCollection("lecciones", Leccion.class);
    }

    // Insertar nueva lección
    public void insertarLeccion(Leccion leccion) {
        coleccion.insertOne(leccion);
    }

    // Buscar lección por id
    public Leccion buscarLeccionPorId(String id) {
        return coleccion.find(eq("_id", new ObjectId(id))).first();
    }

    // Actualizar una lección (por id)
    public void actualizarLeccion(Leccion leccion) {
        coleccion.replaceOne(eq("_id", new ObjectId(leccion.getId())), leccion);
    }

    // Eliminar lección por id
    public void eliminarLeccion(String id) {
        coleccion.deleteOne(eq("_id", new ObjectId(id)));
    }

    // Obtener todas las lecciones
    public List<Leccion> obtenerTodas() {
        List<Leccion> lista = new ArrayList<>();
        coleccion.find().into(lista);
        return lista;
    }
}