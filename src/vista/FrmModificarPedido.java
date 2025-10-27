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

import entidades.Cliente;
import operaciones.ControladorPedido;
import operaciones.Pedido;
import sistema.BaseDatos;

import javax.swing.JTextArea;

public class FrmModificarPedido extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCliente;
	private JTextField txtNumeroPedido;
	private ControladorPedido ctrlPedido;
	private PedidoModificadoListener listener;
	private int idCliente;


	/**
	 * Create the frame.
	 */
	public FrmModificarPedido(BaseDatos db, Pedido objPedido, PedidoModificadoListener pListener) {
		super();
		ctrlPedido = new ControladorPedido(db);
		listener = pListener;
		
		Cliente objCli = ctrlPedido.getTblCliente().obtenerCliente(objPedido.getIdCliente());
		
		setTitle("Modificar Pedido");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 512, 405);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(10, 39, 46, 14);
		contentPane.add(lblCliente);
		
		txtCliente = new JTextField();
		txtCliente.setEditable(false);
		txtCliente.setText(objCli.getRazonSocial());
		txtCliente.setBounds(66, 36, 309, 20);
		contentPane.add(txtCliente);
		txtCliente.setColumns(10);
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		
		btnSeleccionar.setBounds(385, 35, 102, 23);
		contentPane.add(btnSeleccionar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 329, 487, 7);
		contentPane.add(separator);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(398, 337, 89, 23);
		contentPane.add(btnCancelar);
		
		JTextArea txtDetalle = new JTextArea();
		txtDetalle.setBounds(66, 83, 421, 93);
		txtDetalle.setText(objPedido.getDetallePedido());
		contentPane.add(txtDetalle);
		
		JTextArea txtCaracteristicas = new JTextArea();
		txtCaracteristicas.setText(objPedido.getCaracteristicasPedido());
		txtCaracteristicas.setBounds(66, 212, 420, 106);
		contentPane.add(txtCaracteristicas);
		
		JLabel lblDetalle = new JLabel("Detalle");
		lblDetalle.setBounds(10, 64, 89, 14);
		contentPane.add(lblDetalle);
		
		JLabel lblCarateristicas = new JLabel("Características");
		lblCarateristicas.setBounds(10, 187, 89, 14);
		contentPane.add(lblCarateristicas);
		
		JLabel lblNumeroPedido = new JLabel("Número de Pedido");
		lblNumeroPedido.setBounds(10, 11, 95, 14);
		contentPane.add(lblNumeroPedido);
		
		txtNumeroPedido = new JTextField();
		txtNumeroPedido.setText(String.valueOf(objPedido.getNumeroPedido()));
		txtNumeroPedido.setEditable(false);
		txtNumeroPedido.setBounds(115, 8, 86, 20);
		contentPane.add(txtNumeroPedido);
		txtNumeroPedido.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 if (listener != null) {
		                listener.onPedidoModificado(
		                		objPedido.getNumeroPedido(),
		                		idCliente,
		                		txtDetalle.getText(),
		                		txtCaracteristicas.getText()
		                		);
		            }
		            dispose();	
			}
		});
		btnAceptar.setBounds(299, 337, 89, 23);
		contentPane.add(btnAceptar);

	}
}
