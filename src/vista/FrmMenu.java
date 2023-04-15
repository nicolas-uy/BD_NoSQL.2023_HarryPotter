package vista;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import controlador.Conexion;
import controlador.Ejercicio2;
import controlador.Ejercicio3;
import controlador.Ejercicio4;
import vista.panel.ayuda.Ayuda;
import vista.util.Mensaje;

public class FrmMenu extends JFrame {

	private static final long serialVersionUID = 1L;

	private static FrmMenu objVentana = null;

	public static FrmMenu getInstance() {
		if (objVentana == null) {
			objVentana = new FrmMenu();
		}
		return objVentana;
	}

	private JPanel contentPane;
	private JTextArea txtMostrar;
	private JLabel lblTotal;
	private JTextPane txtConsulta;
	private JComboBox<String> cbox;
	private JScrollPane scrollPane;

	private FrmMenu() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmMenu.class.getResource("/vista/iconos/LosTroyanos16x16.png")));
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				cerrarFormulario();
			}
		});
		// Cambio el cierre del JFrame para que no haga nada
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		// Seteo del tamaño por defecto.
		setBounds(100, 100, 1186, 845);
		setTitle("Base de Datos NoSQL - Semana 3");

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menuArchivo = new JMenu("Archivo");
		menuArchivo.setIcon(new ImageIcon(FrmMenu.class.getResource("/vista/iconos/Archivo16x16.png")));
		menuArchivo.setFont(new Font("Segoe UI", Font.BOLD, 15));
		menuBar.add(menuArchivo);

		JMenuItem itemSalir = new JMenuItem("Salir");
		itemSalir.setIcon(new ImageIcon(FrmMenu.class.getResource("/vista/iconos/Salir.png")));
		itemSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarFormulario();
			}
		});
		itemSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
		itemSalir.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuArchivo.add(itemSalir);

		JMenu menuAyuda = new JMenu("Ayuda");
		menuAyuda.setIcon(new ImageIcon(FrmMenu.class.getResource("/vista/iconos/ayuda16x16.png")));
		menuAyuda.setFont(new Font("Segoe UI", Font.BOLD, 15));
		menuBar.add(menuAyuda);

		JMenuItem itemAcercaDe = new JMenuItem("Acerca de");
		itemAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Ayuda();
			}
		});
		itemAcercaDe.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuAyuda.add(itemAcercaDe);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		cbox = new JComboBox<String>();
		cbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbox.getSelectedIndex() != -1) {
					txtMostrar.setText("");
					txtConsulta.setText("");
					lblTotal.setText("");
					String metodo = null;
					String query = null;
					if (cbox.getSelectedIndex() == 0) {
						listarResultados(Ejercicio2.getInstance().tarea2a());
						metodo = "Conexion.getInstance().dbTarea().Peliculas";
						query = "db.Peliculas.find()";
						txtConsulta.setText(consulta(metodo, query));
					} else if (cbox.getSelectedIndex() == 1) {
						listarResultados(Ejercicio2.getInstance().tarea2b());
						metodo = "Document(\"actores\", new Document(\"$in\", Arrays.asList(\"Emma Watson\", \"Daniel Radcliffe\")))";
						query = "db.Peliculas.find({actores:{$in:[\"Emma Watson\",\"Daniel Radcliffe\"]}})";
						txtConsulta.setText(consulta(metodo, query));
					} else if (cbox.getSelectedIndex() == 2) {
						listarResultados(Ejercicio2.getInstance().tarea2c());
						metodo = "Document(\"basadaen\", new Document(\"$regex\", \"Stan\")).append(\"anio\", 2019)";
						query = "db.Peliculas.find({basadaen: {$regex: /Stan/},anio:2019})";
						txtConsulta.setText(consulta(metodo, query));
					} else if (cbox.getSelectedIndex() == 3) {
						listarResultados(Ejercicio2.getInstance().tarea2d());
						metodo = "Document(\"anio\", new Document(\"$lte\", 2013))";
						query = "db.Peliculas.find({anio:{$lte:2013}})";
						txtConsulta.setText(consulta(metodo, query));
					} else if (cbox.getSelectedIndex() == 4) {
						listarResultados(Ejercicio2.getInstance().tarea2e());
						metodo = "Document(\"sinopsis\", new Document(\"$regex\", \"hogwarts\").append(\"$options\", \"i\"))";
						query = "db.Peliculas.find({sinopsis: {$regex: /Hogwarts/i}});";
						txtConsulta.setText(consulta(metodo, query));
					} else if (cbox.getSelectedIndex() == 5) {
						int cant = Ejercicio3.getInstance().tarea3a();
						if (cant > 0) {
							txtMostrar.setText("Actualización exitosa de documento/s con clave:\"anio\" y valor:\"2003\" a valor:\"2002\".\n\n" + "Se ha actualizado: " + cant + " Documento/s.");
						} else {
							txtMostrar.setText("No se ha encontrados documentos con esa clave y valor.");
						}
						metodo = "Document(\"anio\", 2003);Document(\"$set\", new Document(\"anio\", 2002));collection.updateMany(filter, update)";
						query = "db.Peliculas.updateMany({anio:2003},{$set:{anio:2002}})";
						txtConsulta.setText(consulta(metodo, query));
					} else if (cbox.getSelectedIndex() == 6) {
						int cant = Ejercicio3.getInstance().tarea3b();
						if (cant > 0) {
							txtMostrar.setText(
									"Actualización exitosa de documento con clave:\"anio\" y valor:\"2004\" se agrego la clave:\"basadaen\" y valor:\"Harry Potter y el prisionero de Azkaban, de J. K. Rowling.\"\n\n"
											+ "Se ha actualizado: " + cant + " Documento/s.");
						} else {
							txtMostrar.setText("No se ha encontrados documentos con esa clave y valor.");
						}
						metodo = "Document(\"anio\", 2004);Document(\"basadaen\", \"Harry Potter y el prisionero de Azkaban, de J. K. Rowling.\");Document(\"$set\", newField)\n;collection.updateOne(filter, update)";
						query = "db.Peliculas.updateOne({anio:2004},{$set:{basadaen:\"Harry Potter y el prisionero de Azkaban, de J. K. Rowling.\"}})";
						txtConsulta.setText(consulta(metodo, query));
					} else if (cbox.getSelectedIndex() == 7) {
						int cant = Ejercicio3.getInstance().tarea3c();
						if (cant > 0) {
							txtMostrar.setText("Actualización exitosa se cambio de nombre la clave:\"nombre\" a clave:\"titulo\"\n\n" + "Se ha actualizado: " + cant + " Documento/s.");
						} else {
							txtMostrar.setText("No se ha encontrados documentos con esa clave y valor.");
						}
						metodo = "Filters.exists(\"nombre\");Updates.rename(\"nombre\", \"titulo\");collection.updateMany(filter, update);";
						query = "db.Peliculas.updateMany({nombre:{$exists:true}},{$rename:{\"nombre\":\"titulo\"}})";
						txtConsulta.setText(consulta(metodo, query));
					} else if (cbox.getSelectedIndex() == 8) {
						int cant = Ejercicio3.getInstance().tarea3d();
						if (cant > 0) {
							txtMostrar.setText("Actualización exitosa se agrego la clave:\"saga\" con valor:\"Harry Potter\".\nEn los documentos que no tienen la clave:\"saga\".\n\n" + "Se ha actualizado: " + cant
									+ " Documento/s.");
						} else {
							txtMostrar.setText("No se ha encontrados documentos con esa clave y valor.");
						}
						metodo = "Filters.exists(\"saga\",false);Document(\"$set\", new Document(\"saga\", \"Harry Potter\"));collection.updateMany(filter, update);";
						query = "db.Peliculas.updateMany({saga:{$exists:false}},{$set:{saga:\"Harry Potter\"}})";
						txtConsulta.setText(consulta(metodo, query));
					} else if (cbox.getSelectedIndex() == 9) {
						int cant = Ejercicio4.getInstance().tarea4a();
						if (cant > 0) {
							txtMostrar.setText("Eliminación exitosa de documento con clave:\"titulo\" y valor:\"Harry Potter y la Orden del Fénix\".\n\n" + "Se ha eliminado: " + cant + " Documento/s.");
						} else {
							txtMostrar.setText("No se ha encontrados documentos con esa clave y valor.");
						}
						metodo = "Document(\"titulo\", \"Harry Potter y la Orden del Fénix\");collection.deleteOne(query)";
						query = "db.Peliculas.deleteOne({titulo:\"Harry Potter y la Orden del Fénix\"})";
						txtConsulta.setText(consulta(metodo, query));
					} else if (cbox.getSelectedIndex() == 10) {
						int cant = Ejercicio4.getInstance().tarea4b();
						if (cant > 0) {
							txtMostrar.setText("Eliminación exitosa de documento/s con clave:\"basadaen\" y valor:\"cáliz de fuego\".\n\n" + "Se ha eliminado: " + cant + " Documento/s.");
						} else {
							txtMostrar.setText("No se ha encontrados documentos con esa clave y valor.");
						}
						metodo = "Document(\"basadaen\", new Document(\"$regex\", \"cáliz de fuego\"));;collection.deleteOne(query);";
						query = "db.Peliculas.deleteOne({basadaen:{$regex:/cáliz de fuego/}})";
						txtConsulta.setText(consulta(metodo, query));
					}
					// Vuelve la barra a la posición de inicio.
					txtMostrar.select(0, 0);
				}
			}
		});
		cbox.setToolTipText("Listado de tareas disponibles.");
		cbox.setModel(opcionesComboBox());
		cbox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbox.setBounds(10, 51, 896, 30);
		contentPane.add(cbox);

		JLabel lblTitulo = new JLabel("Seleccione la Tarea:");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitulo.setBounds(10, 10, 1151, 31);
		contentPane.add(lblTitulo);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 91, 1151, 532);
		contentPane.add(scrollPane);

		txtMostrar = new JTextArea();
		scrollPane.setViewportView(txtMostrar);
		txtMostrar.setFont(new Font("Consolas", Font.PLAIN, 20));

		JButton btnSalir = new JButton("Salir");
		btnSalir.setIcon(new ImageIcon(FrmMenu.class.getResource("/vista/iconos/Salir.png")));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarFormulario();
			}
		});
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSalir.setBounds(10, 737, 1151, 30);
		contentPane.add(btnSalir);

		txtConsulta = new JTextPane();
		// txtConsulta.setContentType("text/html");
		txtConsulta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtConsulta.setBounds(10, 634, 1151, 66);
		txtConsulta.setEditable(false);
		txtConsulta.setBackground(null);
		txtConsulta.setBorder(null);
		contentPane.add(txtConsulta);

		lblTotal = new JLabel("");
		lblTotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTotal.setBounds(10, 696, 845, 31);
		contentPane.add(lblTotal);
		cbox.setSelectedIndex(-1);

		JButton btnReset = new JButton("Reset de Base de Datos");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetBD();
			}
		});
		btnReset.setIcon(new ImageIcon(FrmMenu.class.getResource("/vista/iconos/trash-icon-16x16.png")));
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnReset.setToolTipText("Reinicia todos los datos.");
		btnReset.setBounds(916, 51, 245, 30);
		contentPane.add(btnReset);
		setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void cerrarFormulario() {
		// Cierra el formulario mostrando un JOptionPane al usuario con opciones Aceptar y Cancelar.
		if (JOptionPane.OK_OPTION == Mensaje.salir(this)) {
			System.exit(0);
		}
	}

	private String prettyJson(String str) {
		// Esta librerìa permite formatear el String y convertir a JSON para visualizar el String a JSON.
		Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
		String temp = gson.toJson(JsonParser.parseString(str)).replaceAll("\\\\", "");
		return temp;
	}

	private void resetBD() {
		// Realiza el reset de la BD desde el botón.
		int numero = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea hacer el reset de los datos?", "Seleccione opción:", JOptionPane.OK_OPTION, JOptionPane.OK_OPTION);
		if (JOptionPane.OK_OPTION == numero) {
			Conexion.getInstance().borrarBD("OK");
			txtMostrar.setText("");
			cbox.setSelectedIndex(-1);
			txtConsulta.setText("");
			lblTotal.setText("");
			Mensaje.informacion(this, "Se ha reiniciado de manera exitosa.", "BASE DE DATOS:");
		}
	}

	private ComboBoxModel<String> opcionesComboBox() {
		// Crea la lista con las opciones del ejericio.
		ComboBoxModel<String> cboxModel = new DefaultComboBoxModel<String>(
				new String[] { "2a. Obtener todos los documentos",
						"2b. Obtener documentos con actores que contenga el valor Emma Watson y Daniel Radcliffe",
						"2c. Obtener documentos cuya clave basadaen contenga la palabra Stan y el anio sea 2019.",
						"2d. Obtener documentos con anio menor igual a 2013.",
						"2e. Obtener documentos donde tenga el valor Hogwarts en sinopsis. Sin importar mayúsculas o minúsculas.",
						"3a. Para los documentos que tengan la clave anio valor 2003, modificar el valor y asignarle el valor 2002.",
						"3b. Agregarle la clave basadaen al documento de anio 2004, con el valor Harry Potter y el prisionero de Azkaban, de J. K. Rowling.",
						"3c. Asignarle el valor de la clave nombre a la nueva clave titulo.",
						"3d. Agregar la clave saga con el valor Harry Potter, según corresponda.",
						"4a. El documento cuyo titulo es Harry Potter y la Orden del Fénix",
						"4b. El documento que contenga en la clave basadaen el valor cáliz de fuego." });
		return cboxModel;
	}

	private void listarResultados(ArrayList<String> col) {
		int cont = 0;
		if (!col.isEmpty()) {
			for (String str : col) {
				if (cont < (col.size() - 1)) {
					txtMostrar.append(prettyJson(str) + ",\n");
				} else {
					txtMostrar.append(prettyJson(str));
				}
				cont++;
			}	
		} else {
			txtMostrar.append("No hay resulatados para mostrar.");
		}
		
		lblTotal.setText("Total de registros: " + col.size() + ".");
	}

	private String consulta(String metodo, String query) {
		return "Metodo usado: " + metodo + "\nQuery Realizada : " + query;
	}

}
