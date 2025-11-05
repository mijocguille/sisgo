package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import operaciones.ControladorInsumoProyecto;
import recursos.Insumo;
import sistema.BaseDatos;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmAgregarInsumoProyecto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtInsumo;
	private JTextField txtCantidad;
	private ControladorInsumoProyecto ctrlInsumoProyecto;
	private InsumoProyectoNuevoListener listener;
	private int idInsumo;

	/**
	 * Create the frame.
	 */
	public FrmAgregarInsumoProyecto(int numeroProyecto, BaseDatos db, InsumoProyectoNuevoListener pListener) {
		super();
		ctrlInsumoProyecto = new ControladorInsumoProyecto(db);
		listener = pListener;
		setTitle("Agregar Insumo a Proyecto NN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 512, 140);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInsumo = new JLabel("Insumo");
		lblInsumo.setBounds(10, 11, 46, 14);
		contentPane.add(lblInsumo);
		
		JLabel lblCantidad = new JLabel("Cantidad a Agregar");
		lblCantidad.setBounds(10, 36, 109, 14);
		contentPane.add(lblCantidad);
		
		txtInsumo = new JTextField();
		txtInsumo.setEditable(false);
		txtInsumo.setBounds(66, 8, 309, 20);
		contentPane.add(txtInsumo);
		txtInsumo.setColumns(10);
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(289, 33, 86, 20);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmInsumos frmSeleccion = new FrmInsumos(db,true, new SeleccionListener() {
					
					@Override
					public void onSeleccion(int id) {
						Insumo i = ctrlInsumoProyecto.getTblInsumo().obtenerInsumo(id);
						txtInsumo.setText(i.getDescripcionInsumo());
						idInsumo = id;
					}
				});				
				frmSeleccion.setAlwaysOnTop(true);
				frmSeleccion.setVisible(true);
			}
		});
		btnSeleccionar.setBounds(385, 7, 102, 23);
		contentPane.add(btnSeleccionar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 61, 487, 7);
		contentPane.add(separator);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(398, 71, 89, 23);
		contentPane.add(btnCancelar);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listener != null) {
					listener.onInsumoProyectoCreado(idInsumo, Integer.parseInt(txtCantidad.getText()));
				}
				dispose();
			}
		});
		btnAceptar.setBounds(302, 71, 89, 23);
		contentPane.add(btnAceptar);
		this.setLocationRelativeTo(null); 
	}
}
