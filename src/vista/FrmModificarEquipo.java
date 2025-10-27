package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class FrmModificarEquipo extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtIdEquipo;
	private JTextField txtDescripcion;
	private JTextField txtCantidad;


	/**
	 * Create the frame.
	 */
	public FrmModificarEquipo() {
		setTitle("Modificar Equipo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 168);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdEquipo = new JLabel("Id");
		lblIdEquipo.setBounds(10, 11, 70, 14);
		contentPane.add(lblIdEquipo);
		
		JLabel lblDescrpcion = new JLabel("Descripción");
		lblDescrpcion.setBounds(10, 36, 70, 14);
		contentPane.add(lblDescrpcion);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(10, 61, 70, 14);
		contentPane.add(lblCantidad);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(335, 99, 89, 23);
		contentPane.add(btnCancelar);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(236, 99, 89, 23);
		contentPane.add(btnAceptar);
		
		txtIdEquipo = new JTextField();
		txtIdEquipo.setEditable(false);
		txtIdEquipo.setBounds(90, 8, 86, 20);
		contentPane.add(txtIdEquipo);
		txtIdEquipo.setColumns(10);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(90, 33, 334, 20);
		contentPane.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(90, 58, 86, 20);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 86, 414, 2);
		contentPane.add(separator);

	}
}
