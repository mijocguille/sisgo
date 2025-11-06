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

import operaciones.ControladorEmpleadoProyecto;
import recursos.Empleado;
import sistema.BaseDatos;

public class FrmAgregarEmpleadoProyecto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmpleado;
	private EmpleadoProyectoNuevoListener listener;
	private ControladorEmpleadoProyecto ctrlControladorEmpleadoProyecto;
	private int idEmpleado;
	private FrmMensaje frmMsg;

	/**
	 * Create the frame.
	 */
	public FrmAgregarEmpleadoProyecto(int numeroProyecto, BaseDatos db, EmpleadoProyectoNuevoListener pListener) {
		super();
		listener = pListener;
		ctrlControladorEmpleadoProyecto = new ControladorEmpleadoProyecto(db);
		setTitle("Agregar Empleado a Proyecto " + String.valueOf(numeroProyecto));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 512, 114);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEmpleado = new JLabel("Empleado");
		lblEmpleado.setBounds(10, 11, 46, 14);
		contentPane.add(lblEmpleado);
		
		txtEmpleado = new JTextField();
		txtEmpleado.setEditable(false);
		txtEmpleado.setBounds(66, 8, 309, 20);
		contentPane.add(txtEmpleado);
		txtEmpleado.setColumns(10);
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmEmpleados frmSeleccion = new FrmEmpleados(db,true, new SeleccionListener() {
					
					@Override
					public void onSeleccion(int id) {
						Empleado e = ctrlControladorEmpleadoProyecto.getTblEmpleado().obtenerEmpleado(id);
						txtEmpleado.setText(e.getNombreCompleto());
						idEmpleado = id;
					}
				});				
				frmSeleccion.setAlwaysOnTop(true);
				frmSeleccion.setVisible(true);
			}
		});
		btnSeleccionar.setBounds(385, 7, 102, 23);
		contentPane.add(btnSeleccionar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 36, 487, 7);
		contentPane.add(separator);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(408, 46, 89, 23);
		contentPane.add(btnCancelar);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validarInformacionVentana()) {
					if(listener != null) {
						listener.onEmpleadoACrear(idEmpleado);
						dispose();
					}
				} else {
					frmMsg.setAlwaysOnTop(true);
					frmMsg.setVisible(true);
				}
			}
		});
		btnAceptar.setBounds(312, 46, 89, 23);
		contentPane.add(btnAceptar);
		this.setLocationRelativeTo(null); 

	}

	private boolean validarInformacionVentana() {

		boolean valido = true;
		String textoMensaje = "";
		if (txtEmpleado.getText().length() == 0) {
			textoMensaje = "Debe seleccionar un empleado para agregar al proyecto";
			valido = false;
		}

		frmMsg = new FrmMensaje(textoMensaje);
				
		return valido;
	}
}
