package entidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import sistema.BaseDatos;

public class TablaUsuario {
	
private BaseDatos db;
	
	public TablaUsuario (BaseDatos objDb) {
		
		this.db = objDb;
	}

	
	public Usuario obtenerUsuario(int idUsuario) {
		
		try {
			
			Usuario objUsuario = null;
			Statement st = this.db.getConnection().createStatement();
			String query = "select idUsuario, nombreUsuario, claveUsuario, descripcionUsuario, fechaAlta, fechaBaja, idRol ";
			query += "from usuario where idUsuario = " + idUsuario;
			ResultSet rs = st.executeQuery(query);
			
			if(rs != null) {
				objUsuario = new Usuario();
			    if(rs.next()) {
			    	objUsuario.setIdUsuario(rs.getInt("idUsuario"));
			    	objUsuario.setNombreUsuario(rs.getString("nombreUsuario"));
			    	objUsuario.setClaveUsuario(rs.getString("claveUsuario"));
			    	objUsuario.setDescripcionUsuario(rs.getString("descripcionUsuario"));
			    	objUsuario.setFechaAlta(rs.getDate("fechaAlta"));
			    	objUsuario.setFechaBaja(rs.getDate("fechaBaja"));
			    	objUsuario.setIdRol(rs.getInt("idRol"));
			    }
			    rs.close();
			}
			return objUsuario;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar obtenerUsuario." + e.getMessage()); 
			return null;
		}
		
	}
	
	public ArrayList<Usuario> obtenerUsuarios() {
		
		try {
			
			ArrayList<Usuario> colUsuarios = new ArrayList<Usuario>();
			Statement st = this.db.getConnection().createStatement();
			String query = "select idUsuario, nombreUsuario, claveUsuario, descripcionUsuario, fechaAlta, fechaBaja, idRol ";
			query += "from usuario ";
			ResultSet rs = st.executeQuery(query);
			
			if(rs != null) {
			    while(rs.next()) {
			    	Usuario objUsuario = new Usuario();
			    	objUsuario.setIdUsuario(rs.getInt("idUsuario"));
			    	objUsuario.setNombreUsuario(rs.getString("nombreUsuario"));
			    	objUsuario.setClaveUsuario(rs.getString("claveUsuario"));
			    	objUsuario.setDescripcionUsuario(rs.getString("descripcionUsuario"));
			    	objUsuario.setFechaAlta(rs.getDate("fechaAlta"));
			    	objUsuario.setFechaBaja(rs.getDate("fechaBaja"));
			    	objUsuario.setIdRol(rs.getInt("idRol"));
			    	colUsuarios.add(objUsuario);
			    }
			    rs.close();
			}
			return colUsuarios;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar obtenerUsuarios." + e.getMessage()); 
			return null;
		}
		
	}
	
	public int altaUsuario(Usuario objUsuario) {
		
		try {
			
			int idUsuario = 0;
			Statement st = this.db.getConnection().createStatement();
			String query = "insert into usuario (nombreUsuario, claveUsuario, descripcionUsuario, fechaAlta, idRol) ";
			query += "values('"+ objUsuario.getNombreUsuario() +"','" + objUsuario.getClaveUsuario();
			query += "','"+ objUsuario.getDescripcionUsuario()+"',now(), "+ String.valueOf(objUsuario.getIdRol()) +")";
			
		    st.execute(query);
	    	query = "select max(idUsuario) from usuario";
	    	ResultSet rs = st.executeQuery(query);
			
			if(rs != null) {
			    if (rs.next()) {
			    	idUsuario = rs.getInt(1);
			    }
			}
			
			return idUsuario;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar altaUsuario." + e.getMessage()); 
			return -1;
		}
	}
	
	public boolean modificarUsuario(Usuario objUsuario) {
		
		try {
			
			Statement st = this.db.getConnection().createStatement();
			String query = "update usuario ";
			query += "set nombreUsuario = '"+ objUsuario.getNombreUsuario() +"', ";
			if(objUsuario.getClaveUsuario().length() > 0)
			{
				query += "claveUsuario = '"+ objUsuario.getClaveUsuario() +"', ";
			}
			query += "descripcionUsuario = '"+ objUsuario.getDescripcionUsuario() +"' ";
			query += "idRol = "+ String.valueOf(objUsuario.getIdRol()) +" ";
			query += "where idUsuario = " + objUsuario.getIdUsuario();
			
		    st.execute(query);
		
			return true;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar modificarUsuario." + e.getMessage()); 
			return false;
		}
	}

	public boolean bajaUsuario(int idUsuario) {
		
		try {
			
			Statement st = this.db.getConnection().createStatement();
			String query = "update usuario ";
			query += "set fechaBaja = now() ";
			query += "where idUsuario = " + idUsuario;
			
			st.execute(query);
			
			return true;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar bajaUsuario." + e.getMessage()); 
			return false;
		}
	
	}
	
	public Usuario validarUsuario(String nombreUsuario, String claveUsuario) {
		try {
			Usuario objUsuario = null;
			Statement st = this.db.getConnection().createStatement();
			String query = "select idUsuario, nombreUsuario, claveUsuario, descripcionUsuario, fechaAlta, fechaBaja, idRol ";
			query += "from usuario where nombreUsuario = '" + nombreUsuario + "' and claveUsuario = '" + claveUsuario + "' and fechaBaja is null ";
			ResultSet rs = st.executeQuery(query);
			
			if(rs != null) {
				objUsuario = new Usuario();
			    if(rs.next()) {
			    	objUsuario.setIdUsuario(rs.getInt("idUsuario"));
			    	objUsuario.setNombreUsuario(rs.getString("nombreUsuario"));
			    	objUsuario.setClaveUsuario(rs.getString("claveUsuario"));
			    	objUsuario.setDescripcionUsuario(rs.getString("descripcionUsuario"));
			    	objUsuario.setFechaAlta(rs.getDate("fechaAlta"));
			    	objUsuario.setFechaBaja(rs.getDate("fechaBaja"));
			    	objUsuario.setIdRol(rs.getInt("idRol"));
			    }
			    rs.close();
			}
			return objUsuario;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar validarUsuario." + e.getMessage()); 
			return null;
		}
	}


}
