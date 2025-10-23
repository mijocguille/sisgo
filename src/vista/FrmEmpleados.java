package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import recursos.ControladorEmpleado;
import sistema.BaseDatos;

public class FrmEmpleados extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable tblEmpleados;
	private ControladorEmpleado ctrlEmpleado;
	private SeleccionListener listener;

	/**
	 * Create the frame.
	 */
	public FrmEmpleados(BaseDatos db, boolean esSeleccion, SeleccionListener pListener) {
		super();
		listener = pListener;
		ctrlEmpleado = new ControladorEmpleado(db);
		setTitle("Listado de Empleados");
		setBounds(100, 100, 736, 412);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 700, 267);
		getContentPane().add(scrollPane);
		
		tblEmpleados = new JTable();
		scrollPane.setViewportView(tblEmpleados);
		tblEmpleados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cargarEmpleados();
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
				FrmNuevoEmpleado frmNuevo = new FrmNuevoEmpleado();
				frmNuevo.setAlwaysOnTop(true);
				frmNuevo.setVisible(true);
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
		btnSeleccionar.setVisible(false);
		btnSeleccionar.setBounds(10, 289, 89, 23);
		btnSeleccionar.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				int idSeleccion = Integer.parseInt(tblEmpleados.getModel().getValueAt(tblEmpleados.getSelectedRow(),0).toString());
				if(idSeleccion > 0) {
					listener.onSeleccion(idSeleccion);
					dispose();
				}
			}
		});
		getContentPane().add(btnSeleccionar);
		
		if(esSeleccion) {
			btnBajaEmpleado.setVisible(false);
			btnAgregarEmpleado.setVisible(false);
			btnModificarEmpleado.setVisible(false);
			btnSeleccionar.setVisible(true);
		}
		
	}

	private void cargarEmpleados() {
		tblEmpleados.removeAll();
		tblEmpleados.setModel(ctrlEmpleado.listarEmpleados());	
			
	}
}
