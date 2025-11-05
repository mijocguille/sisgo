package operaciones;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import sistema.BaseDatos;

public class TablaInsumoProyecto {
	private BaseDatos db;
	
	public TablaInsumoProyecto (BaseDatos objDb) {
		
		this.db = objDb;
	}
	
	public InsumoProyecto obtenerInsumoProyecto(int numeroProyecto, int idInsumo) {
		
		try {
			
			InsumoProyecto objEP = null;
			Statement st = this.db.getConnection().createStatement();
			String query = "select idInsumo, numeroProyecto ";
			query += "from insumo_proyecto where numeroProyecto = " + numeroProyecto + " and idInsumo = " + idInsumo;
			ResultSet rs = st.executeQuery(query);
			
			if(rs != null) {
				objEP = new InsumoProyecto();
			    if(rs.next()) {
			    	objEP.setNumeroProyecto(rs.getInt("numeroProyecto"));
			    	objEP.setIdInsumo(rs.getInt("idInsumo"));
			    }
			    rs.close();
			}
			return objEP;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar obtenerInsumoProyecto." + e.getMessage()); 
			return null;
		}
		
	}

	public ArrayList<InsumoProyecto> obtenerInsumosProyecto(int numeroProyecto) {
		
		try {
			
			ArrayList<InsumoProyecto> colInsumos = new ArrayList<InsumoProyecto>();
			Statement st = this.db.getConnection().createStatement();
			String query = "select idInsumo, numeroProyecto, cantidad ";
			query += "from insumo_proyecto where numeroProyecto = " + numeroProyecto;
			ResultSet rs = st.executeQuery(query);
			
			if(rs != null) {
			    while(rs.next()) {
			    	InsumoProyecto objIP = new InsumoProyecto();
			    	objIP.setIdInsumo(rs.getInt("idInsumo"));
			    	objIP.setNumeroProyecto(rs.getInt("numeroProyecto"));
			    	objIP.setCantidad(rs.getInt("cantidad"));
			    	colInsumos.add(objIP);
			    }
			    rs.close();
			}
			return colInsumos;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar obtenerInsumosProyecto." + e.getMessage()); 
			return null;
		}
		
	}
	
	public boolean agregarInsumoProyecto(InsumoProyecto objIP) {
		
		try {
			Statement st = this.db.getConnection().createStatement();
			String query = "insert into insumo_proyecto (idInsumo, numeroProyecto, cantidad) ";
			query += "values("+ objIP.getIdInsumo() +"," + objIP.getNumeroProyecto() +","+ objIP.getCantidad()+")";
			st.execute(query);
			
			return true;
			
		} catch(SQLException e) {
			System.out.println("Error al ejecutar agregarInsumoProyecto." + e.getMessage()); 
			return false;
		}
	}
	
	public boolean quitarInsumoProyecto(InsumoProyecto objIP) {
		
		try {
			Statement st = this.db.getConnection().createStatement();
			String query = "delete from insumo_proyecto where idInsumo = " + objIP.getIdInsumo() + " and numeroProyecto = " + objIP.getNumeroProyecto();
		
		    st.execute(query);
			
			return true;
			
		} catch(SQLException e) {
			System.out.println("Error al ejecutar quitarInsumoProyecto." + e.getMessage()); 
			return false;
		}
	}
	

	

}
