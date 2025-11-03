package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import operaciones.ControladorPresupuesto;
import operaciones.Presupuesto;
import sistema.BaseDatos;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmPresupuestos extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblPresupuestos;
	private JLabel lblNewLabel;
	private ControladorPresupuesto ctrlPresupuesto;
	private JButton btnNuevo;
	private JButton btnEditar;
	private JButton btnEliminar;

	/**
	 * Create the frame.
	 */
	public FrmPresupuestos(BaseDatos db, int numeroProyecto) {
		super();
		ctrlPresupuesto = new ControladorPresupuesto(db);
		
		setTitle("Visualizando Presupuestos");
		setModal(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 269);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 538, 108);
		contentPane.add(scrollPane);
		
		tblPresupuestos = new JTable();
		cargarPresupuestos(numeroProyecto);
		tblPresupuestos.getColumnModel().getColumn(1).setPreferredWidth(122);
		scrollPane.setViewportView(tblPresupuestos);
		
		lblNewLabel = new JLabel("Presupuestos del Proyecto \"nn\"");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 11, 194, 14);
		contentPane.add(lblNewLabel);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int numeroPresupuesto = Integer.parseInt(tblPresupuestos.getModel().getValueAt(tblPresupuestos.getSelectedRow(),0).toString());
				if( numeroPresupuesto > 0)	{
					FrmConfirmacion frmConfirmacion = new FrmConfirmacion("¿Seguro que desea dar de baja el presupuesto "+ numeroPresupuesto +"?",new ConfirmacionListener() {
					    @Override
					    public void onConfirmar(boolean resultado) {
					        if(resultado) {
						        if(ctrlPresupuesto.eliminarPresupuesto(numeroProyecto,numeroPresupuesto)){
						        	cargarPresupuestos(numeroProyecto);
						        } 
					        }
					    }
					});
					frmConfirmacion.setAlwaysOnTop(true);
					frmConfirmacion.setVisible(true);
				}
			}
		});
		btnEliminar.setBounds(459, 150, 89, 23);
		contentPane.add(btnEliminar);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int numeroPresupuesto = Integer.parseInt(tblPresupuestos.getModel().getValueAt(tblPresupuestos.getSelectedRow(),0).toString());
				if( numeroPresupuesto > 0)	{
					Presupuesto objPresupuesto = ctrlPresupuesto.getTblPresupuesto().obtenerPresupuesto(numeroProyecto, numeroPresupuesto);
					FrmModificarPresupuesto frmEdit = new FrmModificarPresupuesto(objPresupuesto, new PresupuestoModificadoListener() {
					    @Override
					    public void onPresupuestoModificado(int numeroPresupuesto, int diasValidez, double importePresupuestado) {
					        Presupuesto objPre = new Presupuesto();
					        objPre.setNumeroProyecto(numeroProyecto);
					        objPre.setNumeroPresupuesto(numeroPresupuesto);
					        objPre.setDiasValidez(diasValidez);
					        objPre.setImportePresupuestado(importePresupuestado);
					        if(ctrlPresupuesto.modificarPresupuesto(objPre)){
					        	cargarPresupuestos(numeroProyecto);
					        }
					    }
					});
					frmEdit.setAlwaysOnTop(true);
					frmEdit.setVisible(true);
				}
			}
		});		
		btnEditar.setBounds(360, 150, 89, 23);
		contentPane.add(btnEditar);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmNuevoPresupuesto frmNuevo = new FrmNuevoPresupuesto(new PresupuestoNuevoListener() {
				    @Override
				    public void onPresupuestoCreado(int diasValidez, double importePresupuestado) {
				    	Presupuesto objPre = new Presupuesto();
				        objPre.setNumeroProyecto(numeroProyecto);
				        objPre.setDiasValidez(diasValidez);
				        objPre.setImportePresupuestado(importePresupuestado);
				        if(ctrlPresupuesto.crearPresupuesto(objPre)){
				        	cargarPresupuestos(numeroProyecto);
				        }
				    }
				});
				frmNuevo.setAlwaysOnTop(true);
				frmNuevo.setVisible(true);
			}
		});
		
		btnNuevo.setBounds(261, 150, 89, 23);
		contentPane.add(btnNuevo);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setBounds(459, 203, 89, 23);
		contentPane.add(btnCerrar);
		this.setLocationRelativeTo(null); 
		gestionarBotones();
	}
	
	private void cargarPresupuestos(int numeroProyecto) {
		tblPresupuestos.removeAll();
		tblPresupuestos.setModel(ctrlPresupuesto.listarPresupuestos(numeroProyecto));
	}
	
	private void gestionarBotones() {
		if(tblPresupuestos.getModel().getRowCount() == 0) {
			btnEliminar.setEnabled(false);
			btnEditar.setEnabled(false);		
		} else {
			tblPresupuestos.setRowSelectionInterval(0, 0);
		}
	}

}
