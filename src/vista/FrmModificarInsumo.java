package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import recursos.Insumo;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmModificarInsumo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtIdInsumo;
	private JTextField txtDescripcion;
	private JTextField txtStock;
	private InsumoModificadoListener listener;

	/**
	 * Create the frame.
	 */
	public FrmModificarInsumo(Insumo objInsumo, InsumoModificadoListener pListener) {
		super();
		listener = pListener;
		
		setTitle("Modificar Insumo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 168);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdInsumo = new JLabel("Id");
		lblIdInsumo.setBounds(10, 11, 70, 14);
		contentPane.add(lblIdInsumo);
		
		JLabel lblDescrpcion = new JLabel("Descripción");
		lblDescrpcion.setBounds(10, 36, 70, 14);
		contentPane.add(lblDescrpcion);
		
		JLabel lblStock = new JLabel("Stock");
		lblStock.setBounds(10, 61, 70, 14);
		contentPane.add(lblStock);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(335, 99, 89, 23);
		contentPane.add(btnCancelar);
		
		
		txtIdInsumo = new JTextField();
		txtIdInsumo.setEditable(false);
		txtIdInsumo.setBounds(90, 8, 86, 20);
		contentPane.add(txtIdInsumo);
		txtIdInsumo.setColumns(10);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(90, 33, 334, 20);
		contentPane.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		txtStock = new JTextField();
		txtStock.setBounds(90, 58, 86, 20);
		contentPane.add(txtStock);
		txtStock.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 86, 414, 2);
		contentPane.add(separator);
		
		txtIdInsumo.setText(String.valueOf(objInsumo.getIdInsumo()));
		txtDescripcion.setText(objInsumo.getDescripcionInsumo());
		txtStock.setText(String.valueOf(objInsumo.getCantidadStock()));
		
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(listener != null) {
					listener.onInsumoModificado(objInsumo.getIdInsumo(), txtDescripcion.getText(), Integer.parseInt(txtStock.getText()));
				}
				dispose();
				
			}
		});
		btnAceptar.setBounds(236, 99, 89, 23);
		contentPane.add(btnAceptar);
		this.setLocationRelativeTo(null); 
	}
}
