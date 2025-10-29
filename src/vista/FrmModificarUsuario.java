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

import entidades.ControladorRol;
import entidades.Rol;
import entidades.Usuario;
import sistema.BaseDatos;

import javax.swing.JPasswordField;
import javax.swing.JComboBox;

public class FrmModificarUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JTextField txtDescripcionUsuario;
	private JSeparator separator;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JLabel lblIdUsuario;
	private JTextField txIdUsuario;
	private JPasswordField txtContrasenia;
	private JPasswordField txtConfirmarContrasenia;
	private JComboBox<Rol> cboRoles;
	private ControladorRol ctrlRol;
	private UsuarioModificadoListener listener;
	private FrmMensaje frmMsg;

	/**
	 * Create the frame.
	 */
	public FrmModificarUsuario(BaseDatos db, Usuario objUsuario, UsuarioModificadoListener pListener) {
		super();
		listener = pListener;
		ctrlRol = new ControladorRol(db);
		setTitle("Modificar Usuario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 284);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario= new JLabel("Usuario");
		lblUsuario.setBounds(10, 51, 78, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblDescripcionUsuario = new JLabel("Descripción");
		lblDescripcionUsuario.setBounds(10, 81, 78, 14);
		contentPane.add(lblDescripcionUsuario);
		
		JLabel lblContrasenia = new JLabel("Contraseña");
		lblContrasenia.setBounds(10, 111, 78, 14);
		contentPane.add(lblContrasenia);
		
		JLabel lblConfirmarContrasenia = new JLabel("Confirmar");
		lblConfirmarContrasenia.setBounds(10, 141, 78, 14);
		contentPane.add(lblConfirmarContrasenia);
		
		txtUsuario = new JTextField();
		txtUsuario.setText(objUsuario.getNombreUsuario());
		txtUsuario.setBounds(98, 51, 326, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtDescripcionUsuario = new JTextField();
		txtDescripcionUsuario.setText(objUsuario.getDescripcionUsuario());
		txtDescripcionUsuario.setBounds(98, 78, 326, 20);
		contentPane.add(txtDescripcionUsuario);
		txtDescripcionUsuario.setColumns(10);
		
		separator = new JSeparator();
		separator.setBounds(10, 198, 414, 2);
		contentPane.add(separator);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(335, 211, 89, 23);
		contentPane.add(btnCancelar);		
		
		lblIdUsuario = new JLabel("Id Usuario");
		lblIdUsuario.setBounds(10, 22, 78, 14);
		contentPane.add(lblIdUsuario);
		
		txIdUsuario = new JTextField();
		txIdUsuario.setText(String.valueOf(objUsuario.getIdUsuario()));
		txIdUsuario.setEditable(false);
		txIdUsuario.setBounds(98, 19, 86, 20);
		contentPane.add(txIdUsuario);
		txIdUsuario.setColumns(10);
		
		txtContrasenia = new JPasswordField();
		txtContrasenia.setBounds(98, 108, 326, 20);
		contentPane.add(txtContrasenia);
		
		txtConfirmarContrasenia = new JPasswordField();
		txtConfirmarContrasenia.setBounds(98, 138, 326, 20);
		contentPane.add(txtConfirmarContrasenia);
		
		JLabel lblRol = new JLabel("Rol");
		lblRol.setBounds(10, 173, 46, 14);
		contentPane.add(lblRol);
		
		cboRoles = new JComboBox<Rol>();
		//cboRoles.setModel(ctrlRol.cargarComboRoles());
		cboRoles.setBounds(98, 169, 217, 22);
		contentPane.add(cboRoles);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validarInformacionVentana()) {
					if(listener != null) {
						int idRol = cboRoles.getSelectedIndex();
						listener.onUsuarioModificado(objUsuario.getIdUsuario(),txtUsuario.getText(), txtDescripcionUsuario.getText(), txtContrasenia.getPassword().toString(), idRol);
					}
					dispose();
				} else {
					frmMsg.setAlwaysOnTop(true);
					frmMsg.setVisible(true);
				}
			}
		});
		btnAceptar.setBounds(240, 211, 89, 23);
		contentPane.add(btnAceptar);

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
		} else if(txtContrasenia.getPassword().toString() != txtConfirmarContrasenia.getPassword().toString() && txtContrasenia.getPassword().toString().length() > 0) {
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
