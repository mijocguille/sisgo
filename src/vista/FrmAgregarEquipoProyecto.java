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

import operaciones.ControladorEquipoProyecto;
import sistema.BaseDatos;

public class FrmAgregarEquipoProyecto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEquipo;
	private JTextField txtCantidad;
	private ControladorEquipoProyecto ctrlEquipoProyecto;


	/**
	 * Create the frame.
	 */
	public FrmAgregarEquipoProyecto(BaseDatos db) {
		super();
		ctrlEquipoProyecto = new ControladorEquipoProyecto(db);
		setTitle("Agregar Equipo a Proyecto NN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 512, 140);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEquipo = new JLabel("Equipo");
		lblEquipo.setBounds(10, 11, 46, 14);
		contentPane.add(lblEquipo);
		
		JLabel lblCantidad = new JLabel("Cantidad a Agregar");
		lblCantidad.setBounds(10, 36, 109, 14);
		contentPane.add(lblCantidad);
		
		txtEquipo = new JTextField();
		txtEquipo.setEditable(false);
		txtEquipo.setBounds(66, 8, 309, 20);
		contentPane.add(txtEquipo);
		txtEquipo.setColumns(10);
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(289, 33, 86, 20);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.setBounds(385, 7, 102, 23);
		contentPane.add(btnSeleccionar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 61, 487, 7);
		contentPane.add(separator);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(398, 71, 89, 23);
		contentPane.add(btnCancelar);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(302, 71, 89, 23);
		contentPane.add(btnAceptar);

	}

}
