package controlador;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;

public class Ejercicio4 {

	MongoCollection<Document> collection;

	private Ejercicio4() {
		// Obtiene la coleccion "Peliculas" de la BD.
		collection = Conexion.getInstance().coleccionesDePeliculas();
	}

	public int tarea4a() {
		// Creo un objeto Document con los criterios de búsqueda
		Document query = new Document("titulo", "Harry Potter y la Orden del Fénix");
		// Borra el documento que coincide con los criterios de búsqueda
		DeleteResult result = collection.deleteOne(query);
		return (int) result.getDeletedCount();
	}

	public int tarea4b() {
		// Creo el filtro con los criterios de búsqueda.
		Document query = new Document("basadaen", new Document("$regex", "cáliz de fuego"));
		// Borra el primer documento que coincide con los criterios de búsqueda
		DeleteResult result = collection.deleteOne(query);
		return (int) result.getDeletedCount();
	}

	// Patrón Singleton.
	private static Ejercicio4 obj = null;

	public static Ejercicio4 getInstance() {
		if (obj == null) {
			obj = new Ejercicio4();
		}
		return obj;
	}
}
