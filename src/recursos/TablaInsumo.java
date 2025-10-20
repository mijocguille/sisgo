package recursos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import operaciones.InsumoProyecto;
import sistema.BaseDatos;

public class TablaInsumo {

	private BaseDatos db;
	
	public TablaInsumo (BaseDatos objDb) {
		
		this.db = objDb;
	}

	
	public Insumo obtenerInsumo(int idInsumo) {
		
		try {
						
			Insumo objInsumo = null;
			Statement st = this.db.getConnection().createStatement();
			String query = "select idInsumo, descripcionInsumo, cantidadStock, fechaAlta, fechaBaja, idUsuario ";
			query += "from insumo where idInsumo = " + idInsumo;
			ResultSet rs = st.executeQuery(query);
			
			if(rs != null) {
				objInsumo = new Insumo();
			    while(rs.next()) {
			    	objInsumo.setIdInsumo(rs.getInt("idInsumo"));
			    	objInsumo.setDescripcionInsumo(rs.getString("descripcionInsumo"));
			    	objInsumo.setCantidadStock(rs.getInt("cantidadStock"));
			    	objInsumo.setFechaAlta(rs.getDate("fechaAlta"));
			    	objInsumo.setFechaBaja(rs.getDate("fechaBaja"));
			    	objInsumo.setIdUsuario(rs.getInt("idUsuario"));
			    }
			    rs.close();
			}
			return objInsumo;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar obtenerInsumo." + e.getMessage()); 
			return null;
		}
		
	}
	
	public ArrayList<Insumo> obtenerInsumos() {
		
		try {
			
			ArrayList<Insumo> colInsumos = new ArrayList<Insumo>();
			Statement st = this.db.getConnection().createStatement();
			String query = "select idInsumo, descripcionInsumo, cantidadStock, fechaAlta, fechaBaja, idUsuario ";
			query += "from Insumo ";
			ResultSet rs = st.executeQuery(query);
			
			if(rs != null) {
			    while(rs.next()) {
			    	Insumo objInsumo = new Insumo();
			    	objInsumo.setIdInsumo(rs.getInt("idInsumo"));
			    	objInsumo.setDescripcionInsumo(rs.getString("descripcionInsumo"));
			    	objInsumo.setCantidadStock(rs.getInt("cantidadStock"));
			    	objInsumo.setFechaAlta(rs.getDate("fechaAlta"));
			    	objInsumo.setFechaBaja(rs.getDate("fechaBaja"));
			    	objInsumo.setIdUsuario(rs.getInt("idUsuario"));
			    	colInsumos.add(objInsumo);
			    }
			    rs.close();
			}
			return colInsumos;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar obtenerInsumos." + e.getMessage()); 
			return null;
		}
		
	}
	
	public int altaInsumo(Insumo objInsumo) {
		
		try {
			
			int idInsumo = 0;
			Statement st = this.db.getConnection().createStatement();
			String query = "insert into insumo (descripcionInsumo, cantidadStock, fechaAlta, idUsuario) ";
			query += "values('"+ objInsumo.getDescripcionInsumo() +"'," + objInsumo.getCantidadStock() +",now(),"+ objInsumo.getIdUsuario() + ")";
			
		    if (st.execute(query)) {
		    	query = "select max(idInsumo) from Insumo";
		    	ResultSet rs = st.executeQuery(query);
				
				if(rs != null) {
				    if (rs.next()) {
				    	idInsumo = rs.getInt(1);
				    }
				}
		    } 
			
			return idInsumo;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar altaInsumo." + e.getMessage()); 
			return -1;
		}
	}
	
	public boolean modificarInsumo(Insumo objInsumo) {
		
		try {
			
			boolean resultado = false;
			Statement st = this.db.getConnection().createStatement();
			String query = "update insumo ";
			query += "set cantidadStock = "+ objInsumo.getCantidadStock() +", ";
			query += "descripcionInsumo = '"+ objInsumo.getDescripcionInsumo() +"' ";
			query += "where idInsumo = " + objInsumo.getIdInsumo();
			
		    if (st.execute(query)) {
		    	resultado = true;
		    } 
			
			return resultado;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar modificarInsumo." + e.getMessage()); 
			return false;
		}
	}

	public boolean bajaInsumo(Insumo objInsumo) {
		
		try {
			
			boolean resultado = false;
			Statement st = this.db.getConnection().createStatement();
			String query = "update insumo ";
			query += "set fechaBaja = '"+ objInsumo.getFechaBaja() +"' ";
			query += "where idInsumo = " + objInsumo.getIdInsumo();
			
		    if (st.execute(query)) {
		    	resultado = true;
		    } 
			
			return resultado;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar bajaInsumo." + e.getMessage()); 
			return false;
		}
	
	}
	
	
	public int obtenerStock(int idInsumo) {
		
		try {
			int stock = 0;
			Statement st = this.db.getConnection().createStatement();
			String query = "select  i.cantidadStock - (select sum(ip.cantidad) ";
			query += "from proyecto p inner join insumo_proyecto ip on p.numeroProyecto = ip.numeroProyecto ";
			query += "where now() between p.fechaEstimadaInicio and p.fechaFin and ip.idInsumo = i.idInsumo)";
			query += "from insumo i ";
			query += "where i.idInsumo = " + idInsumo;
		
			ResultSet rs = st.executeQuery(query);
			
			if(rs != null) {
			    if(rs.next()) {
			    	stock = rs.getInt(1);
			    }
			    rs.close();
			}			
			return stock;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar obtenerStock." + e.getMessage()); 
			return -1;
		}
	
	}


}
