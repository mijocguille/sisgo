package sistema;

import java.awt.EventQueue;
import java.util.ArrayList;

import entidades.Accion;
import entidades.Permiso;
import entidades.TablaAccion;
import entidades.TablaCliente;
import entidades.TablaPermiso;
import entidades.TablaRol;
import entidades.TablaUsuario;
import operaciones.TablaEmpleadoProyecto;
import operaciones.TablaEquipoProyecto;
import operaciones.TablaInsumoProyecto;
import operaciones.TablaPedido;
import operaciones.TablaPresupuesto;
import operaciones.TablaProyecto;
import recursos.TablaEmpleado;
import recursos.TablaEquipo;
import recursos.TablaInsumo;
import vista.FrmMain;

public class main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMain frmMain = new FrmMain();
					frmMain.setVisible(true);			
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
