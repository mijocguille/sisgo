package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmNuevoProyecto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmNuevoProyecto frame = new FrmNuevoProyecto();
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
	public FrmNuevoProyecto() {
		setTitle("Crear Proyecto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 542, 186);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreProyecto = new JLabel("Nombre del Proyecto");
		lblNombreProyecto.setBounds(10, 11, 129, 14);
		contentPane.add(lblNombreProyecto);
		
		JLabel lblFechaInicio = new JLabel("Inicio");
		lblFechaInicio.setBounds(10, 50, 46, 14);
		contentPane.add(lblFechaInicio);
		
		JLabel lblFechaFin = new JLabel("Fin ");
		lblFechaFin.setBounds(232, 50, 46, 14);
		contentPane.add(lblFechaFin);
		
		textField = new JTextField();
		textField.setBounds(149, 8, 372, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(66, 47, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(288, 47, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblPedidoAsociado = new JLabel("Pedido Asociado");
		lblPedidoAsociado.setBounds(10, 86, 130, 14);
		contentPane.add(lblPedidoAsociado);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(150, 83, 241, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.setBounds(401, 82, 115, 23);
		contentPane.add(btnSeleccionar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 111, 521, 14);
		contentPane.add(separator);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(432, 116, 89, 23);
		contentPane.add(btnCancelar);
		
		JButton btnAceptar = new JButton("Aceptar ");
		btnAceptar.setBounds(333, 116, 89, 23);
		contentPane.add(btnAceptar);

	}
}
