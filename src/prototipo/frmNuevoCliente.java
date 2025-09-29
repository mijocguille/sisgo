package prototipo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmNuevoCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtRazonSocial;
	private JTextField txtCuit;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JSeparator separator;
	private JButton btnCancelar;
	private JButton btnAceptar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmNuevoCliente frame = new frmNuevoCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frmNuevoCliente() {
		setTitle("Nuevo Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 210);
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
		
		JLabel lblDireccion = new JLabel("Dirección");
		lblDireccion.setBounds(10, 71, 78, 14);
		contentPane.add(lblDireccion);
		
		JLabel lblTelefono = new JLabel("Teléfono");
		lblTelefono.setBounds(10, 101, 78, 14);
		contentPane.add(lblTelefono);
		
		txtRazonSocial = new JTextField();
		txtRazonSocial.setBounds(98, 11, 326, 20);
		contentPane.add(txtRazonSocial);
		txtRazonSocial.setColumns(10);
		
		txtCuit = new JTextField();
		txtCuit.setBounds(98, 38, 326, 20);
		contentPane.add(txtCuit);
		txtCuit.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(98, 98, 326, 20);
		contentPane.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(98, 68, 326, 20);
		contentPane.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		separator = new JSeparator();
		separator.setBounds(10, 126, 414, 2);
		contentPane.add(separator);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(335, 139, 89, 23);
		contentPane.add(btnCancelar);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(236, 139, 89, 23);
		contentPane.add(btnAceptar);

	}

}
