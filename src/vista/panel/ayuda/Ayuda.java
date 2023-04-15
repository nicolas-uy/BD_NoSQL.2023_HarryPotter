package vista.panel.ayuda;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Ayuda extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	public Ayuda() {
		setModal(true);
		setTitle("Acerca de nosotros...");
		setBounds(100, 100, 905, 344);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		this.setResizable(false);

		JLabel lblCorbo = new JLabel("- Germán Corbo");
		lblCorbo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCorbo.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblCorbo.setBounds(139, 178, 300, 40);
		contentPanel.add(lblCorbo);

		JLabel lblZunini = new JLabel("- Nicolás Zunini");
		lblZunini.setHorizontalAlignment(SwingConstants.CENTER);
		lblZunini.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblZunini.setBounds(449, 178, 300, 40);
		contentPanel.add(lblZunini);

		JLabel lblMateria = new JLabel("Base de Datos NoSQL");
		lblMateria.setHorizontalAlignment(SwingConstants.CENTER);
		lblMateria.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblMateria.setBounds(10, 10, 881, 74);
		contentPanel.add(lblMateria);

		JLabel lblDocente = new JLabel("Docente: Lic. Gloria Oholeguy");
		lblDocente.setHorizontalAlignment(SwingConstants.CENTER);
		lblDocente.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDocente.setBounds(10, 81, 871, 40);
		contentPanel.add(lblDocente);

		JButton btnAtras = new JButton("Atr\u00E1s");
		btnAtras.setIcon(new ImageIcon(Ayuda.class.getResource("/vista/iconos/Atras16x16.png")));
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnAtras.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAtras.setBounds(10, 252, 871, 33);
		contentPanel.add(btnAtras);
		
		JLabel lblEstudiantes = new JLabel("Estudiantes:");
		lblEstudiantes.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstudiantes.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEstudiantes.setBounds(10, 131, 871, 40);
		contentPanel.add(lblEstudiantes);
		setLocationRelativeTo(null);
		setVisible(true);

	}
}
