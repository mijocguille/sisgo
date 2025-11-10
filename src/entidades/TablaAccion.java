package entidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import sistema.BaseDatos;

public class TablaAccion {
	
	private BaseDatos db;
	
	public TablaAccion (BaseDatos objDb) {
		
		this.db = objDb;
	}

	
	public Accion obtenerAccion(int idAccion) {
		
		try {
			
			Accion objAccion = null;
			Statement st = this.db.getConnection().createStatement();
			String query = "select idAccion, nombreAccion from accion where idAccion = " + idAccion;
			ResultSet rs = st.executeQuery(query);
			
			if(rs != null) {
				objAccion = new Accion();
			    if(rs.next()) {
			    	objAccion.setIdAccion(rs.getInt("idAccion"));
			    	objAccion.setNombreAccion(rs.getString("nombreAccion"));
			    }
			    rs.close();
			}
			return objAccion;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar obtenerAccion." + e.getMessage()); 
			return null;
		}
		
	}
	
	public ArrayList<Accion> obtenerAcciones() {
		
		try {
			
			ArrayList<Accion> colAcciones = new ArrayList<Accion>();
			Statement st = this.db.getConnection().createStatement();
			String query = "select idAccion, nombreAccion from accion ";
			ResultSet rs = st.executeQuery(query);
			Accion objAccion = null;
			if(rs != null) {
			    while(rs.next()) {
			    	objAccion = new Accion();
			    	objAccion.setIdAccion(rs.getInt("idAccion"));
			    	objAccion.setNombreAccion(rs.getString("nombreAccion"));
			    
			    	colAcciones.add(objAccion);
			    }
			    rs.close();
			}
			return colAcciones;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar obtenerAcciones." + e.getMessage()); 
			return null;
		}
		
	}
	
}
