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

public class FrmRoles extends JFrame {
	
	
	private static final long serialVersionUID = 1L;
	private JTable tblRoles;

	/**
	 * Create the frame.
	 */
	public FrmRoles() {
		setTitle("Listado de Roles");
		setBounds(100, 100, 599, 412);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 551, 267);
		getContentPane().add(scrollPane);
		
		tblRoles = new JTable();
		scrollPane.setViewportView(tblRoles);
		tblRoles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblRoles.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"#", "Descripci\u00F3n", "Fecha Alta", "Fecha Baja"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tblRoles.getColumnModel().getColumn(1).setPreferredWidth(397);
		
		JButton btnBajaRol = new JButton("Baja");
		btnBajaRol.setBounds(464, 289, 89, 23);
		getContentPane().add(btnBajaRol);
		
		JButton btnModificarRol = new JButton("Editar");
		btnModificarRol.setBounds(365, 289, 89, 23);
		getContentPane().add(btnModificarRol);
		
		JButton btnAgregarRol = new JButton("Alta");
		btnAgregarRol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*FrmNuevoCliente frmNuevo = new FrmNuevoCliente();
				frmNuevo.show();*/
			}
		});
		btnAgregarRol.setBounds(266, 289, 89, 23);
		getContentPane().add(btnAgregarRol);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnCerrar.setBounds(464, 339, 89, 23);
		getContentPane().add(btnCerrar);
	}

}
