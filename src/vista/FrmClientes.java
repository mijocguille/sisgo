package vista;


import javax.swing.JFrame;
import javax.swing.JTable;

import entidades.Cliente;
import entidades.ControladorCliente;
import sistema.BaseDatos;
import sistema.Util;

import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmClientes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable tblClientes;
	private ControladorCliente ctrlCliente;
	private SeleccionListener listener;
	private JButton btnSeleccionar;
	private JButton btnAgregarCliente;
	private JButton btnModificarCliente;
	private JButton btnBajaCliente;
	
	/**
	 * Create the frame.
	 */
	public FrmClientes(BaseDatos db, boolean esSeleccion, SeleccionListener pListener) {
		
		super();
		ctrlCliente = new ControladorCliente(db);
		listener = pListener;
		
		setTitle("Listado de Clientes");
		setBounds(100, 100, 736, 412);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 700, 267);
		getContentPane().add(scrollPane);
		
		tblClientes = new JTable();
		scrollPane.setViewportView(tblClientes);
		tblClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cargarClientes();
		
		btnBajaCliente = new JButton("Baja");
		btnBajaCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idSeleccionado = Integer.parseInt(tblClientes.getModel().getValueAt(tblClientes.getSelectedRow(),0).toString());
				if( idSeleccionado > 0)	{
					FrmConfirmacion frmConfirmacion = new FrmConfirmacion("¿Seguro que desea dar de baja al cliente?",new ConfirmacionListener() {
					    @Override
					    public void onConfirmar(boolean resultado) {
					        if(resultado) {
						        if(ctrlCliente.darBajaCliente(idSeleccionado)){
						        	cargarClientes();
						        } 
					        }
					    }
					});
					frmConfirmacion.setAlwaysOnTop(true);
					frmConfirmacion.setVisible(true);
				}
			}
		});
		btnBajaCliente.setBounds(618, 289, 89, 23);
		getContentPane().add(btnBajaCliente);
		
		btnModificarCliente = new JButton("Editar");
		btnModificarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int idSeleccionado = Integer.parseInt(tblClientes.getModel().getValueAt(tblClientes.getSelectedRow(),0).toString());
				if( idSeleccionado > 0)	{
					Cliente objCli = ctrlCliente.getTblCliente().obtenerCliente(idSeleccionado);
					FrmModificarCliente frmEdit = new FrmModificarCliente(objCli, new ClienteModificadoListener() {
					    @Override
					    public void onClienteModificado(int idCliente, String razonSocial, String cuit, String direccion, String telefono) {
					        Cliente objCli = new Cliente();
					        objCli.setIdCliente(idCliente);
					        objCli.setRazonSocial(razonSocial);
					        objCli.setCuit(cuit);
					        objCli.setDireccion(direccion);
					        objCli.setTelefono(telefono);
					        
					        if(ctrlCliente.modificarCliente(objCli)){
					        	cargarClientes();
					        }
					    }
					});
					frmEdit.setAlwaysOnTop(true);
					frmEdit.setVisible(true);
				}
			}
		});
		btnModificarCliente.setBounds(519, 289, 89, 23);
		getContentPane().add(btnModificarCliente);
		
		btnAgregarCliente = new JButton("Alta");
		btnAgregarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmNuevoCliente frmNuevo = new FrmNuevoCliente(new ClienteNuevoListener() {
				    @Override
				    public void onClienteCreado(String razonSocial, String cuit, String direccion, String telefono) {
				        Cliente objCli = new Cliente();
				        objCli.setRazonSocial(razonSocial);
				        objCli.setCuit(cuit);
				        objCli.setDireccion(direccion);
				        objCli.setTelefono(telefono);
				        objCli.setIdUsuario(FrmMain.idUsuarioLogueado);
				        int idCliente = ctrlCliente.crearCliente(objCli);
				        if(idCliente > 0) {
				        	cargarClientes();
				        }
				    }
				});
				frmNuevo.setAlwaysOnTop(true);
				frmNuevo.setVisible(true);
			}
		});
		btnAgregarCliente.setBounds(420, 	289, 89, 23);
		getContentPane().add(btnAgregarCliente);
		
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
			public void actionPerformed(ActionEvent e) {
				int idSeleccion = Integer.parseInt(tblClientes.getModel().getValueAt(tblClientes.getSelectedRow(),0).toString());
				if(idSeleccion > 0) {
					listener.onSeleccion(idSeleccion);
					dispose();
				}
			}
		});
		btnSeleccionar.setBounds(10, 289, 114, 23);
		getContentPane().add(btnSeleccionar);
		tblClientes.getColumnModel().getColumn(1).setPreferredWidth(245);
		tblClientes.getColumnModel().getColumn(3).setPreferredWidth(152);
		
		gestionarBotones(esSeleccion);
		this.setLocationRelativeTo(null);
		
		tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	gestionarBotonBaja();
		    }
		});
		
	}
	
	private void cargarClientes() {
		tblClientes.removeAll();
		tblClientes.setModel(ctrlCliente.listarClientes());			
	}
	
	private void gestionarBotones(boolean esSeleccion) {
		if(tblClientes.getModel().getRowCount() == 0) {
			btnSeleccionar.setEnabled(false);
			btnModificarCliente.setEnabled(false);
			btnBajaCliente.setEnabled(false);		
		} else {
			if(esSeleccion) {
				btnSeleccionar.setVisible(true);
				btnModificarCliente.setVisible(false);
				btnBajaCliente.setVisible(false);
			}
			tblClientes.setRowSelectionInterval(0, 0);
			gestionarBotonBaja();
		}
	}
	
	private void gestionarBotonBaja() {
		int idSeleccionado = Integer.parseInt(tblClientes.getModel().getValueAt(tblClientes.getSelectedRow(),0).toString());
		if( idSeleccionado > 0)	{
			Cliente obj = ctrlCliente.getTblCliente().obtenerCliente(idSeleccionado);
			String fechaBaja = Util.obtenerFechaFormateada(obj.getFechaBaja());
			if(fechaBaja == "") {
				btnBajaCliente.setEnabled(true);
			} else {
				btnBajaCliente.setEnabled(false);
			}
		}
	}
	
}
