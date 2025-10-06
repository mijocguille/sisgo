package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmClientes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmClientes frame = new frmClientes();
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
	public frmClientes() {
		setTitle("Listado de Clientes");
		setBounds(100, 100, 736, 412);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 700, 267);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
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
				"#", "Raz\u00F3n Social", "Cuit", "Direcci\u00F3n ", "Tel\u00E9fono"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		JButton btnBajaCliente = new JButton("Baja");
		btnBajaCliente.setBounds(618, 289, 89, 23);
		getContentPane().add(btnBajaCliente);
		
		JButton btnModificarCliente = new JButton("Editar");
		btnModificarCliente.setBounds(519, 289, 89, 23);
		getContentPane().add(btnModificarCliente);
		
		JButton btnAgregarCliente = new JButton("Alta");
		btnAgregarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmNuevoCliente frmNuevo = new frmNuevoCliente();
				frmNuevo.show();
			}
		});
		btnAgregarCliente.setBounds(420, 289, 89, 23);
		getContentPane().add(btnAgregarCliente);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnCerrar.setBounds(618, 337, 89, 23);
		getContentPane().add(btnCerrar);
		table.getColumnModel().getColumn(1).setPreferredWidth(245);
		table.getColumnModel().getColumn(3).setPreferredWidth(152);
	}
	
	
}
