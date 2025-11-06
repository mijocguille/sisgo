package vista;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JToolBar;

import entidades.ControladorUsuario;
import sistema.BaseDatos;

public class FrmMain extends JFrame {
	
	private static final long serialVersionUID = 1L;
	static BaseDatos db = new BaseDatos();
	static int idUsuarioLogueado = -1;
	static FrmClientes wndClientes;
	static FrmPedidos wndPedidos;
	static FrmInsumos wndInsumos;
	static FrmEquipos wndEquipos;
	static FrmEmpleados wndEmpleados;
	static FrmProyectos wndProyectos;
	static FrmUsuarios wndUsuarios;
	static FrmRoles wndRoles;
	
	private JButton btnClientes;
	private JButton btnPedidos;
	private JButton btnInsumos;
	private JButton btnProyectos;
	private JButton btnEquipos;
	private JButton btnEmpleados;
	private JButton btnUsuarios;
	private JButton btnRoles;
	
	/**
	 * Create the application.
	 */
	public FrmMain() {	
		super();
		iniciarSesion();
		initialize();
		this.setLocationRelativeTo(null); 
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setTitle("SisGo - Version 0.0.1");
		this.setFont(new Font("Calibri", Font.PLAIN, 11));
		this.setBounds(100, 100, 800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 784, 63);
		this.getContentPane().add(toolBar);
		
		btnClientes = new JButton("Clientes");
		btnClientes.setFont(new Font("Tahoma", Font.PLAIN, 16));
		toolBar.add(btnClientes);
		
		btnPedidos = new JButton("Pedidos");
		btnPedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmPedidos frmPedidos = FrmMain.crearWndPedidos();
				frmPedidos.setAlwaysOnTop(true);
				frmPedidos.setVisible(true);
			}
		});
		btnPedidos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		toolBar.add(btnPedidos);
		
		btnInsumos = new JButton("Insumos");
		btnInsumos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmInsumos frmInsumos = FrmMain.crearWndInsumos();
				frmInsumos.setAlwaysOnTop(true);
				frmInsumos.setVisible(true);
			}
		});
		btnInsumos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		toolBar.add(btnInsumos);
		
		btnProyectos = new JButton("Proyectos");
		btnProyectos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmProyectos frmProyectos = FrmMain.crearWndProyectos();
				frmProyectos.setAlwaysOnTop(true);
				frmProyectos.setVisible(true);
			}
		});
		btnProyectos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		toolBar.add(btnProyectos);
		
		btnEquipos = new JButton("Equipos");
		btnEquipos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmEquipos frmEquipos = FrmMain.crearWndEquipos();
				frmEquipos.setAlwaysOnTop(true);
				frmEquipos.setVisible(true);
			}
		});
		btnEquipos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		toolBar.add(btnEquipos);
		
		btnEmpleados = new JButton("Empleados");
		btnEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmEmpleados frmEmpleados = FrmMain.crearWndEmpleados();
				frmEmpleados.setAlwaysOnTop(true);
				frmEmpleados.setVisible(true);
			}
		});
		btnEmpleados.setFont(new Font("Tahoma", Font.PLAIN, 16));
		toolBar.add(btnEmpleados);
		
		btnUsuarios = new JButton("Usuarios");
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmUsuarios frmUsuarios = FrmMain.crearWndUsuarios();
				frmUsuarios.setAlwaysOnTop(true);
				frmUsuarios.setVisible(true);
			}
		});
		btnUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 16));
		toolBar.add(btnUsuarios);
		
		btnRoles = new JButton("Roles");
		btnRoles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmRoles frmRoles = FrmMain.crearWndRoles();
				frmRoles.setAlwaysOnTop(true);
				frmRoles.setVisible(true);
			}
		});
		btnRoles.setFont(new Font("Tahoma", Font.PLAIN, 16));
		toolBar.add(btnRoles);
		
		JButton btnCerrarSesion= new JButton("Cerrar Sesión");
		btnCerrarSesion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		toolBar.add(btnCerrarSesion);
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmConfirmacion frmConfirmacion = new FrmConfirmacion("¿Seguro que desea cerrar la sesión?",new ConfirmacionListener() {
				    @Override
				    public void onConfirmar(boolean resultado) {
				        if(resultado) {
					        idUsuarioLogueado = -1;
					        iniciarSesion();
				        }
				    }
				});
				frmConfirmacion.setAlwaysOnTop(true);
				frmConfirmacion.setVisible(true);
			}
		});
		
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 16));
		toolBar.add(btnSalir);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(EXIT_ON_CLOSE);
			}
		});
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				FrmClientes frmClientes = FrmMain.crearWndClientes();
				frmClientes.setAlwaysOnTop(true);
				frmClientes.setVisible(true);
					
			}
		});
	}
	
	private void iniciarSesion() {
		FrmLogin frmLogin = new FrmLogin(db);
		frmLogin.setModal(true);
		frmLogin.setAlwaysOnTop(true);
		frmLogin.setVisible(true);
		
		
	}
	
	public static FrmClientes crearWndClientes() {
		
		if(wndClientes == null) {
			wndClientes = new FrmClientes(db,false,null);
		}
		return wndClientes;
	}
	
	public static FrmPedidos crearWndPedidos() {
		
		if(wndPedidos == null) {
			wndPedidos = new FrmPedidos(db,false,null);
		}
		return wndPedidos;
	}

	public static FrmInsumos crearWndInsumos() {

		if(wndInsumos == null) {
			wndInsumos = new FrmInsumos(db,false,null);
		}
		return wndInsumos;
	}

	public static FrmProyectos crearWndProyectos() {
		
		if(wndProyectos == null) {
			wndProyectos = new FrmProyectos(db);
		}
		return wndProyectos;
	}
	
	public static FrmEquipos crearWndEquipos() {
		
		if(wndEquipos == null) {
			wndEquipos = new FrmEquipos(db,false,null);
		}
		return wndEquipos;
	}
	
	public static FrmEmpleados crearWndEmpleados() {
		
		if(wndEmpleados == null) {
			wndEmpleados = new FrmEmpleados(db,false,null);
		}
		
		return wndEmpleados;
	}
	
	public static FrmUsuarios crearWndUsuarios() {
		
		if(wndUsuarios == null) {
			wndUsuarios = new FrmUsuarios(db);
		}
		
		return wndUsuarios;
	}

	public static FrmRoles crearWndRoles() {
	
		if(wndRoles == null) {
			wndRoles = new FrmRoles(db);
		}
		
		return wndRoles;
	}
	
}
