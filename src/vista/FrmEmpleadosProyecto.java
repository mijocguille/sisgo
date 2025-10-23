package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import operaciones.ControladorEmpleadoProyecto;
import operaciones.EmpleadoProyecto;
import sistema.BaseDatos;

public class FrmEmpleadosProyecto extends JFrame {

	private static final long serialVersionUID = 1L;
	private ControladorEmpleadoProyecto ctrlEmpleadoProyecto;
	private JTable tblEmpleados;
	/**
	 * Create the frame.
	 */
	public FrmEmpleadosProyecto(BaseDatos db, int numeroProyecto) {
		super();
		ctrlEmpleadoProyecto = new ControladorEmpleadoProyecto(db);
		setTitle("Listado de Empleados del proyecto " + numeroProyecto);
		setBounds(100, 100, 736, 412);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 700, 267);
		getContentPane().add(scrollPane);
		
		tblEmpleados = new JTable();
		scrollPane.setViewportView(tblEmpleados);
		tblEmpleados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cargarEmpleados(numeroProyecto);
		tblEmpleados.getColumnModel().getColumn(1).setPreferredWidth(80);
		tblEmpleados.getColumnModel().getColumn(2).setPreferredWidth(317);
		
		JButton btnQuitarEmpleado = new JButton("Quitar");
		btnQuitarEmpleado.setBounds(618, 289, 89, 23);
		btnQuitarEmpleado.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int idSeleccion = Integer.parseInt(tblEmpleados.getModel().getValueAt(tblEmpleados.getSelectedRow(), 0).toString());
				if(idSeleccion > 0) {
					FrmConfirmacion frmConfirmacion = new FrmConfirmacion("¿Está seguro de quitar el empleado del proyecto " + String.valueOf(numeroProyecto) + "?", new ConfirmacionListener() {
						@Override
						public void onConfirmar(boolean resultado) {
							if(resultado) {
								EmpleadoProyecto ep = new EmpleadoProyecto();
								ep.setIdEmpleado(idSeleccion);
								ep.setNumeroProyecto(numeroProyecto);
								ctrlEmpleadoProyecto.quitarEmpleado(ep);
								cargarEmpleados(numeroProyecto);
							}
						}
					});				
					frmConfirmacion.setAlwaysOnTop(true);
					frmConfirmacion.setVisible(true);
				}
			}
		});
		getContentPane().add(btnQuitarEmpleado);
		
		JButton btnAgregarEmpleado = new JButton("Agregar");
		btnAgregarEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmAgregarEmpleadoProyecto frmAgregar = new FrmAgregarEmpleadoProyecto(numeroProyecto, db, new EmpleadoProyectoNuevoListener() {
					
					@Override
					public void onEmpleadoACrear(int idEmpleado) {
						EmpleadoProyecto ep = new EmpleadoProyecto();
						ep.setIdEmpleado(idEmpleado);
						ep.setNumeroProyecto(numeroProyecto);
						ctrlEmpleadoProyecto.agregarEmpleado(ep);
						cargarEmpleados(numeroProyecto);
					}
				});
				frmAgregar.setAlwaysOnTop(true);
				frmAgregar.setVisible(true);
			}
		});
		btnAgregarEmpleado.setBounds(525, 289, 89, 23);
		getContentPane().add(btnAgregarEmpleado);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnCerrar.setBounds(618, 337, 89, 23);
		getContentPane().add(btnCerrar);
	}

	private void cargarEmpleados(int numeroProyecto) {
		tblEmpleados.removeAll();
		tblEmpleados.setModel(ctrlEmpleadoProyecto.listarPersonalAsignado(numeroProyecto));	
			
	}
}
