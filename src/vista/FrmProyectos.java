package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import operaciones.ControladorPedido;
import operaciones.ControladorProyecto;
import operaciones.Pedido;
import operaciones.Proyecto;
import sistema.BaseDatos;

public class FrmProyectos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable tblProyectos;
	private ControladorProyecto ctrlProyecto;
	private ControladorPedido ctrlPedido;

	/**
	 * Create the frame.
	 */
	public FrmProyectos(BaseDatos db) {
		super();
		ctrlProyecto = new ControladorProyecto(db);
		ctrlPedido = new ControladorPedido(db);
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
		
		JButton btnEliminarProyecto = new JButton("Eliminar");
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
		
		JButton btnModificarProyecto = new JButton("Editar");
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
		
		JButton btnAgregarProyecto = new JButton("Crear");
		btnAgregarProyecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FrmNuevoProyecto frmNuevo = new FrmNuevoProyecto(new ProyectoNuevoListener() {
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
		
		JButton btnEmpleadosProyecto = new JButton("Empleados Asignados");
		btnEmpleadosProyecto.setBounds(10, 289, 178, 23);
		getContentPane().add(btnEmpleadosProyecto);
		
		JButton btnEquiposAsignados = new JButton("Equipos Asignados");
		btnEquiposAsignados.setBounds(386, 289, 178, 23);
		getContentPane().add(btnEquiposAsignados);
		
		JButton btnInsumosProyecto = new JButton("Insumos Asignados");
		btnInsumosProyecto.setBounds(198, 289, 178, 23);
		getContentPane().add(btnInsumosProyecto);
	}
	
	private void cargarProyectos() {
		tblProyectos.removeAll();
		tblProyectos.setModel(ctrlProyecto.listarProyectos());	
			
	}
}
