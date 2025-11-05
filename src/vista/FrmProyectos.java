package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import operaciones.ControladorEmpleadoProyecto;
import operaciones.ControladorEquipoProyecto;
import operaciones.ControladorInsumoProyecto;
import operaciones.ControladorPedido;
import operaciones.ControladorPresupuesto;
import operaciones.ControladorProyecto;
import operaciones.Pedido;
import operaciones.Proyecto;
import sistema.BaseDatos;

public class FrmProyectos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable tblProyectos;
	private ControladorProyecto ctrlProyecto;
	private ControladorPedido ctrlPedido;
	private ControladorInsumoProyecto ctrlInsumoProyecto;
	private ControladorEmpleadoProyecto ctrlEmpleadoProyecto;
	private ControladorEquipoProyecto ctrlEquipoProyecto;
	private ControladorPresupuesto ctrlPresupuesto;
	private JButton btnAgregarProyecto;
	private JButton btnModificarProyecto;
	private JButton btnEliminarProyecto;
	private JButton btnPresupuestos;
	private JButton btnEmpleadosProyecto;
	private JButton btnEquiposAsignados;
	private JButton btnInsumosProyecto;

	/**
	 * Create the frame.
	 */
	public FrmProyectos(BaseDatos db) {
		super();
		ctrlProyecto = new ControladorProyecto(db);
		ctrlPedido = new ControladorPedido(db);
		ctrlEmpleadoProyecto = new ControladorEmpleadoProyecto(db);
		ctrlEquipoProyecto = new ControladorEquipoProyecto(db);
		ctrlInsumoProyecto = new ControladorInsumoProyecto(db);
		ctrlPresupuesto = new ControladorPresupuesto(db);
		
		setTitle("Listado de Proyectos");
		setBounds(100, 100, 736, 437);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 700, 267);
		getContentPane().add(scrollPane);
		
		tblProyectos = new JTable();
		scrollPane.setViewportView(tblProyectos);
		tblProyectos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cargarProyectos();
		
		btnEliminarProyecto = new JButton("Eliminar");
		btnEliminarProyecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				int idSeleccionado = Integer.parseInt(tblProyectos.getModel().getValueAt(tblProyectos.getSelectedRow(),0).toString());
				if( idSeleccionado > 0)	{
					FrmConfirmacion frmConfirmacion = new FrmConfirmacion("¿Seguro que desea eliminar el proyecto?",new ConfirmacionListener() {
					    @Override
					    public void onConfirmar(boolean resultado) {
					        if(resultado) {
					        	ctrlProyecto.bajaProyecto(idSeleccionado);
					        	cargarProyectos();
					        }
					    }
					});
					frmConfirmacion.setAlwaysOnTop(true);
					frmConfirmacion.setVisible(true);
				}
			}
		});
		btnEliminarProyecto.setBounds(621, 330, 89, 23);
		getContentPane().add(btnEliminarProyecto);
		
		btnModificarProyecto = new JButton("Editar");
		btnModificarProyecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int idSeleccionado = Integer.parseInt(tblProyectos.getModel().getValueAt(tblProyectos.getSelectedRow(),0).toString());
				if( idSeleccionado > 0)	{
					Proyecto objProyecto = ctrlProyecto.getTblProyecto().obtenerProyecto(idSeleccionado);
					FrmModificarProyecto frmEdit = new FrmModificarProyecto(objProyecto, new ProyectoModificadoListener() {
					    @Override
					    public void onProyectoModificado(int numeroProyecto, String nombreProyecto, Date fechaEstimadaInicio, Date fechaFin, int numeroPedido) {
					    	Pedido objPed = ctrlProyecto.getTblPedido().obtenerPedido(numeroPedido);
					    	objPed.setNumeroProyecto(numeroProyecto);
					    	if(ctrlPedido.modificarPedido(objPed))
					    	{
						        Proyecto objProy = new Proyecto();
						        objProy.setNumeroProyecto(numeroProyecto);
						        objProy.setNombreProyecto(nombreProyecto);
						        objProy.setFechaEstimadaInicio(fechaEstimadaInicio);
						        objProy.setFechaFin(fechaFin);
						        if(ctrlProyecto.modificaProyecto(objProy)){
						        	cargarProyectos();
						        }
					        }
					    }
					});
					frmEdit.setAlwaysOnTop(true);
					frmEdit.setVisible(true);
				}
			}
		});
		btnModificarProyecto.setBounds(522, 330, 89, 23);
		getContentPane().add(btnModificarProyecto);
		
		btnAgregarProyecto = new JButton("Crear");
		btnAgregarProyecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FrmNuevoProyecto frmNuevo = new FrmNuevoProyecto(db, new ProyectoNuevoListener() {
				    @Override
				    public void onProyectoCreado(String nombreProyecto, Date fechaEstimadaInicio, Date fechaFin, int numeroPedido) {
				    	Proyecto objProy = new Proyecto();
				        objProy.setNombreProyecto(nombreProyecto);
				        objProy.setFechaEstimadaInicio(fechaEstimadaInicio);
				        objProy.setFechaFin(fechaFin);
				        objProy.setIdUsuario(FrmMain.idUsuarioLogueado);
				        int numeroProyecto = ctrlProyecto.crearProyecto(objProy);
				    	Pedido objPed = ctrlProyecto.getTblPedido().obtenerPedido(numeroPedido);
				    	objPed.setNumeroProyecto(numeroProyecto);
				    	ctrlPedido.modificarPedido(objPed);
				    	cargarProyectos();   
				    }
				});
				frmNuevo.setAlwaysOnTop(true);
				frmNuevo.setVisible(true);
			}
		});
		btnAgregarProyecto.setBounds(423, 330, 89, 23);
		getContentPane().add(btnAgregarProyecto);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnCerrar.setBounds(621, 364, 89, 23);
		getContentPane().add(btnCerrar);
		
		btnEmpleadosProyecto = new JButton("Empleados Asignados");
		btnEmpleadosProyecto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int idSeleccionado = Integer.parseInt(tblProyectos.getModel().getValueAt(tblProyectos.getSelectedRow(),0).toString());
				if( idSeleccionado > 0)	{	
					FrmEmpleadosProyecto frmEmpleados = new FrmEmpleadosProyecto(db, idSeleccionado);
					frmEmpleados.setAlwaysOnTop(true);
					frmEmpleados.setVisible(true);
				}
			}
		});
		btnEmpleadosProyecto.setBounds(10, 289, 178, 23);
		getContentPane().add(btnEmpleadosProyecto);
		
		btnEquiposAsignados = new JButton("Equipos Asignados");
		btnEquiposAsignados.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int idSeleccionado = Integer.parseInt(tblProyectos.getModel().getValueAt(tblProyectos.getSelectedRow(),0).toString());
				if( idSeleccionado > 0)	{	
					FrmEquiposProyecto frmEquipos = new FrmEquiposProyecto(db, idSeleccionado);
					frmEquipos.setAlwaysOnTop(true);
					frmEquipos.setVisible(true);
				}
			}
		});
		btnEquiposAsignados.setBounds(386, 289, 178, 23);
		getContentPane().add(btnEquiposAsignados);
		
		btnInsumosProyecto = new JButton("Insumos Asignados");
		btnInsumosProyecto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int idSeleccionado = Integer.parseInt(tblProyectos.getModel().getValueAt(tblProyectos.getSelectedRow(),0).toString());
				if( idSeleccionado > 0)	{	
					FrmInsumosProyecto frmInsumos = new FrmInsumosProyecto(db, idSeleccionado);
					frmInsumos.setAlwaysOnTop(true);
					frmInsumos.setVisible(true);
				}
			}
		});
		btnInsumosProyecto.setBounds(198, 289, 178, 23);
		getContentPane().add(btnInsumosProyecto);
		
		btnPresupuestos = new JButton("Presupuestos");
		btnPresupuestos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int idSeleccionado = Integer.parseInt(tblProyectos.getModel().getValueAt(tblProyectos.getSelectedRow(),0).toString());
				if( idSeleccionado > 0)	{	
					FrmPresupuestos frmPresupuestos = new FrmPresupuestos(db, idSeleccionado);
					frmPresupuestos.setAlwaysOnTop(true);
					frmPresupuestos.setVisible(true);
				}
			}
		});
		btnPresupuestos.setBounds(10, 364, 178, 23);
		getContentPane().add(btnPresupuestos);
		this.setLocationRelativeTo(null); 
		gestionarBotones();
		tblProyectos.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	int idSeleccionado = Integer.parseInt(tblProyectos.getModel().getValueAt(tblProyectos.getSelectedRow(),0).toString());
				if( idSeleccionado > 0)	{
					btnModificarProyecto.setEnabled(true);
					btnEliminarProyecto.setEnabled(true);
					btnEmpleadosProyecto.setEnabled(true);
					btnEquiposAsignados.setEnabled(true);
					btnInsumosProyecto.setEnabled(true);
					btnPresupuestos.setEnabled(true);
				}
				
				int cantidadEmpleadosProyecto = ctrlEmpleadoProyecto.getTblEmpleadoProyecto().obtenerEmpleadosProyecto(idSeleccionado).size();
				int cantidadEquiposProyecto = ctrlEquipoProyecto.getTblEquipoProyecto().obtenerEquiposProyecto(idSeleccionado).size();
				int cantidadInsumosProyecto = ctrlInsumoProyecto.getTblInsumoProyecto().obtenerInsumosProyecto(idSeleccionado).size();
				int cantidadPresupuestosProyecto = ctrlPresupuesto.getTblPresupuesto().obtenerPresupuestos(idSeleccionado).size();
				btnEmpleadosProyecto.setText("Empleados Asignados ("+ String.valueOf(cantidadEmpleadosProyecto) +")");
				btnEquiposAsignados.setText("Equipos Asignados ("+ String.valueOf(cantidadEquiposProyecto) +")");
				btnInsumosProyecto.setText("Insumos Asignados ("+ String.valueOf(cantidadInsumosProyecto) +")");
				btnPresupuestos.setText("Presupuestos ("+ String.valueOf(cantidadPresupuestosProyecto) +")");
		    }
		});
	}
	
	private void cargarProyectos() {
		tblProyectos.removeAll();
		tblProyectos.setModel(ctrlProyecto.listarProyectos());
	}
	

	private void gestionarBotones() {
		if(tblProyectos.getModel().getRowCount() == 0) {
			btnEmpleadosProyecto.setEnabled(false);
			btnEquiposAsignados.setEnabled(false);
			btnInsumosProyecto.setEnabled(false);
			btnPresupuestos.setEnabled(false);
			btnModificarProyecto.setEnabled(false);
			btnEliminarProyecto.setEnabled(false);		
		} else {
			tblProyectos.setRowSelectionInterval(0, 0);
		}
	}

}
