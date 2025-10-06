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

public class frmLogin {

	private JFrame frmLogin;
	private JTextField txtUsuario;
	private JPasswordField txtContrasenia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmLogin window = new frmLogin();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public frmLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setResizable(false);
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 450, 170);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmLogin.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(243, 100, 89, 23);
		btnAceptar.setVerticalAlignment(SwingConstants.BOTTOM);
		panel.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLogin.dispose();
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
		frmLogin.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{frmLogin.getContentPane(), panel, btnAceptar, btnCancelar, txtUsuario, lblUsuario, lblContrasenia, txtContrasenia}));
	}
}
