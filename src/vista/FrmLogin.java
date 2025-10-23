package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JLabel;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmLogin extends JFrame{

	private JFrame frmLogin;
	private JTextField txtUsuario;
	private JPasswordField txtContrasenia;

	/**
	 * Create the application.
	 */
	public FrmLogin() {
		super();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	
		this.setResizable(false);
		this.setTitle("Login");
		this.setBounds(100, 100, 450, 170);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		this.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(243, 100, 89, 23);
		btnAceptar.setVerticalAlignment(SwingConstants.BOTTOM);
		panel.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(335, 100, 89, 23);
		panel.add(btnCancelar);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(158, 36, 250, 20);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(52, 39, 65, 14);
		panel.add(lblUsuario);
		
		JLabel lblContrasenia = new JLabel("Contraseña");
		lblContrasenia.setBounds(52, 72, 65, 14);
		panel.add(lblContrasenia);
		
		txtContrasenia = new JPasswordField();
		txtContrasenia.setBounds(158, 67, 250, 20);
		panel.add(txtContrasenia);
		this.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{frmLogin.getContentPane(), panel, btnAceptar, btnCancelar, txtUsuario, lblUsuario, lblContrasenia, txtContrasenia}));
	}
}
