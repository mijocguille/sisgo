package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import recursos.ControladorInsumo;
import recursos.Insumo;
import sistema.BaseDatos;
import sistema.Util;

public class FrmInsumos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable tblInsumos;
	private SeleccionListener listener;
	private ControladorInsumo ctrlInsumo;
	private JButton btnSeleccionar;
	private JButton btnAgregarInsumo;
	private JButton btnModificarInsumo;
	private JButton btnBajaInsumo;

	

	/**
	 * Create the frame.
	 */
	public FrmInsumos(BaseDatos db, boolean esSeleccion, SeleccionListener pListener) {
		super();
		listener = pListener;
		ctrlInsumo = new ControladorInsumo(db);
		setTitle("Listado de Insumos");
		setBounds(100, 100, 736, 412);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 700, 267);
		getContentPane().add(scrollPane);
		
		tblInsumos = new JTable();
		scrollPane.setViewportView(tblInsumos);
		tblInsumos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cargarInsumos();
		tblInsumos.getColumnModel().getColumn(1).setPreferredWidth(322);
		
		btnBajaInsumo = new JButton("Baja");
		btnBajaInsumo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int idSeleccionado = Integer.parseInt(tblInsumos.getModel().getValueAt(tblInsumos.getSelectedRow(),0).toString());
				if( idSeleccionado > 0)	{
					FrmConfirmacion frmConfirmacion = new FrmConfirmacion("¿Seguro que desea dar de baja al Insumo?",new ConfirmacionListener() {
					    @Override
					    public void onConfirmar(boolean resultado) {
					        if(resultado) {
					            if(ctrlInsumo.darBajaInsumo(idSeleccionado)){
						        	cargarInsumos();
						        } 
					        }
					    }
					});
					frmConfirmacion.setAlwaysOnTop(true);
					frmConfirmacion.setVisible(true);
				}
			}
		});
		btnBajaInsumo.setBounds(618, 289, 89, 23);
		getContentPane().add(btnBajaInsumo);
		
		btnModificarInsumo = new JButton("Editar");
		btnModificarInsumo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int idSeleccionado = Integer.parseInt(tblInsumos.getModel().getValueAt(tblInsumos.getSelectedRow(),0).toString());
				if( idSeleccionado > 0)	{
					Insumo objIns = ctrlInsumo.getTblInsumo().obtenerInsumo(idSeleccionado);
					FrmModificarInsumo frmEdit = new FrmModificarInsumo(objIns, new InsumoModificadoListener() {
						
					    @Override
					    public void onInsumoModificado(int idInsumo, String descripcionInsumo, int cantidadStock) {
					        Insumo objInsumo = new Insumo();
					        objInsumo.setIdInsumo(idInsumo);
					        objInsumo.setDescripcionInsumo(descripcionInsumo);
					        objInsumo.setCantidadStock(cantidadStock);
					        
					        if(ctrlInsumo.modificarInsumo(objInsumo)){
					        	cargarInsumos();
					        }
					    }
					});
					frmEdit.setAlwaysOnTop(true);
					frmEdit.setVisible(true);
				}
			}
		});
		btnModificarInsumo.setBounds(519, 289, 89, 23);
		getContentPane().add(btnModificarInsumo);
		
		btnAgregarInsumo = new JButton("Alta");
		btnAgregarInsumo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmNuevoInsumo frmNuevo = new FrmNuevoInsumo(new InsumoNuevoListener() {
				    @Override
				    public void onInsumoCreado(String descripcionInsumo, int cantidadStock) {
				        Insumo objIns = new Insumo();
				        objIns.setDescripcionInsumo(descripcionInsumo);
				        objIns.setCantidadStock(cantidadStock);	
				        objIns.setIdUsuario(FrmMain.idUsuarioLogueado);
				        int idInsumo = ctrlInsumo.darAltaInsumo(objIns);
				        if(idInsumo > 0) {
				        	cargarInsumos();
				        }
				    }
				});
				frmNuevo.setAlwaysOnTop(true);
				frmNuevo.setVisible(true);
			}
		});
		btnAgregarInsumo.setBounds(420, 289, 89, 23);
		getContentPane().add(btnAgregarInsumo);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnCerrar.setBounds(618, 337, 89, 23);
		getContentPane().add(btnCerrar);
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idSeleccion = Integer.parseInt(tblInsumos.getModel().getValueAt(tblInsumos.getSelectedRow(),0).toString());
				if(idSeleccion > 0) {
					listener.onSeleccion(idSeleccion);
					dispose();
				}
			}
		});
		btnSeleccionar.setVisible(false);
		btnSeleccionar.setBounds(10, 289, 89, 23);
		getContentPane().add(btnSeleccionar);
		
		
		this.setLocationRelativeTo(null); 
		gestionarBotones(esSeleccion);
		tblInsumos.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	gestionarBotonBaja();
		    }
		});
		
	}
	
	private void cargarInsumos() {
		tblInsumos.removeAll();
		tblInsumos.setModel(ctrlInsumo.listarInsumos());
	}
	
	private void gestionarBotones(boolean esSeleccion) {
		if(tblInsumos.getModel().getRowCount() == 0) {
			btnSeleccionar.setEnabled(false);
			btnModificarInsumo.setEnabled(false);
			btnBajaInsumo.setEnabled(false);		
		} else {
			if(esSeleccion) {
				btnSeleccionar.setVisible(true);
				btnModificarInsumo.setVisible(false);
				btnBajaInsumo.setVisible(false);
			}
			tblInsumos.setRowSelectionInterval(0, 0);
			gestionarBotonBaja();
		}
	}
	
	private void gestionarBotonBaja() {
		int idSeleccionado = Integer.parseInt(tblInsumos.getModel().getValueAt(tblInsumos.getSelectedRow(),0).toString());
		if( idSeleccionado > 0)	{
			Insumo obj = ctrlInsumo.getTblInsumo().obtenerInsumo(idSeleccionado);
			String fechaBaja = Util.obtenerFechaFormateada(obj.getFechaBaja());
			if(fechaBaja == "") {
				btnBajaInsumo.setEnabled(true);
			} else {
				btnBajaInsumo.setEnabled(false);
			}
		}
	}
}
