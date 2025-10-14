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

public class FrmInsumos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable tblInsumos;

	/**
	 * Create the frame.
	 */
	public FrmInsumos() {
		setTitle("Listado de Insumos");
		setBounds(100, 100, 736, 412);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 700, 267);
		getContentPane().add(scrollPane);
		
		tblInsumos = new JTable();
		scrollPane.setViewportView(tblInsumos);
		tblInsumos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblInsumos.setModel(new DefaultTableModel(
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
				"#", "Descripci\u00F3n", "Cantidad", "Fecha Alta", "Fecha Baja"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tblInsumos.getColumnModel().getColumn(1).setPreferredWidth(322);
		
		JButton btnBajaInsumo = new JButton("Baja");
		btnBajaInsumo.setBounds(618, 289, 89, 23);
		getContentPane().add(btnBajaInsumo);
		
		JButton btnModificarInsumo = new JButton("Editar");
		btnModificarInsumo.setBounds(519, 289, 89, 23);
		getContentPane().add(btnModificarInsumo);
		
		JButton btnAgregarInsumo = new JButton("Alta");
		btnAgregarInsumo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmNuevoCliente frmNuevo = new FrmNuevoCliente();
				frmNuevo.show();
			}
		});
		btnAgregarInsumo.setBounds(420, 289, 89, 23);
		getContentPane().add(btnAgregarInsumo);
		
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
