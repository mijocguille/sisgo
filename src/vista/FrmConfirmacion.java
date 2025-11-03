package vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmConfirmacion extends JFrame {

	private static final long serialVersionUID = 1L;
	private ConfirmacionListener listener;

	/**
	 * Create the frame.
	 */
	public FrmConfirmacion(String texto, ConfirmacionListener pListener) {
		listener = pListener;
		setTitle("Solicitud de Confirmación");
		setBounds(100, 100, 450, 129);
		getContentPane().setLayout(null);
		
		JButton btnNo = new JButton("No");
		btnNo.setBounds(218, 61, 89, 23);
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().add(btnNo);
		
		JButton btnSi = new JButton("Sí");
		btnSi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listener.onConfirmar(true);
				dispose();
			}
		});
		btnSi.setBounds(119, 61, 89, 23);
		getContentPane().add(btnSi);
		
		JLabel lblTexto = new JLabel("");
		lblTexto.setText(texto);
		lblTexto.setVerticalAlignment(SwingConstants.TOP);
		lblTexto.setBounds(10, 11, 414, 39);
		getContentPane().add(lblTexto);
		this.setLocationRelativeTo(null); 
	}
}
