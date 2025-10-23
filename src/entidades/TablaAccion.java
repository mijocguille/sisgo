package entidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
}
