package vista.util;

import java.awt.Component;

import javax.swing.JOptionPane;

public class Mensaje extends JOptionPane {

	private static final long serialVersionUID = 1L;

	public static void informacion(Component padre, String textoAmostrar, String titulo) {
		JOptionPane.showMessageDialog(padre, textoAmostrar, titulo, JOptionPane.INFORMATION_MESSAGE);
	}

	public static void error(Component padre, String textoAmostrar, String titulo) {
		JOptionPane.showMessageDialog(padre, textoAmostrar, titulo, JOptionPane.ERROR_MESSAGE);
	}

	public static int salir(Component padre) {
		int numero = JOptionPane.showConfirmDialog(padre, "¿Está seguro de que desea salir?", "Seleccione opción:", JOptionPane.OK_OPTION, JOptionPane.OK_OPTION);
		return numero;
	}

	public static int modificar(Component padre, String titulo) {
		int numero = JOptionPane.showConfirmDialog(padre,"¿Está seguro de que desea modificar?", titulo, JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE);
		return numero;
	}

	public static int eliminar(Component padre, String titulo) {
		int numero = JOptionPane.showConfirmDialog(padre, "¿Está seguro de que desea eliminar?", titulo, JOptionPane.OK_OPTION, JOptionPane.OK_OPTION);
		return numero;
	}

}
