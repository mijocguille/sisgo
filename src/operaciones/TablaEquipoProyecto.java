package operaciones;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import sistema.BaseDatos;

public class TablaEquipoProyecto {
	
	private BaseDatos db;
	
	public TablaEquipoProyecto (BaseDatos objDb) {
		
		this.db = objDb;
	}

	
	public ArrayList<EquipoProyecto> obtenerEquiposProyecto(int numeroProyecto) {
		
		try {
			
			ArrayList<EquipoProyecto> colEquipos = new ArrayList<EquipoProyecto>();
			Statement st = this.db.getConnection().createStatement();
			String query = "select idEquipo, numeroProyecto, cantidad ";
			query += "from equipo_proyecto where numeroProyecto = " + numeroProyecto;
			ResultSet rs = st.executeQuery(query);
			
			if(rs != null) {
			    while(rs.next()) {
			    	EquipoProyecto objEP = new EquipoProyecto();
			    	objEP.setIdEquipo(rs.getInt("idEquipo"));
			    	objEP.setNumeroProyecto(rs.getInt("numeroProyecto"));
			    	objEP.setCantidad(rs.getInt("cantidad"));
			    	colEquipos.add(objEP);
			    }
			    rs.close();
			}
			return colEquipos;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar obtenerEquiposProyecto." + e.getMessage()); 
			return null;
		}
		
	}
	
	public boolean agregarEquipoProyecto(EquipoProyecto objEP) {
		
		try {
			boolean resultado = false;
			Statement st = this.db.getConnection().createStatement();
			String query = "insert into equipo_proyecto (idEquipo, numeroProyecto, cantidad) ";
			query += "values("+ objEP.getIdEquipo() +"," + objEP.getNumeroProyecto() +","+ objEP.getCantidad()+")";
		    if (st.execute(query)) {
		    	resultado = true;
		    } 
			
			return resultado;
			
		} catch(SQLException e) {
			System.out.println("Error al ejecutar agregarEquipoProyecto." + e.getMessage()); 
			return false;
		}
	}
	
	public boolean quitarEquipoProyecto(EquipoProyecto objEP) {
		
		try {
			boolean resultado = false;
			Statement st = this.db.getConnection().createStatement();
			String query = "delete from equipo_proyecto where idEquipo = " + objEP.getIdEquipo() + " and numeroProyecto = " + objEP.getNumeroProyecto();
		
		    if (st.execute(query)) {
		    	resultado = true;
		    } 
			
			return resultado;
			
		} catch(SQLException e) {
			System.out.println("Error al ejecutar quitarEquipoProyecto." + e.getMessage()); 
			return false;
		}
	}
	

	public boolean verificarCantidadDisponible(EquipoProyecto objEP) {
		
		try {
			boolean resultado = false;
			Statement st = this.db.getConnection().createStatement();
			String query = "select idEquipo ";
			query += "from equipo e ";
			query += "where e.idEquipo = " + objEP.getIdEquipo() + " and (e.cantidadEquipos - ((select sum(ep.cantidad) ";
			query += "from proyecto p inner join equipo_proyecto ep on p.numeroProyecto = ep.numeroProyecto ";
			query += "where now() between p.fechaEstimadaInicio and p.fechaFin and ";
			query += "ep.idEquipo = e.idEquipo) + " + objEP.getCantidad() + ")) >= 0 ";
		
			ResultSet rs = st.executeQuery(query);
			
			if(rs != null) {
			    if(rs.next()) {
			    	resultado = true;
			    }
			    rs.close();
			}			
			return resultado;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar verificarCantidadDisponible." + e.getMessage()); 
			return false;
		}
	
	}

}
