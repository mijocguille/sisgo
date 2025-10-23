package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class FrmNuevoUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtRazonSocial;
	private JTextField txtCuit;
	private JSeparator separator;
	private JButton btnCancelar;
	private JButton btnAceptar;
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
	public FrmNuevoUsuario() {
		setTitle("Nuevo Usuario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 242);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRazonSocial = new JLabel("Razón Social ");
		lblRazonSocial.setBounds(10, 11, 78, 14);
		contentPane.add(lblRazonSocial);
		
		JLabel lblCuit = new JLabel("CUIT");
		lblCuit.setBounds(10, 41, 78, 14);
		contentPane.add(lblCuit);
		
		JLabel lblContrasenia = new JLabel("Contraseña");
		lblContrasenia.setBounds(10, 71, 78, 14);
		contentPane.add(lblContrasenia);
		
		JLabel lblConfirmarContrasenia = new JLabel("Confirmar");
		lblConfirmarContrasenia.setBounds(10, 101, 78, 14);
		contentPane.add(lblConfirmarContrasenia);
		
		txtRazonSocial = new JTextField();
		txtRazonSocial.setBounds(98, 11, 326, 20);
		contentPane.add(txtRazonSocial);
		txtRazonSocial.setColumns(10);
		
		txtCuit = new JTextField();
		txtCuit.setBounds(98, 38, 326, 20);
		contentPane.add(txtCuit);
		txtCuit.setColumns(10);
		
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
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(240, 171, 89, 23);
		contentPane.add(btnAceptar);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(98, 68, 326, 20);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(98, 98, 326, 20);
		contentPane.add(passwordField_1);
		
		JLabel lblRol = new JLabel("Rol");
		lblRol.setBounds(10, 133, 46, 14);
		contentPane.add(lblRol);
		
		JComboBox cboRoles = new JComboBox();
		cboRoles.setBounds(98, 129, 217, 22);
		contentPane.add(cboRoles);

	}

}
