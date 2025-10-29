package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import entidades.ControladorRol;
import entidades.Rol;
import sistema.BaseDatos;
import javax.swing.DefaultComboBoxModel;

public class FrmNuevoUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JTextField txtDescripcionUsuario;
	private JSeparator separator;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JPasswordField txtContrasenia;
	private JPasswordField txtConfirmarContrasenia;
	private JComboBox<Rol> cboRoles;
	private UsuarioNuevoListener listener;
	private ControladorRol ctrlRol;
	private FrmMensaje frmMsg;


	/**
	 * Create the frame.
	 */
	public FrmNuevoUsuario(BaseDatos db, UsuarioNuevoListener pListener) {
		super();
		listener = pListener;
		ctrlRol = new ControladorRol(db);
		setTitle("Nuevo Usuario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 242);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(10, 11, 78, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblDescripcion = new JLabel("Descripción");
		lblDescripcion.setBounds(10, 41, 78, 14);
		contentPane.add(lblDescripcion);
		
		JLabel lblContrasenia = new JLabel("Contraseña");
		lblContrasenia.setBounds(10, 71, 78, 14);
		contentPane.add(lblContrasenia);
		
		JLabel lblConfirmarContrasenia = new JLabel("Confirmar");
		lblConfirmarContrasenia.setBounds(10, 101, 78, 14);
		contentPane.add(lblConfirmarContrasenia);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(98, 11, 326, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtDescripcionUsuario = new JTextField();
		txtDescripcionUsuario.setBounds(98, 38, 326, 20);
		contentPane.add(txtDescripcionUsuario);
		txtDescripcionUsuario.setColumns(10);
		
		separator = new JSeparator();
		separator.setBounds(10, 158, 414, 2);
		contentPane.add(separator);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(335, 171, 89, 23);
		contentPane.add(btnCancelar);
		
		txtContrasenia = new JPasswordField();
		txtContrasenia.setBounds(98, 68, 326, 20);
		contentPane.add(txtContrasenia);
		
		txtConfirmarContrasenia = new JPasswordField();
		txtConfirmarContrasenia.setBounds(98, 98, 326, 20);
		contentPane.add(txtConfirmarContrasenia);
		
		JLabel lblRol = new JLabel("Rol");
		lblRol.setBounds(10, 133, 46, 14);
		contentPane.add(lblRol);
		
		cboRoles = new JComboBox<Rol>();
		cargarCombo();
		cboRoles.setBounds(98, 129, 217, 22);
		contentPane.add(cboRoles);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validarInformacionVentana()) {
					if(listener != null) {
						int idRol = cboRoles.getSelectedIndex();
						listener.onUsuarioCreado(txtUsuario.getText(), txtDescripcionUsuario.getText(), txtContrasenia.getPassword().toString(), idRol);
					}
					dispose();
				} else {
					frmMsg.setAlwaysOnTop(true);
					frmMsg.setVisible(true);
				}
			}
		});
		btnAceptar.setBounds(240, 171, 89, 23);
		contentPane.add(btnAceptar);
		
	}
	
	private void cargarCombo() {
		cboRoles.removeAll();
		cboRoles.setModel(ctrlRol.cargarComboRoles());
	}
	
	private boolean validarInformacionVentana() {

		boolean valido = true;
		String textoMensaje = "";
		if(txtUsuario.getText().length() == 0) {
			textoMensaje = "Debe proporcionar un nombre de usuario";
			valido = false;
		} else if (txtDescripcionUsuario.getText().length() == 0) {
			textoMensaje = "Debe proporcionar una descripción para el usuario";
			valido = false;
		} else if(txtContrasenia.getPassword().toString() != txtConfirmarContrasenia.getPassword().toString()) {
			textoMensaje = "Las contraseñas no coinciden";
			valido = false;
		} else if (cboRoles.getSelectedItem() == null) {
			textoMensaje = "Debe seleccionar un rol para el usuario";
			valido = false;
		}

		frmMsg = new FrmMensaje(textoMensaje);
				
		return valido;
	}

}
