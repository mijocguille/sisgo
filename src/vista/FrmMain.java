package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JToolBar;

public class FrmMain {

	private JFrame frmMain;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMain window = new FrmMain();
					window.frmMain.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrmMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMain = new JFrame();
		frmMain.setResizable(false);
		frmMain.setAlwaysOnTop(true);
		frmMain.setTitle("SisGo - Version 0.0.1");
		frmMain.setFont(new Font("Calibri", Font.PLAIN, 11));
		frmMain.setBounds(100, 100, 800, 600);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMain.getContentPane().setLayout(null);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 784, 63);
		frmMain.getContentPane().add(toolBar);
		
		JButton btnClientes = new JButton("Clientes");
		btnClientes.setFont(new Font("Tahoma", Font.PLAIN, 16));
		toolBar.add(btnClientes);
		
		JButton btnPedidos = new JButton("Pedidos");
		btnPedidos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		toolBar.add(btnPedidos);
		
		JButton btnInsumos = new JButton("Insumos");
		btnInsumos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		toolBar.add(btnInsumos);
		
		JButton btnProyectos = new JButton("Proyectos");
		btnProyectos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		toolBar.add(btnProyectos);
		
		JButton btnEquipos = new JButton("Equipos");
		btnEquipos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		toolBar.add(btnEquipos);
		
		JButton btnEmpleados = new JButton("Empleados");
		btnEmpleados.setFont(new Font("Tahoma", Font.PLAIN, 16));
		toolBar.add(btnEmpleados);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 16));
		toolBar.add(btnSalir);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMain.dispose();
			}
		});
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmClientes wndClientes = new FrmClientes();
				wndClientes.show();
				
			}
		});
	}
}
