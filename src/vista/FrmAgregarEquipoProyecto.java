package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import operaciones.ControladorEquipoProyecto;
import recursos.Equipo;
import sistema.BaseDatos;
import sistema.Util;

public class FrmAgregarEquipoProyecto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEquipo;
	private JTextField txtCantidad;
	private ControladorEquipoProyecto ctrlEquipoProyecto;
	private EquipoProyectoNuevoListener listener;
	private int idEquipo;
	private FrmMensaje frmMsg;


	/**
	 * Create the frame.
	 */
	public FrmAgregarEquipoProyecto(int numeroProyecto, BaseDatos db, EquipoProyectoNuevoListener pListener) {
		super();
		ctrlEquipoProyecto = new ControladorEquipoProyecto(db);
		listener = pListener;
		setTitle("Agregar Equipo a Proyecto NN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 512, 140);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEquipo = new JLabel("Equipo");
		lblEquipo.setBounds(10, 11, 46, 14);
		contentPane.add(lblEquipo);
		
		JLabel lblCantidad = new JLabel("Cantidad a Agregar");
		lblCantidad.setBounds(10, 36, 109, 14);
		contentPane.add(lblCantidad);
		
		txtEquipo = new JTextField();
		txtEquipo.setEditable(false);
		txtEquipo.setBounds(66, 8, 309, 20);
		contentPane.add(txtEquipo);
		txtEquipo.setColumns(10);
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(289, 33, 86, 20);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmEquipos frmSeleccion = new FrmEquipos(db,true, new SeleccionListener() {
					
					@Override
					public void onSeleccion(int id) {
						Equipo e = ctrlEquipoProyecto.getTblEquipo().obtenerEquipo(id);
						txtEquipo.setText(e.getDescripcionEquipo());
						idEquipo = id;
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
				if(validarInformacionVentana()) {
					if(listener != null) {
						listener.onEquipoProyectoCreado(idEquipo, Integer.parseInt(txtCantidad.getText()));
						dispose();
					}
				} else {
					frmMsg.setAlwaysOnTop(true);
					frmMsg.setVisible(true);
				}
			}
		});
		btnAceptar.setBounds(302, 71, 89, 23);
		contentPane.add(btnAceptar);
		this.setLocationRelativeTo(null); 
	}

	private boolean validarInformacionVentana() {

		boolean valido = true;
		String textoMensaje = "";
		if (txtEquipo.getText().length() == 0) {
			textoMensaje = "Debe seleccionar un equipo para agregar al proyecto";
			valido = false;
		} else if (txtCantidad.getText().length() == 0) {
			textoMensaje = "Debe ingresar una cantidad a agregar";
			valido = false;
		} else if (!Util.esEntero(txtCantidad.getText())) {
			textoMensaje = "La cantidad debe ser un número";
			valido = false;
		} else if (Integer.parseInt(txtCantidad.getText()) <= 0) {
			textoMensaje = "La cantidad debe ser mayor a 0";
			valido = false;
		}

		frmMsg = new FrmMensaje(textoMensaje);
				
		return valido;
	}
}
