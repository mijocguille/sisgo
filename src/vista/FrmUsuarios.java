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

public class FrmUsuarios extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable tblUsuarios;

	/**
	 * Create the frame.
	 */
	public FrmUsuarios() {
		setTitle("Listado de Usuarios");
		setBounds(100, 100, 736, 412);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 700, 267);
		getContentPane().add(scrollPane);
		
		tblUsuarios = new JTable();
		scrollPane.setViewportView(tblUsuarios);
		tblUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblUsuarios.setModel(new DefaultTableModel(
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
				"#", "Usuario", "Descripci\u00F3n", "Fecha Alta", "Fecha Baja"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tblUsuarios.getColumnModel().getColumn(2).setPreferredWidth(322);
		
		JButton btnBajaUsuario = new JButton("Baja");
		btnBajaUsuario.setBounds(618, 289, 89, 23);
		getContentPane().add(btnBajaUsuario);
		
		JButton btnModificarUsuario = new JButton("Editar");
		btnModificarUsuario.setBounds(519, 289, 89, 23);
		getContentPane().add(btnModificarUsuario);
		
		JButton btnAgregarUsuario = new JButton("Alta");
		btnAgregarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmNuevoCliente frmNuevo = new FrmNuevoCliente();
				frmNuevo.show();
			}
		});
		btnAgregarUsuario.setBounds(420, 289, 89, 23);
		getContentPane().add(btnAgregarUsuario);
		
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
