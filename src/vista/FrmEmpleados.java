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

public class FrmEmpleados extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable tblEmpleados;

	/**
	 * Create the frame.
	 */
	public FrmEmpleados() {
		setTitle("Listado de Empleados");
		setBounds(100, 100, 736, 412);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 700, 267);
		getContentPane().add(scrollPane);
		
		tblEmpleados = new JTable();
		scrollPane.setViewportView(tblEmpleados);
		tblEmpleados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblEmpleados.setModel(new DefaultTableModel(
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
				"#", "Legajo", "Nombre y Apellido", "Fecha Alta", "Fecha Baja"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tblEmpleados.getColumnModel().getColumn(1).setPreferredWidth(80);
		tblEmpleados.getColumnModel().getColumn(2).setPreferredWidth(317);
		
		JButton btnBajaEmpleado = new JButton("Baja");
		btnBajaEmpleado.setBounds(618, 289, 89, 23);
		getContentPane().add(btnBajaEmpleado);
		
		JButton btnModificarEmpleado = new JButton("Editar");
		btnModificarEmpleado.setBounds(519, 289, 89, 23);
		getContentPane().add(btnModificarEmpleado);
		
		JButton btnAgregarEmpleado = new JButton("Alta");
		btnAgregarEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmNuevoCliente frmNuevo = new FrmNuevoCliente();
				frmNuevo.show();
			}
		});
		btnAgregarEmpleado.setBounds(420, 289, 89, 23);
		getContentPane().add(btnAgregarEmpleado);
		
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
