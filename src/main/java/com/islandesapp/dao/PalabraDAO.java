package com.islandesapp.dao;

import com.islandesapp.model.Palabra;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;

/**
 * Implementación de {@link IPalabraDAO} que gestiona operaciones CRUD
 * sobre documentos de tipo Palabra en una colección MongoDB.
 */
public class PalabraDAO implements IPalabraDAO {

    private final MongoCollection<Document> collection;

    /**
     * Constructor que inicializa la conexión a la colección "palabras".
     */
    public PalabraDAO() {
        MongoDatabase db = MongoConnection.getDatabase();
        collection = db.getCollection("palabras");
    }

    /**
     * Convierte un objeto Palabra a un documento de MongoDB.
     * 
     * @param p objeto {@link Palabra} a convertir.
     * @return documento Mongo equivalente.
     */
    private Document palabraToDocument(Palabra p) {
        return new Document("islandes", p.getIslandes())
                .append("traduccion", p.getTraduccion())
                .append("categoria", p.getCategoria())
                .append("ejemplo", p.getEjemplo())
                .append("leccionId", p.getLeccionId());
    }

    /**
     * Convierte un documento MongoDB a un objeto Palabra.
     * 
     * @param doc documento de la colección.
     * @return instancia de {@link Palabra} o null si el documento es null.
     */
    private Palabra documentToPalabra(Document doc) {
        if (doc == null) return null;
        Palabra palabra = new Palabra(
                doc.getString("islandes"),
                doc.getString("traduccion"),
                doc.getString("categoria"),
                doc.getString("ejemplo"),
                doc.getString("leccionId")
        );
        if (doc.getObjectId("_id") != null) {
            palabra.setId(doc.getObjectId("_id").toHexString());
        }
        return palabra;
    }

    /**
     * Inserta una nueva palabra en la colección.
     * 
     * @param palabra palabra a insertar.
     */
    public void insertarPalabra(Palabra palabra) {
        collection.insertOne(palabraToDocument(palabra));
    }

    /**
     * Busca una palabra por su valor en islandés.
     * 
     * @param islandes texto en islandés.
     * @return palabra encontrada o null si no existe.
     */
    public Palabra buscarPorIslandes(String islandes) {
        Document doc = collection.find(eq("islandes", islandes)).first();
        return documentToPalabra(doc);
    }

    /**
     * Busca una palabra por su identificador único.
     * 
     * @param id identificador de Mongo (_id).
     * @return palabra correspondiente o null.
     */
    public Palabra buscarPalabraPorId(String id) {
        Document doc = collection.find(eq("_id", new ObjectId(id))).first();
        return documentToPalabra(doc);
    }

    /**
     * Devuelve todas las palabras de una categoría concreta.
     * 
     * @param categoria categoría a filtrar.
     * @return lista de palabras de esa categoría.
     */
    public List<Palabra> buscarPorCategoria(String categoria) {
        List<Palabra> lista = new ArrayList<>();
        for (Document doc : collection.find(eq("categoria", categoria))) {
            lista.add(documentToPalabra(doc));
        }
        return lista;
    }

    /**
     * Devuelve todas las palabras asociadas a una lección concreta.
     * 
     * @param idLeccion identificador de la lección.
     * @return lista de palabras asociadas.
     */
    @Override
    public List<Palabra> obtenerPorLeccion(String idLeccion) {
        List<Palabra> lista = new ArrayList<>();
        for (Document doc : collection.find(eq("leccionId", idLeccion))) {
            lista.add(documentToPalabra(doc));
        }
        return lista;
    }

    /**
     * Elimina una palabra mediante su forma en islandés.
     * 
     * @param islandes texto a eliminar.
     */
    public void eliminarPalabra(String islandes) {
        collection.deleteOne(eq("islandes", islandes));
    }

    /**
     * Actualiza una palabra existente en la base de datos.
     * 
     * @param palabra palabra con nuevos valores.
     */
    public void actualizarPalabra(Palabra palabra) {
        collection.updateOne(eq("islandes", palabra.getIslandes()),
                new Document("$set", palabraToDocument(palabra)));
    }

    /**
     * Obtiene todas las palabras existentes en la colección.
     * 
     * @return lista de todas las palabras.
     */
    public List<Palabra> obtenerTodas() {
        List<Palabra> lista = new ArrayList<>();
        for (Document doc : collection.find()) {
            lista.add(documentToPalabra(doc));
        }
        return lista;
    }

    /**
     * Busca una palabra por texto y lección (no implementado).
     * 
     * @param islandes palabra en islandés.
     * @param leccionId ID de la lección.
     * @return null (funcionalidad pendiente).
     */
    @Override
    public Palabra buscarPorIslandesYLeccion(String islandes, String leccionId) {
        // TODO Auto-generated method stub
        return null;
    }
}