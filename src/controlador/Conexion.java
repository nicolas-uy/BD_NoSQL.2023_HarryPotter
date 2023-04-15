package controlador;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Conexion {

	private MongoClient mongoClient = null;

	private Conexion() {
		// Constructor privado de la clase.
		mongoClient = new MongoClient("localhost", 27017);
	}

	public MongoDatabase dbTarea() {
		return mongoClient.getDatabase("TareaSemana3");
	}

	public void borrarBD(String confirmar) {
		if (confirmar.equalsIgnoreCase("OK")) {
			dbTarea().getCollection("Peliculas").drop();
			Ejercicio1.getInstance().crearDocumentos();
		}
	}

	public MongoCollection<Document> coleccionesDePeliculas() {
		return dbTarea().getCollection("Peliculas");
	}

	public boolean existeBdTarea() {
		// Verifica si la base de datos: "TareaSemana3" existe.
		if (mongoClient.listDatabaseNames().into(new ArrayList<String>()).contains("TareaSemana3")) {
			return true;
		}
		return false;
	}

	// Patrón Singleton.
	private static Conexion obj = null;

	public static Conexion getInstance() {
		if (obj == null) {
			obj = new Conexion();
		}
		return obj;
	}

}

/*
 * El driver se puede descargar de aquí: https://mvnrepository.com/artifact/org.mongodb/mongo-java-driver/3.12.12
 *
 */