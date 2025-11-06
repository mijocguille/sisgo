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

import sistema.Util;

public class FrmNuevoEquipo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDescripcion;
	private JTextField txtCantidad;
	private EquipoNuevoListener listener;
	private FrmMensaje frmMsg;

	/**
	 * Create the frame.
	 */
	public FrmNuevoEquipo(EquipoNuevoListener pListener) {
		super();
		listener = pListener;
		setTitle("Nuevo Equipo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 146);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDescrpcion = new JLabel("Descripción");
		lblDescrpcion.setBounds(10, 14, 70, 14);
		contentPane.add(lblDescrpcion);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(10, 39, 70, 14);
		contentPane.add(lblCantidad);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(335, 77, 89, 23);
		contentPane.add(btnCancelar);
		
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(90, 11, 334, 20);
		contentPane.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(90, 36, 86, 20);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 64, 414, 2);
		contentPane.add(separator);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validarInformacionVentana()) {
					if(listener!=null) {
						listener.onEquipoCreado(txtDescripcion.getText(), Integer.parseInt(txtCantidad.getText()));
					}
					dispose();
				} else {
					frmMsg.setAlwaysOnTop(true);
					frmMsg.setVisible(true);
				}
			}
		});
		btnAceptar.setBounds(236, 77, 89, 23);
		contentPane.add(btnAceptar);
		this.setLocationRelativeTo(null); 
	}
	
	private boolean validarInformacionVentana() {

		boolean valido = true;
		String textoMensaje = "";
		if (txtDescripcion.getText().length() == 0) {
			textoMensaje = "Debe proporcionar una descripción para el equipo";
			valido = false;
		} 
		else if(txtCantidad.getText().length() == 0) {
			textoMensaje = "Debe ingresar la cantidad";
			valido = false;
		} else if (!Util.esEntero(txtCantidad.getText())) {
			textoMensaje = "La cantidad debe ser un número";
			valido = false;
		}

		frmMsg = new FrmMensaje(textoMensaje);
				
		return valido;
	}

}
