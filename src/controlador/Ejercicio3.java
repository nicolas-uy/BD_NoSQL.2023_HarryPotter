package controlador;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;

public class Ejercicio3 {

	MongoCollection<Document> collection;
	MongoCursor<Document> cursor;

	private Ejercicio3() {
		// Obtiene la coleccion "Peliculas" de la BD.
		collection = Conexion.getInstance().coleccionesDePeliculas();
	}

	public int tarea3a() {
		// Creo un filtro con los criterios de búsqueda
		Document filter = new Document("anio", 2003);
		// Croa la actualización que deseo aplicar.
		Document update = new Document("$set", new Document("anio", 2002));
		// Actualizo todos los documentos que coinciden con los criterios de búsqueda
		UpdateResult result = collection.updateMany(filter, update);
		return (int) result.getModifiedCount();

	}

	public int tarea3b() {
		// Creo un filtro para seleccionar el documento que deseo actualizar.
		Document filter = new Document("anio", 2004);
		// Creo el campo que deseo agregar
		Document newField = new Document("basadaen", "Harry Potter y el prisionero de Azkaban, de J. K. Rowling.");
		// Crea la actualización que agrega el campo al documento
		Document update = new Document("$set", newField);
		// Actualizo el primer documento que coincide con los criterios de búsqueda
		UpdateResult result = collection.updateOne(filter, update);
		return (int) result.getModifiedCount();
	}

	public int tarea3c() {
		// Crea un filtro para buscar los documentos que tengan la clave "nombre"
		Bson filter = Filters.exists("nombre");
		// Crea un objeto de actualización para renombrar la clave "nombre" a "titulo"
		Bson update = Updates.rename("nombre", "titulo");
		// Actualiza los documentos que cumplan el filtro.
		UpdateResult result = collection.updateMany(filter, update);
		return (int) result.getModifiedCount();
	}

	public int tarea3d() {
		// Crea un filtro para buscar todos los documentos de la colección
		Bson filter = Filters.exists("saga", false);
		// Crear un objeto de actualización para agregar la clave "saga" con valor "Harry Potter"
		Bson update = new Document("$set", new Document("saga", "Harry Potter"));
		// Actualizar todos los documentos de la colección con el filtro y la actualización
		UpdateResult result = collection.updateMany(filter, update);
		return (int) result.getModifiedCount();
	}

	// Patrón Singleton.
	private static Ejercicio3 obj = null;

	public static Ejercicio3 getInstance() {
		if (obj == null) {
			obj = new Ejercicio3();
		}
		return obj;
	}
}
