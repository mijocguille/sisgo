package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import operaciones.ControladorEquipoProyecto;
import operaciones.EquipoProyecto;
import sistema.BaseDatos;

public class FrmEquiposProyecto extends JFrame {

	private static final long serialVersionUID = 1L;
	private ControladorEquipoProyecto ctrlEquipoProyecto;
	private JTable tblEquipos;
	/**
	 * Create the frame.
	 */
	public FrmEquiposProyecto(BaseDatos db, int numeroProyecto) {
		super();
		ctrlEquipoProyecto = new ControladorEquipoProyecto(db);
		setTitle("Listado de equipos del proyecto " + numeroProyecto);
		setBounds(100, 100, 736, 412);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 700, 267);
		getContentPane().add(scrollPane);
		
		tblEquipos = new JTable();
		scrollPane.setViewportView(tblEquipos);
		tblEquipos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cargarEquipos(numeroProyecto);
		tblEquipos.getColumnModel().getColumn(1).setPreferredWidth(80);
		tblEquipos.getColumnModel().getColumn(2).setPreferredWidth(317);
		
		JButton btnQuitarEmpleado = new JButton("Quitar");
		btnQuitarEmpleado.setBounds(618, 289, 89, 23);
		btnQuitarEmpleado.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int idSeleccion = Integer.parseInt(tblEquipos.getModel().getValueAt(tblEquipos.getSelectedRow(), 0).toString());
				if(idSeleccion > 0) {
					FrmConfirmacion frmConfirmacion = new FrmConfirmacion("¿Está seguro de quitar el equipo del proyecto " + String.valueOf(numeroProyecto) + "?", new ConfirmacionListener() {
						@Override
						public void onConfirmar(boolean resultado) {
							if(resultado) {
								EquipoProyecto ep = new EquipoProyecto();
								ep.setIdEquipo(idSeleccion);
								ep.setNumeroProyecto(numeroProyecto);
								ctrlEquipoProyecto.quitarEquipo(ep);
								cargarEquipos(numeroProyecto);
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
				FrmAgregarEquipoProyecto frmAgregar = new FrmAgregarEquipoProyecto(numeroProyecto, db, new EquipoProyectoNuevoListener() {
					
					@Override
					public void onEquipoProyectoCreado(int idEquipo, int cantidad) {
						EquipoProyecto ep = new EquipoProyecto();
						ep.setIdEquipo(idEquipo);
						ep.setNumeroProyecto(numeroProyecto);
						ep.setCantidad(cantidad);
						ctrlEquipoProyecto.agregarEquipoProyecto(ep);
						cargarEquipos(numeroProyecto);
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

	private void cargarEquipos(int numeroProyecto) {
		tblEquipos.removeAll();
		tblEquipos.setModel(ctrlEquipoProyecto.listarEquipos(numeroProyecto));	
			
	}
}
