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

public class FrmProyectos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable tblProyectos;

	/**
	 * Create the frame.
	 */
	public FrmProyectos() {
		setTitle("Listado de Proyectos");
		setBounds(100, 100, 736, 412);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 700, 267);
		getContentPane().add(scrollPane);
		
		tblProyectos = new JTable();
		scrollPane.setViewportView(tblProyectos);
		tblProyectos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblProyectos.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"#", "Fecha Creaci\u00F3n", "Nombre Proyecto", "Fecha Est. Inicio", "Fecha Fin", "Pedido Asociado"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, String.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tblProyectos.getColumnModel().getColumn(1).setPreferredWidth(89);
		tblProyectos.getColumnModel().getColumn(2).setPreferredWidth(198);
		tblProyectos.getColumnModel().getColumn(3).setPreferredWidth(91);
		tblProyectos.getColumnModel().getColumn(5).setPreferredWidth(94);
		
		JButton btnEliminarProyecto = new JButton("Eliminar");
		btnEliminarProyecto.setBounds(618, 289, 89, 23);
		getContentPane().add(btnEliminarProyecto);
		
		JButton btnModificarProyecto = new JButton("Editar");
		btnModificarProyecto.setBounds(519, 289, 89, 23);
		getContentPane().add(btnModificarProyecto);
		
		JButton btnAgregarProyecto = new JButton("Crear");
		btnAgregarProyecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmNuevoCliente frmNuevo = new FrmNuevoCliente();
				frmNuevo.show();
			}
		});
		btnAgregarProyecto.setBounds(420, 289, 89, 23);
		getContentPane().add(btnAgregarProyecto);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnCerrar.setBounds(618, 337, 89, 23);
		getContentPane().add(btnCerrar);
	}

}
