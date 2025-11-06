package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import recursos.ControladorEmpleado;
import recursos.Empleado;
import sistema.BaseDatos;
import sistema.Util;

public class FrmEmpleados extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable tblEmpleados;
	private ControladorEmpleado ctrlEmpleado;
	private SeleccionListener listener;
	private JButton btnSeleccionar;
	private JButton btnAgregarEmpleado;
	private JButton btnModificarEmpleado;
	private JButton btnBajaEmpleado;

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
		cargarEmpleados(esSeleccion);
		tblEmpleados.getColumnModel().getColumn(1).setPreferredWidth(80);
		tblEmpleados.getColumnModel().getColumn(2).setPreferredWidth(317);
		
		btnBajaEmpleado = new JButton("Baja");
		btnBajaEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int idSeleccionado = Integer.parseInt(tblEmpleados.getModel().getValueAt(tblEmpleados.getSelectedRow(),0).toString());
				if( idSeleccionado > 0)	{
					FrmConfirmacion frmConfirmacion = new FrmConfirmacion("¿Seguro que desea dar de baja al empleado?",new ConfirmacionListener() {
					    @Override
					    public void onConfirmar(boolean resultado) {
					        if(resultado) {
						        if(ctrlEmpleado.bajaEmpleado(idSeleccionado)){
						        	cargarEmpleados(esSeleccion);
						        } 
					        }
					    }
					});
					frmConfirmacion.setAlwaysOnTop(true);
					frmConfirmacion.setVisible(true);
				}
			}
		});
		btnBajaEmpleado.setBounds(618, 289, 89, 23);
		
		getContentPane().add(btnBajaEmpleado);
		
		btnModificarEmpleado = new JButton("Editar");
		btnModificarEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int idSeleccionado = Integer.parseInt(tblEmpleados.getModel().getValueAt(tblEmpleados.getSelectedRow(),0).toString());
				if( idSeleccionado > 0)	{
					Empleado objEmpleado = ctrlEmpleado.getTblEmpleado().obtenerEmpleado(idSeleccionado);
					FrmModificarEmpleado frmEdit = new FrmModificarEmpleado(db, objEmpleado, new EmpleadoModificadoListener() {
					    @Override
					    public void onEmpleadoModificado(int idEmpleado, int legajo, String nombre, String apellido) {
					        Empleado objEmp = new Empleado();
					        objEmp.setIdEmpleado(idEmpleado);
					        objEmp.setLegajo(legajo);
					        objEmp.setNombre(nombre);
					        objEmp.setApellido(apellido);
					        
					        if(ctrlEmpleado.modificaEmpleado(objEmp)){
					        	cargarEmpleados(esSeleccion);
					        }
					    }
					});
					frmEdit.setAlwaysOnTop(true);
					frmEdit.setVisible(true);
				}
			}
		});		
		btnModificarEmpleado.setBounds(519, 289, 89, 23);
		getContentPane().add(btnModificarEmpleado);
		
		btnAgregarEmpleado = new JButton("Alta");
		btnAgregarEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmNuevoEmpleado frmNuevo = new FrmNuevoEmpleado(db, new EmpleadoNuevoListener() {
				    @Override
				    public void onEmpleadoCreado(int legajo, String nombre, String apellido) {
				        Empleado objEmp = new Empleado();
				        objEmp.setLegajo(legajo);
				        objEmp.setNombre(nombre);
				        objEmp.setApellido(apellido);
				        objEmp.setIdUsuario(FrmMain.idUsuarioLogueado);
				        int idEmpleado = ctrlEmpleado.darAltaEmpleado(objEmp);
				        if(idEmpleado > 0) {
				        	cargarEmpleados(esSeleccion);
				        }
				    }
				});
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
		
		btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.setVisible(false);
		btnSeleccionar.setBounds(10, 289, 120, 23);
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
		
	
		this.setLocationRelativeTo(null); 
		gestionarBotones(esSeleccion);
		tblEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	gestionarBotonBaja();
		    }
		});
	}

	private void cargarEmpleados(boolean esSeleccion) {
		tblEmpleados.removeAll();
		tblEmpleados.setModel(ctrlEmpleado.listarEmpleados(esSeleccion));	
	}
	
	private void gestionarBotones(boolean esSeleccion) {
		if(tblEmpleados.getModel().getRowCount() == 0) {
			btnSeleccionar.setEnabled(false);
			btnModificarEmpleado.setEnabled(false);
			btnBajaEmpleado.setEnabled(false);		
		} else {
			if(esSeleccion) {
				btnSeleccionar.setVisible(true);
				btnModificarEmpleado.setVisible(false);
				btnBajaEmpleado.setVisible(false);
			}
			tblEmpleados.setRowSelectionInterval(0, 0);
			gestionarBotonBaja();
		}
	}
	
	private void gestionarBotonBaja() {
		int idSeleccionado = Integer.parseInt(tblEmpleados.getModel().getValueAt(tblEmpleados.getSelectedRow(),0).toString());
		if( idSeleccionado > 0)	{
			Empleado obj = ctrlEmpleado.getTblEmpleado().obtenerEmpleado(idSeleccionado);
			String fechaBaja = Util.obtenerFechaFormateada(obj.getFechaBaja());
			if(fechaBaja == "") {
				btnBajaEmpleado.setEnabled(true);
			} else {
				btnBajaEmpleado.setEnabled(false);
			}
		}
	}
}
