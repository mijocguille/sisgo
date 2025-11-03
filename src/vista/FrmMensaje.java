package vista;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmMensaje extends JDialog {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the frame.
	 */
	public FrmMensaje(String texto) {
		super();
		setTitle("Atención!!");
		setBounds(100, 100, 450, 129);
		getContentPane().setLayout(null);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnAceptar.setBounds(119, 61, 89, 23);
		getContentPane().add(btnAceptar);
		
		JLabel lblTexto = new JLabel("");
		lblTexto.setText(texto);
		lblTexto.setVerticalAlignment(SwingConstants.TOP);
		lblTexto.setBounds(10, 11, 414, 39);
		getContentPane().add(lblTexto);
		this.setLocationRelativeTo(null); 
	}
}
