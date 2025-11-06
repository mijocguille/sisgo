package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sistema.Util;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmNuevoCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtRazonSocial;
	private JTextField txtCuit;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JSeparator separator;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private ClienteNuevoListener listener;
	private FrmMensaje frmMsg;

	
	public FrmNuevoCliente(ClienteNuevoListener pListener) {
		listener = pListener;
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
		btnAceptar.addActionListener(e -> {
			if(validarInformacionVentana()) {
	            if (listener != null) {
	                listener.onClienteCreado(
	                    txtRazonSocial.getText(),
	                    txtCuit.getText(),
	                    txtDireccion.getText(),
	                    txtTelefono.getText()
	                );
	            }
	            dispose();
			} else {
				frmMsg.setAlwaysOnTop(true);
				frmMsg.setVisible(true);
			}
	    });
		btnAceptar.setBounds(236, 139, 89, 23);
		contentPane.add(btnAceptar);
		this.setLocationRelativeTo(null); 
	}
	
	private boolean validarInformacionVentana() {

		boolean valido = true;
		String textoMensaje = "";
		if(txtRazonSocial.getText().length() == 0) {
			textoMensaje = "Debe proporcionar una razón social";
			valido = false;
		} else if (txtCuit.getText().length() == 0) {
			textoMensaje = "Debe proporcionar un CUIT";
			valido = false;
		} else if( !Util.validarCUIT(txtCuit.getText())) {
			textoMensaje = "El CUIT ingresado no es correcto";
			valido = false;
		} else if (txtDireccion.getText().length() == 0) {
			textoMensaje = "Debe proporcionar una dirección";
			valido = false;
		} else if (txtTelefono.getText().length() == 0) {
			textoMensaje = "Debe proporcionar un teléfono";
			valido = false;
		}

		frmMsg = new FrmMensaje(textoMensaje);
				
		return valido;
	}

}
