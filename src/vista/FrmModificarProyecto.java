package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import operaciones.Proyecto;

public class FrmModificarProyecto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombreProyecto;
	private JTextField txtFechaInicio;
	private JTextField txtFechaFin;
	private JTextField txtPedidoAsociado;
	private JTextField txtNumeroProyecto;
	private ProyectoModificadoListener listener;
	

	/**
	 * Create the frame.
	 */
	public FrmModificarProyecto(Proyecto objProyecto, ProyectoModificadoListener pListener) {
		super();
		listener = pListener;
		
		setTitle("Modificar Proyecto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 542, 216);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreProyecto = new JLabel("Nombre del Proyecto");
		lblNombreProyecto.setBounds(10, 39, 129, 14);
		contentPane.add(lblNombreProyecto);
		
		JLabel lblFechaInicio = new JLabel("Inicio");
		lblFechaInicio.setBounds(10, 78, 46, 14);
		contentPane.add(lblFechaInicio);
		
		JLabel lblFechaFin = new JLabel("Fin ");
		lblFechaFin.setBounds(232, 78, 46, 14);
		contentPane.add(lblFechaFin);
		
		txtNombreProyecto = new JTextField();
		txtNombreProyecto.setText(objProyecto.getNombreProyecto());
		txtNombreProyecto.setBounds(149, 36, 372, 20);
		contentPane.add(txtNombreProyecto);
		txtNombreProyecto.setColumns(10);
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
		String fechaInicio = formatter.format(objProyecto.getFechaEstimadaInicio());
		
		txtFechaInicio = new JTextField();
		txtFechaInicio.setText(fechaInicio);
		txtFechaInicio.setBounds(66, 75, 86, 20);
		contentPane.add(txtFechaInicio);
		txtFechaInicio.setColumns(10);
		
		String fechaFin = formatter.format(objProyecto.getFechaFin());
		txtFechaFin = new JTextField();
		txtFechaFin.setText(fechaFin);
		txtFechaFin.setBounds(288, 75, 86, 20);
		contentPane.add(txtFechaFin);
		txtFechaFin.setColumns(10);
		
		JLabel lblPedidoAsociado = new JLabel("Pedido Asociado");
		lblPedidoAsociado.setBounds(10, 114, 130, 14);
		contentPane.add(lblPedidoAsociado);
		
		txtPedidoAsociado = new JTextField();
		txtPedidoAsociado.setEditable(false);
		txtPedidoAsociado.setBounds(150, 111, 241, 20);
		contentPane.add(txtPedidoAsociado);
		txtPedidoAsociado.setColumns(10);
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.setBounds(401, 110, 115, 23);
		contentPane.add(btnSeleccionar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 139, 521, 14);
		contentPane.add(separator);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(432, 144, 89, 23);
		contentPane.add(btnCancelar);
		
		JButton btnAceptar = new JButton("Aceptar ");
		btnAceptar.addActionListener(e -> {
            if (listener != null) {
            	DateTimeFormatter formatterLocal = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate localDate = LocalDate.parse(txtFechaInicio.getText(), formatterLocal);
                Date fechaInicioL = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                localDate = LocalDate.parse(txtFechaFin.getText(), formatterLocal);
                Date fechaFinL = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                listener.onProyectoModificado(
                	Integer.parseInt(txtNumeroProyecto.getText()),
                    txtNombreProyecto.getText(),
                    fechaInicioL,
                    fechaFinL,
                    Integer.parseInt(txtPedidoAsociado.getText())
                );
            }
            dispose();
        });
		btnAceptar.setBounds(333, 144, 89, 23);
		contentPane.add(btnAceptar);
		
		JLabel lblNumeroProyecto = new JLabel("Número de Proyecto");
		lblNumeroProyecto.setBounds(10, 11, 129, 14);
		contentPane.add(lblNumeroProyecto);
		
		txtNumeroProyecto = new JTextField();
		txtNumeroProyecto.setText(String.valueOf(objProyecto.getNumeroProyecto()));
		txtNumeroProyecto.setEditable(false);
		txtNumeroProyecto.setBounds(149, 5, 86, 20);
		contentPane.add(txtNumeroProyecto);
		txtNumeroProyecto.setColumns(10);

	}

}
