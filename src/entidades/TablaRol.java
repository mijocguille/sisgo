package entidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import sistema.BaseDatos;

public class TablaRol {
	
	private BaseDatos db;
	
	public TablaRol (BaseDatos objDb) {
		
		this.db = objDb;
	}

	
	public Rol obtenerRol(int idRol) {
		
		try {
			
			Rol objRol = null;
			Statement st = this.db.getConnection().createStatement();
			String query = "select idRol, nombreRol, fechaAlta, fechaBaja from rol where idRol = " + idRol;
			ResultSet rs = st.executeQuery(query);
			
			if(rs != null) {
				objRol = new Rol();
			    if(rs.next()) {
			    	objRol.setIdRol(rs.getInt("idRol"));
			    	objRol.setNombreRol(rs.getString("nombreRol"));
			    	objRol.setFechaAlta(rs.getDate("fechaAlta"));
			    	objRol.setFechaBaja(rs.getDate("fechaBaja"));
			    }
			    rs.close();
			}
			return objRol;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar obtenerRol." + e.getMessage()); 
			return null;
		}
		
	}
	
	public Rol obtenerRol(String rol) {
		
		try {
			
			Rol objRol = null;
			Statement st = this.db.getConnection().createStatement();
			String query = "select idRol, nombreRol, fechaAlta, fechaBaja from rol where nombreRol = '" + rol + "'";
			ResultSet rs = st.executeQuery(query);
			
			if(rs != null) {
				objRol = new Rol();
			    if(rs.next()) {
			    	objRol.setIdRol(rs.getInt("idRol"));
			    	objRol.setNombreRol(rs.getString("nombreRol"));
			    	objRol.setFechaAlta(rs.getDate("fechaAlta"));
			    	objRol.setFechaBaja(rs.getDate("fechaBaja"));
			    }
			    rs.close();
			}
			return objRol;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar obtenerRol." + e.getMessage()); 
			return null;
		}
		
	}
	
	public ArrayList<Rol> obtenerRoles() {
		
		try {
			
			ArrayList<Rol> colRoles = new ArrayList<Rol>();
			Statement st = this.db.getConnection().createStatement();
			String query = "select idRol, nombreRol, fechaAlta, fechaBaja from rol";
			ResultSet rs = st.executeQuery(query);
			
			if(rs != null) {
			    while(rs.next()) {
			    	Rol objRol = new Rol();
			    	objRol.setIdRol(rs.getInt("idRol"));
			    	objRol.setNombreRol(rs.getString("nombreRol"));
			    	objRol.setFechaAlta(rs.getDate("fechaAlta"));
			    	objRol.setFechaBaja(rs.getDate("fechaBaja"));
			    	colRoles.add(objRol);
			    }
			    rs.close();
			}
			return colRoles;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar obtenerRoles." + e.getMessage()); 
			return null;
		}
		
	}
	
	public int altaRol(Rol objRol) {
		
		try {
			
			int idRol = 0;
			Statement st = this.db.getConnection().createStatement();
			String query = "insert into rol (nombreRol, fechaAlta) ";
			query += "values('"+ objRol.getNombreRol() +"',now())";
			
		    st.execute(query);
	    	query = "select max(idRol) from rol";
	    	ResultSet rs = st.executeQuery(query);
			
			if(rs != null) {
			    if (rs.next()) {
			    	idRol = rs.getInt(1);
			    }
			} 
			
			return idRol;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar altaRol." + e.getMessage()); 
			return -1;
		}
	}
	
	public boolean modificarRol(Rol objRol) {
		
		try {
		
			Statement st = this.db.getConnection().createStatement();
			String query = "update rol ";
			query += "set nombreRol = '"+ objRol.getNombreRol() +"' ";
			query += "where idRol = " + objRol.getIdRol();
			
			st.execute(query);
			
			return true;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar modificarRol." + e.getMessage()); 
			return false;
		}
	}

	public boolean bajaRol(int idRol) {
		
		try {
			
			Statement st = this.db.getConnection().createStatement();
			String query = "update rol ";
			query += "set fechaBaja = now() ";
			query += "where idRol = " + idRol;
			
			st.execute(query);
			
			return true;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar bajaRol." + e.getMessage()); 
			return false;
		}
	
	}

}
