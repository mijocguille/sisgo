package recursos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import sistema.BaseDatos;

public class TablaEquipo {

	private BaseDatos db;
	
	public TablaEquipo (BaseDatos objDb) {
		
		this.db = objDb;
	}

	
	public Equipo obtenerEquipo(int idEquipo) {
		
		try {
						
			Equipo objEquipo = null;
			Statement st = this.db.getConnection().createStatement();
			String query = "select idEquipo, descripcionEquipo, cantidadEquipos, fechaAlta, fechaBaja, idUsuario ";
			query += "from equipo where idEquipo = " + idEquipo;
			ResultSet rs = st.executeQuery(query);
			
			if(rs != null) {
				objEquipo = new Equipo();
			    if(rs.next()) {
			    	objEquipo.setIdEquipo(rs.getInt("idEquipo"));
			    	objEquipo.setDescripcionEquipo(rs.getString("descripcionEquipo"));
			    	objEquipo.setCantidadEquipos(rs.getInt("cantidadEquipos"));
			    	objEquipo.setFechaAlta(rs.getDate("fechaAlta"));
			    	objEquipo.setFechaBaja(rs.getDate("fechaBaja"));
			    	objEquipo.setIdUsuario(rs.getInt("idUsuario"));
			    }
			    rs.close();
			}
			return objEquipo;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar obtenerEquipo." + e.getMessage()); 
			return null;
		}
		
	}
	
	public ArrayList<Equipo> obtenerEquipos() {
		
		try {
			
			ArrayList<Equipo> colEquipos = new ArrayList<Equipo>();
			Statement st = this.db.getConnection().createStatement();
			String query = "select idEquipo, descripcionEquipo, cantidadEquipos, fechaAlta, fechaBaja, idUsuario ";
			query += "from equipo ";
			ResultSet rs = st.executeQuery(query);
			
			if(rs != null) {
			    while(rs.next()) {
			    	Equipo objEquipo = new Equipo();
			    	objEquipo.setIdEquipo(rs.getInt("idEquipo"));
			    	objEquipo.setDescripcionEquipo(rs.getString("descripcionEquipo"));
			    	objEquipo.setCantidadEquipos(rs.getInt("cantidadEquipos"));
			    	objEquipo.setFechaAlta(rs.getDate("fechaAlta"));
			    	objEquipo.setFechaBaja(rs.getDate("fechaBaja"));
			    	objEquipo.setIdUsuario(rs.getInt("idUsuario"));
			    	colEquipos.add(objEquipo);
			    }
			    rs.close();
			}
			return colEquipos;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar obtenerEquipos." + e.getMessage()); 
			return null;
		}
		
	}
	
	public int altaEquipo(Equipo objEquipo) {
		
		try {
			
			int idEquipo = 0;
			Statement st = this.db.getConnection().createStatement();
			String query = "insert into Equipo (descripcionEquipo, cantidadEquipos, fechaAlta, idUsuario) ";
			query += "values('"+ objEquipo.getDescripcionEquipo() +"'," + objEquipo.getCantidadEquipos() +", now(),"+ objEquipo.getIdUsuario() + ")";
			
		    st.execute(query);
	    	query = "select max(idEquipo) from equipo";
	    	ResultSet rs = st.executeQuery(query);
			
			if(rs != null) {
			    if (rs.next()) {
			    	idEquipo = rs.getInt(1);
			    }
			} 
			
			return idEquipo;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar altaEquipo." + e.getMessage()); 
			return -1;
		}
	}
	
	public boolean modificarEquipo(Equipo objEquipo) {
		
		try {
			
			boolean resultado = false;
			Statement st = this.db.getConnection().createStatement();
			String query = "update equipo ";
			query += "set cantidadEquipos = "+ objEquipo.getCantidadEquipos() +", ";
			query += "descripcionEquipo = '"+ objEquipo.getDescripcionEquipo() +"' ";
			query += "where idEquipo = " + objEquipo.getIdEquipo();
			
			st.execute(query);
			
			return true;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar modificarEquipo." + e.getMessage()); 
			return false;
		}
	}

	public boolean bajaEquipo(Equipo objEquipo) {
		
		try {
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String fechaBaja = formatter.format(objEquipo.getFechaBaja());
			
			Statement st = this.db.getConnection().createStatement();
			String query = "update equipo ";
			query += "set fechaBaja = '"+ fechaBaja +"' ";
			query += "where idEquipo = " + objEquipo.getIdEquipo();
			
			st.execute(query);
			
			return true;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar bajaEquipo." + e.getMessage()); 
			return false;
		}
	
	}


}
