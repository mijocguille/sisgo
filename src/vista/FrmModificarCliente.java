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

import entidades.Cliente;

public class FrmModificarCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtRazonSocial;
	private JTextField txtCuit;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JSeparator separator;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JLabel lblIdCliente;
	private JTextField txtIdCliente;
	private ClienteModificadoListener listener;

	/**
	 * Create the frame.
	 */
	public FrmModificarCliente(Cliente objCliente, ClienteModificadoListener pListener) {
		listener = pListener;
		setTitle("Modificar Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 284);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblIdCliente = new JLabel("Id Cliente");
		lblIdCliente.setBounds(10, 22, 46, 14);
		contentPane.add(lblIdCliente);
		
		JLabel lblRazonSocial = new JLabel("Razón Social ");
		lblRazonSocial.setBounds(10, 51, 78, 14);
		contentPane.add(lblRazonSocial);
		
		JLabel lblCuit = new JLabel("CUIT");
		lblCuit.setBounds(10, 81, 78, 14);
		contentPane.add(lblCuit);
		
		JLabel lblDireccion = new JLabel("Dirección");
		lblDireccion.setBounds(10, 111, 78, 14);
		contentPane.add(lblDireccion);
		
		JLabel lblTelefono = new JLabel("Teléfono");
		lblTelefono.setBounds(10, 141, 78, 14);
		contentPane.add(lblTelefono);
		
		txtIdCliente = new JTextField();
		txtIdCliente.setText(String.valueOf(objCliente.getIdCliente()));
		txtIdCliente.setEditable(false);
		txtIdCliente.setBounds(98, 19, 86, 20);
		contentPane.add(txtIdCliente);
		txtIdCliente.setColumns(10);
		
		txtRazonSocial = new JTextField();
		txtRazonSocial.setText(objCliente.getRazonSocial());
		txtRazonSocial.setBounds(98, 51, 326, 20);
		contentPane.add(txtRazonSocial);
		txtRazonSocial.setColumns(10);
		
		txtCuit = new JTextField();
		txtCuit.setText(objCliente.getCuit());
		txtCuit.setBounds(98, 78, 326, 20);
		contentPane.add(txtCuit);
		txtCuit.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setText(objCliente.getTelefono());
		txtTelefono.setBounds(98, 138, 326, 20);
		contentPane.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		txtDireccion = new JTextField();
		txtDireccion.setText(objCliente.getDireccion());
		txtDireccion.setBounds(98, 108, 326, 20);
		contentPane.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		separator = new JSeparator();
		separator.setBounds(10, 172, 414, 2);
		contentPane.add(separator);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(335, 185, 89, 23);
		contentPane.add(btnCancelar);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(e -> {
            if (listener != null) {
                listener.onClienteModificado(
                	Integer.valueOf(txtIdCliente.getText()),
                    txtRazonSocial.getText(),
                    txtCuit.getText(),
                    txtDireccion.getText(),
                    txtTelefono.getText()
                );
            }
            dispose();
        });
		btnAceptar.setBounds(240, 185, 89, 23);
		contentPane.add(btnAceptar);
		
		

	}

}
