package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import recursos.ControladorEquipo;
import recursos.Equipo;
import sistema.BaseDatos;
import sistema.Util;

public class FrmEquipos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable tblEquipos;
	private SeleccionListener listener;
	private ControladorEquipo ctrlEquipo;
	private JButton btnSeleccionar;
	private JButton btnAgregarEquipo;
	private JButton btnModificarEquipo;
	private JButton btnBajaEquipo;

	/**
	 * Create the frame.
	 */
	public FrmEquipos(BaseDatos db, boolean esSeleccion, SeleccionListener pListener) {
		super();
		ctrlEquipo = new ControladorEquipo(db);
		listener = pListener;
		setTitle("Listado de Equipos");
		setBounds(100, 100, 736, 412);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 700, 267);
		getContentPane().add(scrollPane);
		
		tblEquipos = new JTable();
		scrollPane.setViewportView(tblEquipos);
		cargarEquipos(esSeleccion);
		tblEquipos.getColumnModel().getColumn(1).setPreferredWidth(322);
		
		btnBajaEquipo = new JButton("Baja");
		btnBajaEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int idSeleccionado = Integer.parseInt(tblEquipos.getModel().getValueAt(tblEquipos.getSelectedRow(),0).toString());
				if( idSeleccionado > 0)	{
					FrmConfirmacion frmConfirmacion = new FrmConfirmacion("¿Seguro que desea dar de baja al equipo?",new ConfirmacionListener() {
					    @Override
					    public void onConfirmar(boolean resultado) {
					        if(resultado) {
						        if(ctrlEquipo.darBajaEquipo(idSeleccionado)){
						        	cargarEquipos(esSeleccion);
						        } 
					        }
					    }
					});
					frmConfirmacion.setAlwaysOnTop(true);
					frmConfirmacion.setVisible(true);
				}
			}
		});
		btnBajaEquipo.setBounds(618, 289, 89, 23);
		getContentPane().add(btnBajaEquipo);
		
	    btnModificarEquipo = new JButton("Editar");
		btnModificarEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int idSeleccionado = Integer.parseInt(tblEquipos.getModel().getValueAt(tblEquipos.getSelectedRow(),0).toString());
				if( idSeleccionado > 0)	{
					Equipo objEquipo = ctrlEquipo.getTblEquipos().obtenerEquipo(idSeleccionado);
					FrmModificarEquipo frmEdit = new FrmModificarEquipo(objEquipo, new EquipoModificadoListener() {
					    @Override
					    public void onEquipoModificado(int idEquipo, String descripcionEquipo, int cantidad) {
					    	Equipo objEq = new Equipo();
					    	objEq.setIdEquipo(idEquipo);
					    	objEq.setDescripcionEquipo(descripcionEquipo);
					    	objEq.setCantidadEquipos(cantidad);
					        if(ctrlEquipo.modificarEquipo(objEq)){
					        	cargarEquipos(esSeleccion);
					        }
					    }
					});
					frmEdit.setAlwaysOnTop(true);
					frmEdit.setVisible(true);
				}
			}
		});
		btnModificarEquipo.setBounds(519, 289, 89, 23);
		getContentPane().add(btnModificarEquipo);
		
		btnAgregarEquipo = new JButton("Alta");
		btnAgregarEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmNuevoEquipo frmNuevo = new FrmNuevoEquipo(new EquipoNuevoListener() {
				    @Override
				    public void onEquipoCreado(String descripcionEquipo, int cantidad) {
				    	Equipo objEq = new Equipo();
				    	objEq.setDescripcionEquipo(descripcionEquipo);
				    	objEq.setCantidadEquipos(cantidad);
				    	objEq.setIdUsuario(FrmMain.idUsuarioLogueado);
				        int idEquipo = ctrlEquipo.darAltaEquipo(objEq);
				        if(idEquipo > 0) {
				        	cargarEquipos(esSeleccion);
				        }
				    }
				});
				frmNuevo.setAlwaysOnTop(true);
				frmNuevo.setVisible(true);
			}
		});
		btnAgregarEquipo.setBounds(420, 289, 89, 23);
		getContentPane().add(btnAgregarEquipo);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnCerrar.setBounds(618, 337, 89, 23);
		getContentPane().add(btnCerrar);
		
		btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.setVisible(false);
		btnSeleccionar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int idSeleccion = Integer.parseInt(tblEquipos.getModel().getValueAt(tblEquipos.getSelectedRow(),0).toString());
				if(idSeleccion > 0) {
					listener.onSeleccion(idSeleccion);
					dispose();
				}
			}
		});
		btnSeleccionar.setBounds(10, 289, 120, 23);
		getContentPane().add(btnSeleccionar);
		
		
		this.setLocationRelativeTo(null);
		gestionarBotones(esSeleccion);
		tblEquipos.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	gestionarBotonBaja();
		    }
		});
	}
	
	private void cargarEquipos(boolean esSeleccion) {
		tblEquipos.removeAll();
		tblEquipos.setModel(ctrlEquipo.listarEquipos(esSeleccion));
	}

	private void gestionarBotones(boolean esSeleccion) {
		if(tblEquipos.getModel().getRowCount() == 0) {
			btnSeleccionar.setEnabled(false);
			btnModificarEquipo.setEnabled(false);
			btnBajaEquipo.setEnabled(false);		
		} else {
			if(esSeleccion) {
				btnSeleccionar.setVisible(true);
				btnModificarEquipo.setVisible(false);
				btnBajaEquipo.setVisible(false);
			}
			tblEquipos.setRowSelectionInterval(0, 0);
			gestionarBotonBaja();
		}
	}
	
	private void gestionarBotonBaja() {
		int idSeleccionado = Integer.parseInt(tblEquipos.getModel().getValueAt(tblEquipos.getSelectedRow(),0).toString());
		if( idSeleccionado > 0)	{
			Equipo obj = ctrlEquipo.getTblEquipos().obtenerEquipo(idSeleccionado);
			String fechaBaja = Util.obtenerFechaFormateada(obj.getFechaBaja());
			if(fechaBaja == "") {
				btnBajaEquipo.setEnabled(true);
			} else {
				btnBajaEquipo.setEnabled(false);
			}
		}
	}
}
