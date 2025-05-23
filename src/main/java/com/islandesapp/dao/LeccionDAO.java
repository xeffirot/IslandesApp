package com.islandesapp.dao;

import com.islandesapp.model.Leccion;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de ILeccionDAO que utiliza MongoDB como base de datos.
 * Proporciona operaciones CRUD para la entidad Leccion.
 */
public class LeccionDAO implements ILeccionDAO {

    private final MongoCollection<Document> coleccion;

    /**
     * Constructor que inicializa la colección "lecciones" desde la conexión MongoDB.
     */
    public LeccionDAO() {
        MongoDatabase database = MongoConnection.getDatabase();
        coleccion = database.getCollection("lecciones");
    }

    /**
     * Inserta una nueva lección en la base de datos.
     * @param leccion Objeto Leccion a insertar.
     */
    @Override
    public void insertarLeccion(Leccion leccion) {
        Document doc = new Document("titulo", leccion.getTitulo())
                .append("descripcion", leccion.getDescripcion());
        coleccion.insertOne(doc);
    }

    /**
     * Busca una lección por su identificador único.
     * @param id Identificador en formato hexadecimal de MongoDB.
     * @return Objeto Leccion si se encuentra, o null si no existe o el id no es válido.
     */
    @Override
    public Leccion buscarLeccionPorId(String id) {
        try {
            Document doc = coleccion.find(new Document("_id", new ObjectId(id))).first();
            if (doc != null) {
                return new Leccion(doc.getObjectId("_id").toHexString(),
                        doc.getString("titulo"),
                        doc.getString("descripcion"));
            }
        } catch (IllegalArgumentException e) {
            // El id proporcionado no es un ObjectId válido
        }
        return null;
    }

    /**
     * Busca una lección por su título exacto.
     * @param titulo Título de la lección.
     * @return Objeto Leccion si se encuentra, o null si no existe.
     */
    @Override
    public Leccion buscarPorTitulo(String titulo) {
        Document doc = coleccion.find(new Document("titulo", titulo)).first();
        if (doc != null) {
            return new Leccion(doc.getObjectId("_id").toHexString(),
                    doc.getString("titulo"),
                    doc.getString("descripcion"));
        }
        return null;
    }

    /**
     * Actualiza los datos de una lección existente basada en su id.
     * @param leccion Objeto Leccion con id y datos actualizados.
     */
    @Override
    public void actualizarLeccion(Leccion leccion) {
        Document filtro = new Document("_id", new ObjectId(leccion.getId()));
        Document actualizacion = new Document("$set", new Document("titulo", leccion.getTitulo())
                .append("descripcion", leccion.getDescripcion()));
        coleccion.updateOne(filtro, actualizacion);
    }

    /**
     * Elimina una lección de la base de datos usando su id.
     * @param id Identificador de la lección a eliminar.
     */
    @Override
    public void eliminarLeccion(String id) {
        try {
            coleccion.deleteOne(new Document("_id", new ObjectId(id)));
        } catch (IllegalArgumentException e) {
            // id no válido
        }
    }

    /**
     * Recupera todas las lecciones almacenadas en la base de datos.
     * @return Lista de objetos Leccion.
     */
    @Override
    public List<Leccion> obtenerTodas() {
        List<Leccion> lecciones = new ArrayList<>();
        for (Document doc : coleccion.find()) {
            Leccion leccion = new Leccion(doc.getObjectId("_id").toHexString(),
                    doc.getString("titulo"),
                    doc.getString("descripcion"));
            lecciones.add(leccion);
        }
        return lecciones;
    }
}