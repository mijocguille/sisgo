package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class FrmPedidos extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTable tblPedidos;


	/**
	 * Create the frame.
	 */
	public FrmPedidos() {
		setTitle("Listado de Pedidos");
		setBounds(100, 100, 736, 412);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 700, 267);
		getContentPane().add(scrollPane);
		
		tblPedidos = new JTable();
		scrollPane.setViewportView(tblPedidos);
		tblPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblPedidos.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"#", "Fecha Pedido", "Detalle de Pedido", "Caracter\u00EDsticas", "Proyecto Asignado"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tblPedidos.getColumnModel().getColumn(1).setPreferredWidth(100);
		tblPedidos.getColumnModel().getColumn(2).setPreferredWidth(185);
		tblPedidos.getColumnModel().getColumn(3).setPreferredWidth(152);
		tblPedidos.getColumnModel().getColumn(4).setPreferredWidth(110);
		
		JButton btnAnularPedido = new JButton("Anular");
		btnAnularPedido.setBounds(618, 289, 89, 23);
		getContentPane().add(btnAnularPedido);
		
		JButton btnModificarPedido = new JButton("Editar");
		btnModificarPedido.setBounds(519, 289, 89, 23);
		getContentPane().add(btnModificarPedido);
		
		JButton btnAgregarPedido = new JButton("Alta");
		btnAgregarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*FrmNuevoCliente frmNuevo = new FrmNuevoCliente();
				frmNuevo.show();*/
			}
		});
		btnAgregarPedido.setBounds(420, 289, 89, 23);
		getContentPane().add(btnAgregarPedido);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnCerrar.setBounds(618, 337, 89, 23);
		getContentPane().add(btnCerrar);
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.setBounds(10, 289, 89, 23);
		getContentPane().add(btnSeleccionar);
	}
}
