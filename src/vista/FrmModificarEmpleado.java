package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import recursos.ControladorEmpleado;
import recursos.Empleado;
import sistema.BaseDatos;
import sistema.Util;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmModificarEmpleado extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtLegajo;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private ControladorEmpleado ctrlEmpleado;
	private EmpleadoModificadoListener listener;
	private FrmMensaje frmMsg;

	/**
	 * Create the frame.
	 */
	public FrmModificarEmpleado(BaseDatos db, Empleado objEmpleado, EmpleadoModificadoListener pListener) {
		super();
		listener = pListener;
		ctrlEmpleado = new ControladorEmpleado(db);
		setTitle("Modificar Empleado");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 238);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtId = new JTextField();
		txtId.setText(String.valueOf(objEmpleado.getIdEmpleado()));
		txtId.setEditable(false);
		txtId.setBounds(95, 8, 86, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(10, 11, 46, 14);
		contentPane.add(lblId);
		
		JLabel lblLegajo = new JLabel("Legajo");
		lblLegajo.setBounds(10, 46, 46, 14);
		contentPane.add(lblLegajo);
		
		JLabel lblNombre = new JLabel("Nombre");
		
		lblNombre.setBounds(10, 81, 46, 14);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(10, 118, 46, 14);
		contentPane.add(lblApellido);
		
		txtLegajo = new JTextField();
		txtLegajo.setText(String.valueOf(objEmpleado.getLegajo()));
		txtLegajo.setBounds(95, 43, 86, 20);
		contentPane.add(txtLegajo);
		txtLegajo.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setText(objEmpleado.getNombre());
		txtNombre.setBounds(95, 78, 329, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setText(objEmpleado.getApellido());
		txtApellido.setBounds(95, 115, 329, 20);
		contentPane.add(txtApellido);
		txtApellido.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 156, 414, 2);
		contentPane.add(separator);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(335, 166, 89, 23);
		contentPane.add(btnCancelar);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validarInformacionVentana()) {
					if(listener != null) {
						listener.onEmpleadoModificado(
								objEmpleado.getIdEmpleado(), 
								Integer.parseInt(txtLegajo.getText()), 
								txtNombre.getText(), 
								txtApellido.getText());
					}
					dispose();
				} else {
					frmMsg.setAlwaysOnTop(true);
					frmMsg.setVisible(true);
				}
			}
		});
		btnAceptar.setBounds(236, 166, 89, 23);
		contentPane.add(btnAceptar);
		this.setLocationRelativeTo(null); 
	}
	
	
	private boolean validarInformacionVentana() {

		boolean valido = true;
		String textoMensaje = "";
		if(txtLegajo.getText().length() == 0) {
			textoMensaje = "Debe proporcionar el legajo";
			valido = false;
		} else if (txtNombre.getText().length() == 0) {
			textoMensaje = "Debe proporcionar un nombre";
			valido = false;
		} else if (txtApellido.getText().length() == 0) {
			textoMensaje = "Debe proporcionar un apellido";
			valido = false;
		} else if (!Util.esEntero(txtLegajo.getText())) {
			textoMensaje = "El legajo debe ser un número";
			valido = false;
		} else if(!ctrlEmpleado.getTblEmpleado().legajoUnico(Integer.parseInt(txtId.getText()), Integer.parseInt(txtLegajo.getText()))) {
			textoMensaje = "El legajo ya está utilizado para otro empleado";
			valido = false;
		}

		frmMsg = new FrmMensaje(textoMensaje);
				
		return valido;
	}

}
