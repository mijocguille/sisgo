package vista;


import javax.swing.JFrame;
import javax.swing.JTable;

import entidades.Cliente;
import entidades.ControladorCliente;
import sistema.BaseDatos;

import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;

public class FrmClientes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private ControladorCliente ctrlCliente;
	private SeleccionListener listener;
	
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
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cargarClientes();
		
		JButton btnBajaCliente = new JButton("Baja");
		btnBajaCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int idSeleccionado = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(),0).toString());
				if( idSeleccionado > 0)	{
					FrmConfirmacion frmConfirmacion = new FrmConfirmacion("¿Seguro que desea dar de baja al cliente?",new ConfirmacionListener() {
					    @Override
					    public void onConfirmar(boolean resultado) {
					        if(resultado) {
					        	Cliente objCli = ctrlCliente.getTblCliente().obtenerCliente(idSeleccionado);
					        	objCli.setFechaBaja(new Date());
						        if(ctrlCliente.darBajaCliente(objCli)){
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
		
		JButton btnModificarCliente = new JButton("Editar");
		btnModificarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int idSeleccionado = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(),0).toString());
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
		
		JButton btnAgregarCliente = new JButton("Alta");
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
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.setVisible(false);
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idSeleccion = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(),0).toString());
				if(idSeleccion > 0) {
					listener.onSeleccion(idSeleccion);
					dispose();
				}
			}
		});
		btnSeleccionar.setBounds(10, 289, 114, 23);
		getContentPane().add(btnSeleccionar);
		table.getColumnModel().getColumn(1).setPreferredWidth(245);
		table.getColumnModel().getColumn(3).setPreferredWidth(152);
		
		if(esSeleccion) {
			btnSeleccionar.setVisible(true);
			btnModificarCliente.setVisible(false);
			btnBajaCliente.setVisible(false);
		}
		
	}
	
	private void cargarClientes() {
		table.removeAll();
		table.setModel(ctrlCliente.listarClientes());	
			
	}
}
