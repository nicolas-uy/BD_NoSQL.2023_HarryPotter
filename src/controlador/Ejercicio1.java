package controlador;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import modelo.Pelicula;

public class Ejercicio1 {

	MongoCollection<Document> collection;

	private Ejercicio1() {
		// Obtiene la coleccion "Peliculas" de la BD.
		collection = Conexion.getInstance().coleccionesDePeliculas();
	}

	public void crearDocumentos() {
		// Creamos la Pelicula 1.
		Pelicula p1 = new Pelicula();
		p1.setTitulo("Harry Potter y la piedra filosofal");
		p1.setBasadaen("Harry Potter y la piedra filosofal de J. K. Rowling");
		p1.agregarActor("Daniel Radcliffe");
		p1.agregarActor("Rupert Grint");
		p1.agregarActor("Emma Watson");
		p1.agregarActor("Robbie Coltrane");
		p1.agregarActor("Richard Harris");
		p1.agregarActor("Alan Rickman");
		p1.agregarActor("Maggie Smith");
		p1.setAnio(2001);
		p1.setSinopsis("Harry Potter se ha quedado huérfano y vive en casa de sus abominables tíos y el insoportable primo Dudley. Harry se siente muy triste y solo, hasta "
				+ " que un buen día recibe una carta que cambiará su vida para siempre. En ella le comunican que ha sido aceptado como alumno en el Colegio Hogwarts de Magia.");
		// Creamos el documento del objeto.
		Document doc1 = new Document("titulo", p1.getTitulo())
				.append("basadaen", p1.getBasadaen())
				.append("actores", p1.getActores())
				.append("anio", p1.getAnio())
				.append("sinopsis", p1.getSinopsis());
		agregarEnBaseDatos(doc1);

		// Creamos la Pelicula 2.
		Pelicula p2 = new Pelicula();
		p2.setNombre("Harry Potter y la cámara secreta");
		p2.agregarActor("Daniel Radcliffe");
		p2.agregarActor("Rupert Grint");
		p2.agregarActor("Emma Watson");
		p2.agregarActor("Toby Jones");
		p2.agregarActor("Kenneth Branagh");
		p2.setAnio(2003);
		// Creamos el documento del objeto.
		Document doc2 = new Document("nombre", p2.getNombre())
				.append("actores", p2.getActores())
				.append("anio", p2.getAnio());
		agregarEnBaseDatos(doc2);

		// Creamos la Pelicula 3.
		Pelicula p3 = new Pelicula();
		p3.setNombre("Harry Potter and the Prisoner of Azkaban");
		p3.setAnio(2004);
		p3.agregarActor("Daniel Radcliffe");
		p3.agregarActor("Rupert Grint");
		p3.agregarActor("Emma Watson");
		p3.agregarActor("Gary Oldman");
		p3.agregarActor("David Thewlis");
		p3.agregarActor("Emma Thompson");
		// Creamos el documento del objeto.
		Document doc3 = new Document("nombre", p3.getNombre())
				.append("anio", p3.getAnio())
				.append("actores", p3.getActores());
		agregarEnBaseDatos(doc3);

		// Creamos la Pelicula 4.
		Pelicula p4 = new Pelicula();
		p4.setTitulo("Harry Potter y el cáliz de fuego");
		p4.setAnio(2005);
		p4.setBasadaen("Harry Potter y el cáliz de fuego, de J. K. Rowling");
		// Creamos el documento del objeto.
		Document doc4 = new Document("titulo", p4.getTitulo())
				.append("anio", p4.getAnio())
				.append("basadaen", p4.getBasadaen());
		agregarEnBaseDatos(doc4);

		// Creamos la Pelicula 5.
		Pelicula p5 = new Pelicula();
		p5.setTitulo("Harry Potter y la Orden del Fénix");
		p5.setAnio(2007);
		// Creamos el documento del objeto.
		Document doc5 = new Document("titulo", p5.getTitulo())
				.append("anio", p5.getAnio());
		agregarEnBaseDatos(doc5);
	}

	private void agregarEnBaseDatos(Document doc) {
		// Agrega el documento a la base de datos.
		collection.insertOne(doc);
	}

	// Patrón Singleton.
	private static Ejercicio1 obj = null;

	public static Ejercicio1 getInstance() {
		if (obj == null) {
			obj = new Ejercicio1();
		}
		return obj;
	}

}
