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

import operaciones.Presupuesto;

public class FrmModificarPresupuesto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNumeroPresupuesto;
	private JTextField txtImportePresupuestado;
	private JTextField txtDiasValidez;
	private PresupuestoModificadoListener listener;

	/**
	 * Create the frame.
	 */
	public FrmModificarPresupuesto(Presupuesto objPresupuesto, PresupuestoModificadoListener pListener) {
		super();
		listener = pListener;
		setTitle("Modificando Presupuesto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 357, 154);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtNumeroPresupuesto = new JTextField();
		txtNumeroPresupuesto.setText(String.valueOf(objPresupuesto.getNumeroPresupuesto()));
		txtNumeroPresupuesto.setEditable(false);
		txtNumeroPresupuesto.setBounds(245, 11, 86, 20);
		contentPane.add(txtNumeroPresupuesto);
		txtNumeroPresupuesto.setColumns(10);
		
		txtImportePresupuestado = new JTextField();
		txtImportePresupuestado.setText(String.valueOf(objPresupuesto.getImportePresupuestado()));
		txtImportePresupuestado.setBounds(196, 39, 135, 20);
		contentPane.add(txtImportePresupuestado);
		txtImportePresupuestado.setColumns(10);
		
		JLabel lblNumeroPresupuesto = new JLabel("Número de Presupuesto");
		lblNumeroPresupuesto.setBounds(10, 14, 121, 14);
		contentPane.add(lblNumeroPresupuesto);
		
		JLabel lblImporte = new JLabel("Importe");
		lblImporte.setBounds(148, 42, 46, 14);
		contentPane.add(lblImporte);
		
		
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
		
		JLabel lblDiasValidez = new JLabel("Días Validez");
		lblDiasValidez.setBounds(10, 42, 66, 14);
		contentPane.add(lblDiasValidez);
		
		txtDiasValidez = new JTextField();
		txtDiasValidez.setText(String.valueOf(objPresupuesto.getDiasValidez()));
		txtDiasValidez.setBounds(90, 39, 53, 20);
		contentPane.add(txtDiasValidez);
		txtDiasValidez.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(listener != null) {
					listener.onPresupuestoModificado(objPresupuesto.getNumeroPresupuesto(), Integer.parseInt(txtDiasValidez.getText()), Double.parseDouble(txtImportePresupuestado.getText()));
				}
				dispose();
			}
		});
		btnAceptar.setBounds(143, 86, 89, 23);
		contentPane.add(btnAceptar);
		this.setLocationRelativeTo(null); 
	}

}
