package main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.mongodb.MongoException;

import controlador.Conexion;
import controlador.Ejercicio1;
import vista.FrmMenu;
import vista.util.Mensaje;

public class Main {

	public static void main(String[] args) {
		if (!Conexion.getInstance().existeBdTarea()) {
			// Consulto si existe la Base de Datos, si no existe se crea.
			crearDatabase();
		}
		// Ejercicio3.getInstance().tarea3d();
		cargarInterfaz();
		FrmMenu.getInstance();
	}

	private static void crearDatabase() {
		try {
			Ejercicio1.getInstance().crearDocumentos();
		} catch (MongoException e) {
			Mensaje.error(null, e.getMessage(), "Error al Crear:");
		}
	}

	private static void cargarInterfaz() {
		// Carga el Look and Feel.
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

}
