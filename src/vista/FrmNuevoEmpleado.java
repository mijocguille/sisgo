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

public class FrmNuevoEmpleado extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtLegajo;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private EmpleadoNuevoListener listener;
	
	/**
	 * Create the frame.
	 */
	public FrmNuevoEmpleado(EmpleadoNuevoListener pListener) {
		super();
		listener = pListener;
		setTitle("Nuevo Empleado");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 207);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLegajo = new JLabel("Legajo");
		lblLegajo.setBounds(10, 14, 46, 14);
		contentPane.add(lblLegajo);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 49, 46, 14);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(10, 86, 46, 14);
		contentPane.add(lblApellido);
		
		txtLegajo = new JTextField();
		txtLegajo.setBounds(95, 11, 86, 20);
		contentPane.add(txtLegajo);
		txtLegajo.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(95, 46, 329, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(95, 83, 329, 20);
		contentPane.add(txtApellido);
		txtApellido.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 124, 414, 2);
		contentPane.add(separator);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(335, 134, 89, 23);
		contentPane.add(btnCancelar);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(listener != null) {
					listener.onEmpleadoCreado(Integer.parseInt(txtLegajo.getText()), txtNombre.getText(), txtApellido.getText());
				}
				dispose();				
			}
		});
		btnAceptar.setBounds(236, 134, 89, 23);
		contentPane.add(btnAceptar);

	}


}
