package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;

public class FrmModificarUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtRazonSocial;
	private JTextField txtCuit;
	private JSeparator separator;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JLabel lblIdUsuario;
	private JTextField txIdUsuario;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					/*FrmNuevoCliente frame = new FrmNuevoCliente();
					frame.setVisible(true);*/
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmModificarUsuario() {
		setTitle("Modificar Usuario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 284);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRazonSocial = new JLabel("Razón Social ");
		lblRazonSocial.setBounds(10, 51, 78, 14);
		contentPane.add(lblRazonSocial);
		
		JLabel lblCuit = new JLabel("CUIT");
		lblCuit.setBounds(10, 81, 78, 14);
		contentPane.add(lblCuit);
		
		JLabel lblContrasenia = new JLabel("Contraseña");
		lblContrasenia.setBounds(10, 111, 78, 14);
		contentPane.add(lblContrasenia);
		
		JLabel lblConfirmarContrasenia = new JLabel("Confirmar");
		lblConfirmarContrasenia.setBounds(10, 141, 78, 14);
		contentPane.add(lblConfirmarContrasenia);
		
		txtRazonSocial = new JTextField();
		txtRazonSocial.setBounds(98, 51, 326, 20);
		contentPane.add(txtRazonSocial);
		txtRazonSocial.setColumns(10);
		
		txtCuit = new JTextField();
		txtCuit.setBounds(98, 78, 326, 20);
		contentPane.add(txtCuit);
		txtCuit.setColumns(10);
		
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
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(240, 211, 89, 23);
		contentPane.add(btnAceptar);
		
		lblIdUsuario = new JLabel("Id Usuario");
		lblIdUsuario.setBounds(10, 22, 78, 14);
		contentPane.add(lblIdUsuario);
		
		txIdUsuario = new JTextField();
		txIdUsuario.setEditable(false);
		txIdUsuario.setBounds(98, 19, 86, 20);
		contentPane.add(txIdUsuario);
		txIdUsuario.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(98, 108, 326, 20);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(98, 138, 326, 20);
		contentPane.add(passwordField_1);
		
		JLabel lblRol = new JLabel("Rol");
		lblRol.setBounds(10, 173, 46, 14);
		contentPane.add(lblRol);
		
		JComboBox cboRoles = new JComboBox();
		cboRoles.setBounds(98, 169, 217, 22);
		contentPane.add(cboRoles);

	}
}
