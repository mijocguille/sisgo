package entidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import sistema.BaseDatos;

public class TablaPermiso {
	
	private BaseDatos db;
	
	public TablaPermiso (BaseDatos objDb) {
		
		this.db = objDb;
	}

	
	public Permiso obtenerPermiso(int idAccion, int idRol) {
		
		try {
			
			Permiso objPermiso = null;
			Statement st = this.db.getConnection().createStatement();
			String query = "select idAccion, idRol from permiso where idAccion = " + idAccion + " and idRol = " + idRol;
			ResultSet rs = st.executeQuery(query);
			
			if(rs != null) {
				objPermiso = new Permiso();
			    if(rs.next()) {
			    	objPermiso.setIdAccion(rs.getInt("idAccion"));
			    	objPermiso.setIdRol(rs.getInt("idRol"));
			    }
			    rs.close();
			}
			return objPermiso;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar obtenerPermiso." + e.getMessage()); 
			return null;
		}
		
	}
	
	public ArrayList<Permiso> obtenerPermisos(int idRol) {
		
		try {
			
			ArrayList<Permiso> colPermisos = new ArrayList<Permiso>();
			Statement st = this.db.getConnection().createStatement();
			String query = "select idAccion, idRol from permiso where idRol = " + idRol;
			ResultSet rs = st.executeQuery(query);
			
			if(rs != null) {
			    while(rs.next()) {
			    	Permiso objPermiso = new Permiso();
			    	objPermiso.setIdAccion(rs.getInt("idAccion"));
			    	objPermiso.setIdRol(rs.getInt("idRol"));
			    	colPermisos.add(objPermiso);
			    }
			    rs.close();
			}
			return colPermisos;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar obtenerPermisos." + e.getMessage()); 
			return null;
		}
		
	}

}
