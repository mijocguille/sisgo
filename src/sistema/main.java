package sistema;

import java.awt.EventQueue;
import java.util.ArrayList;

import entidades.Accion;
import entidades.Permiso;
import entidades.TablaAccion;
import entidades.TablaPermiso;

public class main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BaseDatos db = new BaseDatos();
					TablaAccion tablaAccion = new TablaAccion(db);
					TablaPermiso tablaPermiso = new TablaPermiso(db);
					
					ArrayList<Permiso> col = tablaPermiso.obtenerPermisos(1);
					
					col.forEach(permiso -> System.out.println(tablaAccion.obtenerAccion(permiso.getIdAccion()).getNombreAccion()));
					

					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
