package prototipo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

public class frmNuevoPedido extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmNuevoPedido frame = new frmNuevoPedido();
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
	public frmNuevoPedido() {
		setTitle("Nuevo Pedido");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 548);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(240, 475, 89, 23);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(339, 475, 89, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(10, 71, 46, 14);
		contentPane.add(lblCliente);
		
		JLabel lblDescripcion = new JLabel("Seleccione el cliente al cual le desea crear el nuevo pedido");
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescripcion.setBounds(10, 11, 414, 49);
		contentPane.add(lblDescripcion);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(66, 68, 358, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(264, 99, 75, 23);
		contentPane.add(btnBuscar);
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.setBounds(349, 99, 75, 23);
		contentPane.add(btnNuevo);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 460, 434, 14);
		contentPane.add(separator);
		
		JLabel lblDetallePedido = new JLabel("Detalle del Pedido");
		lblDetallePedido.setBounds(10, 121, 153, 14);
		contentPane.add(lblDetallePedido);
		
		JLabel lblCaracteristicasConstruccion = new JLabel("Características de la construcción");
		lblCaracteristicasConstruccion.setBounds(10, 300, 414, 14);
		contentPane.add(lblCaracteristicasConstruccion);
		
		JTextArea txaDetallePedido = new JTextArea();
		txaDetallePedido.setBounds(10, 146, 414, 143);
		contentPane.add(txaDetallePedido);
		
		JTextArea txaCaracteristicasConstruccion = new JTextArea();
		txaCaracteristicasConstruccion.setBounds(10, 325, 414, 124);
		contentPane.add(txaCaracteristicasConstruccion);

	}
}
