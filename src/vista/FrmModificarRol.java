package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class FrmModificarRol extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtIdRol;
	private JTextField txtNombreRol;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmModificarRol frame = new FrmModificarRol();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmModificarRol() {
		setTitle("Modificando Rol");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 357, 154);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtIdRol = new JTextField();
		txtIdRol.setEditable(false);
		txtIdRol.setBounds(66, 11, 86, 20);
		contentPane.add(txtIdRol);
		txtIdRol.setColumns(10);
		
		txtNombreRol = new JTextField();
		txtNombreRol.setBounds(66, 42, 260, 20);
		contentPane.add(txtNombreRol);
		txtNombreRol.setColumns(10);
		
		JLabel lblIdRol = new JLabel("Id");
		lblIdRol.setBounds(10, 11, 46, 14);
		contentPane.add(lblIdRol);
		
		JLabel lblNombreRol = new JLabel("Rol");
		lblNombreRol.setBounds(10, 45, 46, 14);
		contentPane.add(lblNombreRol);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(143, 86, 89, 23);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(242, 86, 89, 23);
		contentPane.add(btnCancelar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 73, 321, 2);
		contentPane.add(separator);

	}
}
