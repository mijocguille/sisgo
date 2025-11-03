package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import entidades.ControladorUsuario;
import entidades.Usuario;
import sistema.BaseDatos;
import sistema.Util;

public class FrmUsuarios extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable tblUsuarios;
	private ControladorUsuario ctrlUsuario;
	private JButton btnAgregarUsuario;
	private JButton btnModificarUsuario;
	private JButton btnBajaUsuario;

	/**
	 * Create the frame.
	 */
	public FrmUsuarios(BaseDatos db) {
		super();
		ctrlUsuario = new ControladorUsuario(db);
		setTitle("Listado de Usuarios");
		setBounds(100, 100, 736, 412);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 700, 267);
		getContentPane().add(scrollPane);
		
		tblUsuarios = new JTable();
		scrollPane.setViewportView(tblUsuarios);
		tblUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cargarUsuarios();
		tblUsuarios.getColumnModel().getColumn(2).setPreferredWidth(322);
		
		btnBajaUsuario = new JButton("Baja");
		btnBajaUsuario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int idSeleccionado = Integer.parseInt(tblUsuarios.getModel().getValueAt(tblUsuarios.getSelectedRow(),0).toString());
				if( idSeleccionado > 0)	{
					FrmConfirmacion frmConfirmacion = new FrmConfirmacion("¿Seguro que desea dar de baja al usuario?",new ConfirmacionListener() {
					    @Override
					    public void onConfirmar(boolean resultado) {
					        if(resultado) {
						        if(ctrlUsuario.darBajaUsuario(idSeleccionado)){
						        	cargarUsuarios();
						        } 
					        }
					    }
					});
					frmConfirmacion.setAlwaysOnTop(true);
					frmConfirmacion.setVisible(true);
				}
			}
		});
		btnBajaUsuario.setBounds(618, 289, 89, 23);
		getContentPane().add(btnBajaUsuario);
		
		btnModificarUsuario = new JButton("Editar");
		btnModificarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int idSeleccionado = Integer.parseInt(tblUsuarios.getModel().getValueAt(tblUsuarios.getSelectedRow(),0).toString());
				if( idSeleccionado > 0)	{
					Usuario objUsu = ctrlUsuario.getTblUsuario().obtenerUsuario(idSeleccionado);
					FrmModificarUsuario frmEdit = new FrmModificarUsuario(db, objUsu, new UsuarioModificadoListener() {
					    @Override
					    public void onUsuarioModificado(int idUsuario, String nombreUsuario, String claveUsuario, String descripcionUsuario, int idRol) {
					        Usuario objUsuario = new Usuario();
					        objUsuario.setIdUsuario(idUsuario);
					        objUsuario.setNombreUsuario(nombreUsuario);
					        objUsuario.setDescripcionUsuario(descripcionUsuario);
					        if(claveUsuario.length() > 0) {
					        	String claveCifrada = ctrlUsuario.cifrar(claveUsuario); 
					        	objUsuario.setClaveUsuario(claveCifrada);
					        }
					        objUsuario.setIdRol(idRol);
					        if(ctrlUsuario.modificarUsuario(objUsuario)){
					        	cargarUsuarios();
					        }
					    }
					});
					frmEdit.setAlwaysOnTop(true);
					frmEdit.setVisible(true);
				}
			}
		});
		btnModificarUsuario.setBounds(519, 289, 89, 23);
		getContentPane().add(btnModificarUsuario);
		
		btnAgregarUsuario = new JButton("Alta");
		btnAgregarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmNuevoUsuario frmNuevo = new FrmNuevoUsuario(db, new UsuarioNuevoListener() {
				    @Override
				    public void onUsuarioCreado(String nombreUsuario, String claveUsuario, String descripcionUsuario, int idRol) {
				    	Usuario objUsuario = new Usuario();
				        objUsuario.setNombreUsuario(nombreUsuario);
				        objUsuario.setDescripcionUsuario(descripcionUsuario);
				        String claveCifrada = ctrlUsuario.cifrar(claveUsuario); 
				        objUsuario.setClaveUsuario(claveCifrada);
				        objUsuario.setIdRol(idRol);
				        int idUsuario = ctrlUsuario.darAltaUsuario(objUsuario);
				        if(idUsuario > 0){
				        	cargarUsuarios();
				        }
				    }
				});
				frmNuevo.setAlwaysOnTop(true);
				frmNuevo.setVisible(true);
			}
		});
		btnAgregarUsuario.setBounds(420, 289, 89, 23);
		getContentPane().add(btnAgregarUsuario);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnCerrar.setBounds(618, 337, 89, 23);
		getContentPane().add(btnCerrar);
		this.setLocationRelativeTo(null); 
		gestionarBotones();
		tblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	gestionarBotonBaja();
		    }
		});
	}
	
	private void cargarUsuarios() {
		tblUsuarios.removeAll();
		tblUsuarios.setModel(ctrlUsuario.listarUsuarios());
	};
	
	private void gestionarBotones() {
		if(tblUsuarios.getModel().getRowCount() == 0) {
			btnModificarUsuario.setEnabled(false);
			btnBajaUsuario.setEnabled(false);		
		} else {
			tblUsuarios.setRowSelectionInterval(0, 0);
			gestionarBotonBaja();
		}
	}
	
	private void gestionarBotonBaja() {
		int idSeleccionado = Integer.parseInt(tblUsuarios.getModel().getValueAt(tblUsuarios.getSelectedRow(),0).toString());
		if( idSeleccionado > 0)	{
			Usuario obj = ctrlUsuario.getTblUsuario().obtenerUsuario(idSeleccionado);
			String fechaBaja = Util.obtenerFechaFormateada(obj.getFechaBaja());
			if(fechaBaja == "") {
				btnBajaUsuario.setEnabled(true);
			} else {
				btnBajaUsuario.setEnabled(false);
			}
		}
	}
	
}

