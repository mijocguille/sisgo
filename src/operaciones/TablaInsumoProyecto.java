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
			boolean resultado = false;
			Statement st = this.db.getConnection().createStatement();
			String query = "insert into insumo_proyecto (idInsumo, numeroProyecto, cantidad) ";
			query += "values("+ objIP.getIdInsumo() +"," + objIP.getNumeroProyecto() +","+ objIP.getCantidad()+")";
		    if (st.execute(query)) {
		    	resultado = true;
		    } 
			
			return resultado;
			
		} catch(SQLException e) {
			System.out.println("Error al ejecutar agregarInsumoProyecto." + e.getMessage()); 
			return false;
		}
	}
	
	public boolean quitarInsumoProyecto(InsumoProyecto objIP) {
		
		try {
			boolean resultado = false;
			Statement st = this.db.getConnection().createStatement();
			String query = "delete from insumo_proyecto where idInsumo = " + objIP.getIdInsumo() + " and numeroProyecto = " + objIP.getNumeroProyecto();
		
		    if (st.execute(query)) {
		    	resultado = true;
		    } 
			
			return resultado;
			
		} catch(SQLException e) {
			System.out.println("Error al ejecutar quitarInsumoProyecto." + e.getMessage()); 
			return false;
		}
	}
	

	public boolean verificarCantidadDisponible(InsumoProyecto objIP) {
		
		try {
			boolean resultado = false;
			Statement st = this.db.getConnection().createStatement();
			String query = "select idInsumo ";
			query += "from insumo i ";
			query += "where i.idInsumo = " + objIP.getIdInsumo() + " and (i.cantidadStock - ((select sum(ip.cantidad) ";
			query += "from proyecto p inner join insumo_proyecto ip on p.numeroProyecto = ip.numeroProyecto ";
			query += "where now() between p.fechaEstimadaInicio and p.fechaFin and ";
			query += "ip.idInsumo = i.idInsumo) + " + objIP.getCantidad() + ")) >= 0 ";
		
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
