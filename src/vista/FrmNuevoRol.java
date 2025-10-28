package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JSeparator;

public class FrmNuevoRol extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombreRol;
	private RolNuevoListener listener;
	
	/**
	 * Create the frame.
	 */
	public FrmNuevoRol(RolNuevoListener pListener) {
		super();
		listener = pListener;
		setTitle("Nuevo Rol");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 357, 127);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtNombreRol = new JTextField();
		txtNombreRol.setBounds(66, 8, 260, 20);
		contentPane.add(txtNombreRol);
		txtNombreRol.setColumns(10);
		
		JLabel lblNombreRol = new JLabel("Rol");
		lblNombreRol.setBounds(10, 11, 46, 14);
		contentPane.add(lblNombreRol);
				
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(237, 55, 89, 23);
		contentPane.add(btnCancelar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 42, 321, 2);
		contentPane.add(separator);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(listener != null) {
					listener.onRolCreado(txtNombreRol.getText());
				}
				dispose();
			}
		});
		btnAceptar.setBounds(140, 55, 89, 23);
		contentPane.add(btnAceptar);

	}
}
