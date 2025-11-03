package vista;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import operaciones.ControladorPedido;
import operaciones.Pedido;
import sistema.BaseDatos;

public class FrmPedidos extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTable tblPedidos;
	private ControladorPedido ctrlPedidos;
	private SeleccionListener listener;
	private JButton btnSeleccionar;
	private JButton btnAgregarPedido;
	private JButton btnModificarPedido;
	private JButton btnAnularPedido;

	/**
	 * Create the frame.
	 */
	public FrmPedidos(BaseDatos db, boolean esSeleccion, SeleccionListener pListener) {
		super();
		listener = pListener;
		ctrlPedidos = new ControladorPedido(db);
		setTitle("Listado de Pedidos");
		setBounds(100, 100, 736, 412);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 700, 267);
		getContentPane().add(scrollPane);
		
		tblPedidos = new JTable();
		scrollPane.setViewportView(tblPedidos);
		tblPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cargarPedidos();
		tblPedidos.getColumnModel().getColumn(1).setPreferredWidth(100);
		tblPedidos.getColumnModel().getColumn(2).setPreferredWidth(185);
		tblPedidos.getColumnModel().getColumn(3).setPreferredWidth(152);
		tblPedidos.getColumnModel().getColumn(4).setPreferredWidth(110);
		
		btnAnularPedido = new JButton("Anular");
		btnAnularPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int idSeleccionado = Integer.parseInt(tblPedidos.getModel().getValueAt(tblPedidos.getSelectedRow(),0).toString());
				if( idSeleccionado > 0)	{
					FrmConfirmacion frmConfirmacion = new FrmConfirmacion("¿Seguro que desea anular el Pedido?",new ConfirmacionListener() {
					    @Override
					    public void onConfirmar(boolean resultado) {
					        if(resultado) {
					            if(ctrlPedidos.anularPedido(idSeleccionado)){
						        	cargarPedidos();
						        } 
					        }
					    }
					});
					frmConfirmacion.setAlwaysOnTop(true);
					frmConfirmacion.setVisible(true);
				}
			}
		});
		btnAnularPedido.setBounds(618, 289, 89, 23);
		getContentPane().add(btnAnularPedido);
		
		btnModificarPedido = new JButton("Editar");
		btnModificarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int idSeleccionado = Integer.parseInt(tblPedidos.getModel().getValueAt(tblPedidos.getSelectedRow(),0).toString());
				if( idSeleccionado > 0)	{
					Pedido objPed = ctrlPedidos.getTblPedido().obtenerPedido(idSeleccionado);
					FrmModificarPedido frmEdit = new FrmModificarPedido(db, objPed, new PedidoModificadoListener() {
						
					    @Override
					    public void onPedidoModificado(int numeroPedido, int idCliente, String detallePedido, String caracteristicasPedido) {
					        Pedido objPedido = new Pedido();
					        
					        objPedido.setNumeroPedido(numeroPedido);
					        objPedido.setIdCliente(idCliente);
					        objPedido.setDetallePedido(detallePedido);
					        objPedido.setCaracteristicasPedido(caracteristicasPedido);
					        
					        if(ctrlPedidos.modificarPedido(objPedido)){
					        	cargarPedidos();
					        }
					    }
					});
					frmEdit.setAlwaysOnTop(true);
					frmEdit.setVisible(true);
				}
			}
		});
		btnModificarPedido.setBounds(519, 289, 89, 23);
		getContentPane().add(btnModificarPedido);
		
		btnAgregarPedido = new JButton("Alta");
		btnAgregarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmNuevoPedido frmNuevo = new FrmNuevoPedido(db, new PedidoNuevoListener() {
				    @Override
				    public void onPedidoCreado(int idCliente, String detallePedido, String caracteristicasPedido) {
				        Pedido objPed = new Pedido();
				        objPed.setIdCliente(idCliente);
				        objPed.setDetallePedido(detallePedido);
				        objPed.setCaracteristicasPedido(caracteristicasPedido);
				        objPed.setIdUsuario(FrmMain.idUsuarioLogueado);
				        int numeroPedido = ctrlPedidos.crearPedido(objPed);
				        if(numeroPedido > 0) {
				        	cargarPedidos();
				        }
				    }
				});
				frmNuevo.setAlwaysOnTop(true);
				frmNuevo.setVisible(true);
			}
		});
		btnAgregarPedido.setBounds(420, 289, 89, 23);
		getContentPane().add(btnAgregarPedido);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnCerrar.setBounds(618, 337, 89, 23);
		getContentPane().add(btnCerrar);
		
		btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idSeleccion = Integer.parseInt(tblPedidos.getModel().getValueAt(tblPedidos.getSelectedRow(),0).toString());
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
	}
	
	private void cargarPedidos() {
		tblPedidos.removeAll();
		tblPedidos.setModel(ctrlPedidos.listarPedidos());				
	}
	
	private void gestionarBotones(boolean esSeleccion) {
		if(tblPedidos.getModel().getRowCount() == 0) {
			btnSeleccionar.setEnabled(false);
			btnModificarPedido.setEnabled(false);
			btnAnularPedido.setEnabled(false);		
		} else {
			if(esSeleccion) {
				btnSeleccionar.setVisible(true);
				btnModificarPedido.setVisible(false);
				btnAnularPedido.setVisible(false);
			}
			tblPedidos.setRowSelectionInterval(0, 0);
		}
	}
}
