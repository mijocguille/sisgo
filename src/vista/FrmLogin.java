package vista;


import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JLabel;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import entidades.ControladorUsuario;
import sistema.BaseDatos;

import java.awt.Component;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmLogin extends JDialog{

	private static final long serialVersionUID = 1L;
	private JTextField txtUsuario;
	private JPasswordField txtContrasenia;
	private ControladorUsuario ctrlUsuario;

	/**
	 * Create the application.
	 */
	public FrmLogin(BaseDatos db) {
		super();
		ctrlUsuario = new ControladorUsuario(db);
		this.setResizable(false);
		this.setTitle("Login");
		this.setBounds(100, 100, 450, 170);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		
		JPanel panel = new JPanel();
		this.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
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
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int idUsuario = ctrlUsuario.login(txtUsuario.getText(),String.valueOf(txtContrasenia.getPassword()));
				
				if(idUsuario > 0) {
					FrmMain.idUsuarioLogueado = idUsuario;
					dispose();
				} else {
					FrmMensaje frmRespuesta = new FrmMensaje("El usuario o la contraseña no coinciden");
					frmRespuesta.setModal(true);
					frmRespuesta.setAlwaysOnTop(true);
					frmRespuesta.setVisible(true);
				}
				
			}
		});
		btnAceptar.setBounds(243, 100, 89, 23);
		btnAceptar.setVerticalAlignment(SwingConstants.BOTTOM);
		panel.add(btnAceptar);
		
		
		this.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{this.getContentPane(), panel, btnAceptar, btnCancelar, txtUsuario, lblUsuario, lblContrasenia, txtContrasenia}));
		
	}

}
