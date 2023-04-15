package controlador;

import java.util.ArrayList;
import java.util.Arrays;

import org.bson.Document;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriterSettings;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class Ejercicio2 {

	MongoCollection<Document> collection;
	MongoCursor<Document> cursor;

	private Ejercicio2() {
		// Obtiene la coleccion "Peliculas" de la BD.
		collection = Conexion.getInstance().coleccionesDePeliculas();
	}

	public ArrayList<String> tarea2a() {
		// Obtener todos los documentos.
		ArrayList<String> col = new ArrayList<String>();
		cursor = collection.find().iterator();
		while (cursor.hasNext()) {
			Document doc = cursor.next();
			String jsonObj = doc.toJson(settings());
			col.add(jsonObj);
		}
		return col;
	}

	public ArrayList<String> tarea2b() {
		// Coleccion donde guardará los resultados encontrados.
		ArrayList<String> col = new ArrayList<String>();
		// Creo un objeto Document con los criterios de búsqueda.
		Document query = new Document("actores", new Document("$in", Arrays.asList("Emma Watson", "Daniel Radcliffe")));
		// Obtengo un cursor para los documentos que coinciden con los criterios de búsqueda.
		MongoCursor<Document> cursor = collection.find(query).iterator();
		// Formateo y agrego a la coleccion que devolveré para listar.
		while (cursor.hasNext()) {
			Document doc = cursor.next();
			String jsonObj = doc.toJson(settings());
			col.add(jsonObj);
		}
		return col;
	}

	public ArrayList<String> tarea2c() {
		// Coleccion donde guardará los resultados encontrados.
		ArrayList<String> col = new ArrayList<String>();
		// Creo un objeto Document con los criterios de búsqueda.
		Document query = new Document("basadaen", new Document("$regex", "Stan")).append("anio", 2019);
		// Obtengo un cursor para los documentos que coinciden con los criterios de búsqueda.
		MongoCursor<Document> cursor = collection.find(query).iterator();
		// Formateo y agrego a la coleccion que devolveré para listar.
		while (cursor.hasNext()) {
			Document doc = cursor.next();
			String jsonObj = doc.toJson(settings());
			col.add(jsonObj);
		}
		return col;
	}

	public ArrayList<String> tarea2d() {
		// Coleccion donde guardará los resultados encontrados.
		ArrayList<String> col = new ArrayList<String>();
		// Creo un objeto Document con los criterios de búsqueda
		Document query = new Document("anio", new Document("$lte", 2013));
		// Obtengo un cursor para los documentos que coinciden con los criterios de búsqueda.
		MongoCursor<Document> cursor = collection.find(query).iterator();
		// Formateo y agrego a la coleccion que devolveré para listar.
		while (cursor.hasNext()) {
			Document doc = cursor.next();
			String jsonObj = doc.toJson(settings());
			col.add(jsonObj);
		}
		return col;
	}

	public ArrayList<String> tarea2e() {
		// Coleccion donde guardará los resultados encontrados.
		ArrayList<String> col = new ArrayList<String>();
		// Crea un objeto Document con los criterios de búsqueda
		Document query = new Document("sinopsis", new Document("$regex", "hogwarts").append("$options", "i"));
		/*
		 * Aquí se utiliza el operador $regex para especificar la expresión regular que busca la palabra "hogwarts" en la clave "sinopsis", y $options con el valor "i" para indicar que la búsqueda debe ser
		 * insensible a mayúsculas y minúsculas. El objeto query se pasa como argumento al método find() de la colección para obtener los documentos que coinciden con los criterios de búsqueda.
		 */
		// Obtengo un cursor para los documentos que coinciden con los criterios de búsqueda.
		MongoCursor<Document> cursor = collection.find(query).iterator();
		// Formateo y agrego a la coleccion que devolveré para listar.
		while (cursor.hasNext()) {
			Document doc = cursor.next();
			String jsonObj = doc.toJson(settings());
			col.add(jsonObj);
		}
		return col;
	}

	private JsonWriterSettings settings() {
		// Función que configura el String del JSON para mostrarlo por consola.
		return JsonWriterSettings.builder().outputMode(JsonMode.SHELL).build();
	}

	// Patrón Singleton.
	private static Ejercicio2 obj = null;

	public static Ejercicio2 getInstance() {
		if (obj == null) {
			obj = new Ejercicio2();
		}
		return obj;
	}
}
