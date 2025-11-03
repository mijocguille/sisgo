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

public class FrmNuevoInsumo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDescripcion;
	private JTextField txtStock;
	private InsumoNuevoListener listener;


	/**
	 * Create the frame.
	 */
	public FrmNuevoInsumo(InsumoNuevoListener pListener) {
		super();
		listener = pListener;
		
		setTitle("Nuevo Insumo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 148);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDescrpcion = new JLabel("Descripción");
		lblDescrpcion.setBounds(10, 14, 70, 14);
		contentPane.add(lblDescrpcion);
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setBounds(10, 39, 70, 14);
		contentPane.add(lblStock);
		
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
		
		txtStock = new JTextField();
		txtStock.setBounds(90, 36, 86, 20);
		contentPane.add(txtStock);
		txtStock.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 64, 414, 2);
		contentPane.add(separator);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(listener != null) {
					listener.onInsumoCreado(txtDescripcion.getText(), Integer.parseInt(txtStock.getText()));
				}
				dispose();
			}
		});
		btnAceptar.setBounds(236, 77, 89, 23);
		contentPane.add(btnAceptar);
		this.setLocationRelativeTo(null); 
	}

}
