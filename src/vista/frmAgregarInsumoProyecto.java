package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmAgregarInsumoProyecto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmAgregarInsumoProyecto frame = new frmAgregarInsumoProyecto();
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
	public frmAgregarInsumoProyecto() {
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
		
		textField = new JTextField();
		textField.setBounds(66, 8, 309, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(289, 33, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnSeleccionar = new JButton("Seleccionar");
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
		btnAceptar.setBounds(302, 71, 89, 23);
		contentPane.add(btnAceptar);

	}
}
