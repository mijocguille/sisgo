package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import entidades.ControladorRol;
import entidades.Rol;
import sistema.BaseDatos;
import sistema.Util;

public class FrmRoles extends JFrame {
	
	
	private static final long serialVersionUID = 1L;
	private JTable tblRoles;
	private ControladorRol ctrlRol;
	private JButton btnAgregarRol;
	private JButton btnModificarRol;
	private JButton btnBajaRol;

	/**
	 * Create the frame.
	 */
	public FrmRoles(BaseDatos db) {
		super();
		ctrlRol = new ControladorRol(db);
		setTitle("Listado de Roles");
		setBounds(100, 100, 599, 412);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 551, 267);
		getContentPane().add(scrollPane);
		
		tblRoles = new JTable();
		scrollPane.setViewportView(tblRoles);
		tblRoles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cargarRoles();
		tblRoles.getColumnModel().getColumn(1).setPreferredWidth(397);
		
		btnBajaRol = new JButton("Baja");
		btnBajaRol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idSeleccionado = Integer.parseInt(tblRoles.getModel().getValueAt(tblRoles.getSelectedRow(),0).toString());
				if( idSeleccionado > 0)	{
					FrmConfirmacion frmConfirmacion = new FrmConfirmacion("¿Seguro que desea dar de baja al rol?",new ConfirmacionListener() {
					    @Override
					    public void onConfirmar(boolean resultado) {
					        if(resultado) {
						        if(ctrlRol.darBajaRol(idSeleccionado)){
						        	cargarRoles();
						        } 
					        }
					    }
					});
					frmConfirmacion.setAlwaysOnTop(true);
					frmConfirmacion.setVisible(true);
				}
			}
		});
		btnBajaRol.setBounds(464, 289, 89, 23);
		getContentPane().add(btnBajaRol);
		
		btnModificarRol = new JButton("Editar");
		btnModificarRol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int idSeleccionado = Integer.parseInt(tblRoles.getModel().getValueAt(tblRoles.getSelectedRow(),0).toString());
				if( idSeleccionado > 0)	{
					Rol objR = ctrlRol.getTblRol().obtenerRol(idSeleccionado);
					FrmModificarRol frmEdit = new FrmModificarRol(objR, new RolModificadoListener() {
					    @Override
					    public void onRolModificado(int idRol, String nombreRol) {
					        Rol objRol = new Rol();
					        objRol.setIdRol(idRol);
					        objRol.setNombreRol(nombreRol);
					     
					        if(ctrlRol.modificaRol(objRol)){
					        	cargarRoles();
					        }
					    }
					});
					frmEdit.setAlwaysOnTop(true);
					frmEdit.setVisible(true);
				}
			}
		});
		btnModificarRol.setBounds(365, 289, 89, 23);
		getContentPane().add(btnModificarRol);
		
		btnAgregarRol = new JButton("Alta");
		btnAgregarRol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmNuevoRol frmNuevo = new FrmNuevoRol(new RolNuevoListener() {
				    @Override
				    public void onRolCreado(String nombreRol) {
				        Rol objRol = new Rol();
				        objRol.setNombreRol(nombreRol);
				        int idRol = ctrlRol.darAltaRol(objRol);
				        if(idRol > 0) {
				        	cargarRoles();
				        }
				    }
				});
				frmNuevo.setAlwaysOnTop(true);
				frmNuevo.setVisible(true);
			}
		});
		btnAgregarRol.setBounds(266, 289, 89, 23);
		getContentPane().add(btnAgregarRol);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnCerrar.setBounds(464, 339, 89, 23);
		getContentPane().add(btnCerrar);
		this.setLocationRelativeTo(null); 
		gestionarBotones();
		tblRoles.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	gestionarBotonBaja();
		    }
		});
	}
	
	private void cargarRoles() {
		tblRoles.removeAll();
		tblRoles.setModel(ctrlRol.listarRoles());
	}
	
	private void gestionarBotones() {
		if(tblRoles.getModel().getRowCount() == 0) {
			btnModificarRol.setEnabled(false);
			btnBajaRol.setEnabled(false);		
		} else {
			tblRoles.setRowSelectionInterval(0, 0);
			gestionarBotonBaja();
		}
	}
	
	private void gestionarBotonBaja() {
		int idSeleccionado = Integer.parseInt(tblRoles.getModel().getValueAt(tblRoles.getSelectedRow(),0).toString());
		if( idSeleccionado > 0)	{
			Rol obj = ctrlRol.getTblRol().obtenerRol(idSeleccionado);
			String fechaBaja = Util.obtenerFechaFormateada(obj.getFechaBaja());
			if(fechaBaja == "") {
				btnBajaRol.setEnabled(true);
			} else {
				btnBajaRol.setEnabled(false);
			}
		}
	}

}
