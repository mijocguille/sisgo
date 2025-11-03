package vista;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import operaciones.ControladorPedido;
import operaciones.Pedido;
import sistema.BaseDatos;
import sistema.Util;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;

public class FrmNuevoProyecto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombreProyecto;
	private JTextField txtFechaInicio;
	private JTextField txtFechaFin;
	private JTextField txtPedidoAsociado;
	private ProyectoNuevoListener listener;
	private ControladorPedido ctrlPedido;
	private int numeroPedido; 

	/**
	 * Create the frame.
	 */
	public FrmNuevoProyecto(BaseDatos db, ProyectoNuevoListener pListener) {
		super();
		ctrlPedido = new ControladorPedido(db);
		listener = pListener;
		setTitle("Crear Proyecto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 542, 186);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreProyecto = new JLabel("Nombre del Proyecto");
		lblNombreProyecto.setBounds(10, 11, 129, 14);
		contentPane.add(lblNombreProyecto);
		
		JLabel lblFechaInicio = new JLabel("Inicio");
		lblFechaInicio.setBounds(10, 50, 46, 14);
		contentPane.add(lblFechaInicio);
		
		JLabel lblFechaFin = new JLabel("Fin ");
		lblFechaFin.setBounds(232, 50, 46, 14);
		contentPane.add(lblFechaFin);
		
		txtNombreProyecto = new JTextField();
		txtNombreProyecto.setBounds(149, 8, 372, 20);
		contentPane.add(txtNombreProyecto);
		txtNombreProyecto.setColumns(10);
		
		txtFechaInicio = new JTextField();
		txtFechaInicio.setBounds(66, 47, 86, 20);
		contentPane.add(txtFechaInicio);
		txtFechaInicio.setColumns(10);
		
		txtFechaFin = new JTextField();
		txtFechaFin.setBounds(288, 47, 86, 20);
		contentPane.add(txtFechaFin);
		txtFechaFin.setColumns(10);
		
		JLabel lblPedidoAsociado = new JLabel("Pedido Asociado");
		lblPedidoAsociado.setBounds(10, 86, 130, 14);
		contentPane.add(lblPedidoAsociado);
		
		txtPedidoAsociado = new JTextField();
		txtPedidoAsociado.setEditable(false);
		txtPedidoAsociado.setBounds(150, 83, 241, 20);
		contentPane.add(txtPedidoAsociado);
		txtPedidoAsociado.setColumns(10);
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmPedidos frmSeleccion = new FrmPedidos(db,true, new SeleccionListener() {
					
					@Override
					public void onSeleccion(int id) {
						Pedido p = ctrlPedido.getTblPedido().obtenerPedido(id);
						txtPedidoAsociado.setText(String.valueOf(p.getNumeroPedido()));
						numeroPedido = id;
					}
				});				
				frmSeleccion.setAlwaysOnTop(true);
				frmSeleccion.setVisible(true);
			}
		});
		btnSeleccionar.setBounds(401, 82, 115, 23);
		contentPane.add(btnSeleccionar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 111, 521, 14);
		contentPane.add(separator);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(432, 116, 89, 23);
		contentPane.add(btnCancelar);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					if(listener != null) {
					    Date fechaInicio = Util.obtenerFechaDate(txtFechaInicio.getText());
						Date fechaFin = Util.obtenerFechaDate(txtFechaFin.getText());						
						listener.onProyectoCreado(txtNombreProyecto.getText(), fechaInicio, fechaFin, numeroPedido);
					}					
					dispose();
				
			}
		});
		btnAceptar.setBounds(333, 116, 89, 23);
		contentPane.add(btnAceptar);
		this.setLocationRelativeTo(null); 

	}
}
