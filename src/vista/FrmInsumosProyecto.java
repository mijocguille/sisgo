package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import operaciones.ControladorInsumoProyecto;
import operaciones.InsumoProyecto;
import sistema.BaseDatos;

public class FrmInsumosProyecto extends JFrame {

	private static final long serialVersionUID = 1L;
	private ControladorInsumoProyecto ctrlInsumoProyecto;
	private JTable tblInsumos;
	/**
	 * Create the frame.
	 */
	public FrmInsumosProyecto(BaseDatos db, int numeroProyecto) {
		super();
		ctrlInsumoProyecto = new ControladorInsumoProyecto(db);
		setTitle("Listado de Insumos del proyecto " + numeroProyecto);
		setBounds(100, 100, 736, 412);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 700, 267);
		getContentPane().add(scrollPane);
		
		tblInsumos = new JTable();
		scrollPane.setViewportView(tblInsumos);
		tblInsumos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cargarInsumos(numeroProyecto);
		tblInsumos.getColumnModel().getColumn(1).setPreferredWidth(80);
		tblInsumos.getColumnModel().getColumn(2).setPreferredWidth(317);
		
		JButton btnQuitarInsumo = new JButton("Quitar");
		btnQuitarInsumo.setBounds(618, 289, 89, 23);
		btnQuitarInsumo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int idSeleccion = Integer.parseInt(tblInsumos.getModel().getValueAt(tblInsumos.getSelectedRow(), 0).toString());
				if(idSeleccion > 0) {
					FrmConfirmacion frmConfirmacion = new FrmConfirmacion("¿Está seguro de quitar el Insumo del proyecto " + String.valueOf(numeroProyecto) + "?", new ConfirmacionListener() {
						@Override
						public void onConfirmar(boolean resultado) {
							if(resultado) {
								InsumoProyecto ep = new InsumoProyecto();
								ep.setIdInsumo(idSeleccion);
								ep.setNumeroProyecto(numeroProyecto);
								ctrlInsumoProyecto.quitarInsumo(ep);
								cargarInsumos(numeroProyecto);
							}
						}
					});				
					frmConfirmacion.setAlwaysOnTop(true);
					frmConfirmacion.setVisible(true);
				}
			}
		});
		getContentPane().add(btnQuitarInsumo);
		
		JButton btnAgregarInsumo = new JButton("Agregar");
		btnAgregarInsumo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmAgregarInsumoProyecto frmAgregar = new FrmAgregarInsumoProyecto(numeroProyecto, db, new InsumoProyectoNuevoListener() {
					
					@Override
					public void onInsumoProyectoCreado(int idInsumo, int cantidad) {
						InsumoProyecto ip = new InsumoProyecto();
						ip.setIdInsumo(idInsumo);
						ip.setNumeroProyecto(numeroProyecto);
						ip.setCantidad(cantidad);
						ctrlInsumoProyecto.agregarInsumoProyecto(ip);
						cargarInsumos(numeroProyecto);
					}
				});
				frmAgregar.setAlwaysOnTop(true);
				frmAgregar.setVisible(true);
			}
		});
		btnAgregarInsumo.setBounds(525, 289, 89, 23);
		getContentPane().add(btnAgregarInsumo);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnCerrar.setBounds(618, 337, 89, 23);
		getContentPane().add(btnCerrar);
		this.setLocationRelativeTo(null); 
	}

	private void cargarInsumos(int numeroProyecto) {
		tblInsumos.removeAll();
		tblInsumos.setModel(ctrlInsumoProyecto.listarInsumos(numeroProyecto));	
			
	}
}
