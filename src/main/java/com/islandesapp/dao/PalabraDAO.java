package com.islandesapp.dao;

import com.islandesapp.model.Palabra;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class PalabraDAO {

    private final MongoCollection<Document> collection;

    public PalabraDAO() {
        MongoDatabase db = MongoConnection.getDatabase();
        collection = db.getCollection("palabras");
    }

    // Convertir Palabra a Document
    private Document palabraToDocument(Palabra p) {
        return new Document("islandes", p.getIslandes())
                .append("espanol", p.getEspanol())
                .append("categoria", p.getCategoria());
    }

    // Convertir Document a Palabra
    private Palabra documentToPalabra(Document doc) {
        if (doc == null) return null;
        return new Palabra(
            doc.getString("islandes"),
            doc.getString("espanol"),
            doc.getString("categoria")
        );
    }

    // Insertar una palabra
    public void insertar(Palabra palabra) {
        collection.insertOne(palabraToDocument(palabra));
    }

    // Buscar palabra por islandes
    public Palabra buscarPorIslandes(String islandes) {
        Document doc = collection.find(eq("islandes", islandes)).first();
        return documentToPalabra(doc);
    }

    // Actualizar traducción por islandes
    public void actualizar(Palabra palabra) {
        collection.updateOne(
            eq("islandes", palabra.getIslandes()),
            new Document("$set", palabraToDocument(palabra))
        );
    }

    // Borrar palabra por islandes
    public void borrar(String islandes) {
        collection.deleteOne(eq("islandes", islandes));
    }
}