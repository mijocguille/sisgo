package operaciones;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import sistema.BaseDatos;

public class TablaProyecto {

	private BaseDatos db;
	
	public TablaProyecto (BaseDatos objDb) {
		
		this.db = objDb;
	}

	
	public Proyecto obtenerProyecto(int numeroProyecto) {
		
		try {
		
			Proyecto objProyecto = null;
			Statement st = this.db.getConnection().createStatement();
			String query = "select numeroProyecto, fechaCreacion, nombreProyecto, fechaEstimadaInicio, fechaFin, idUsuario ";
			query += "from proyecto where numeroProyecto = " + numeroProyecto;
			ResultSet rs = st.executeQuery(query);
			
			if(rs != null) {
				objProyecto = new Proyecto();
			    while(rs.next()) {
			    	objProyecto.setNumeroProyecto(rs.getInt("numeroProyecto"));
			    	objProyecto.setFechaCreacion(rs.getDate("fechaCreacion"));
			    	objProyecto.setNombreProyecto(rs.getString("nombreProyecto"));
			    	objProyecto.setFechaEstimadaInicio(rs.getDate("fechaEstimadaInicio"));
			    	objProyecto.setFechaFin(rs.getDate("fechaFin"));
			    	objProyecto.setIdUsuario(rs.getInt("idUsuario"));
			    }
			    rs.close();
			}
			return objProyecto;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar obtenerProyecto." + e.getMessage()); 
			return null;
		}
		
	}
	
	public ArrayList<Proyecto> obtenerProyectos() {
		
		try {
			
			ArrayList<Proyecto> colProyectos = new ArrayList<Proyecto>();
			Statement st = this.db.getConnection().createStatement();
			String query = "select numeroProyecto, fechaCreacion, nombreProyecto, fechaEstimadaInicio, fechaFin, idUsuario ";
			query += "from proyecto ";
			ResultSet rs = st.executeQuery(query);
			
			if(rs != null) {
			    while(rs.next()) {
			    	Proyecto objProyecto = new Proyecto();
			    	objProyecto.setNumeroProyecto(rs.getInt("numeroProyecto"));
			    	objProyecto.setFechaCreacion(rs.getDate("fechaCreacion"));
			    	objProyecto.setNombreProyecto(rs.getString("nombreProyecto"));
			    	objProyecto.setFechaEstimadaInicio(rs.getDate("fechaEstimadaInicio"));
			    	objProyecto.setFechaFin(rs.getDate("fechaFin"));
			    	objProyecto.setIdUsuario(rs.getInt("idUsuario"));
			    	colProyectos.add(objProyecto);
			    }
			    rs.close();
			}
			return colProyectos;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar obtenerProyectos." + e.getMessage()); 
			return null;
		}
		
	}
	
	public int altaProyecto(Proyecto objProyecto) {
		
		try {
			
			int numeroProyecto = 0;
			Statement st = this.db.getConnection().createStatement();
			String query = "insert into proyecto (fechaCreacion, nombreProyecto, fechaEstimadaInicio, fechaFin, idUsuario) ";
			query += "values('"+ objProyecto.getFechaCreacion() +"','" + objProyecto.getNombreProyecto() +"','"+ objProyecto.getFechaEstimadaInicio()+"',";
			query += "'"+ objProyecto.getFechaFin()+"',"+ objProyecto.getIdUsuario() + ")";
			
		    if (st.execute(query)) {
		    	query = "select max(numeroProyecto) from Proyecto";
		    	ResultSet rs = st.executeQuery(query);
				
				if(rs != null) {
				    if (rs.next()) {
				    	numeroProyecto = rs.getInt(1);
				    }
				}
		    } 
			
			return numeroProyecto;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar altaProyecto." + e.getMessage()); 
			return -1;
		}
	}
	
	public boolean modificarProyecto(Proyecto objProyecto) {
		
		try {
			
			boolean resultado = false;
			Statement st = this.db.getConnection().createStatement();
			String query = "update proyecto ";
			query += "set nombreProyecto = '"+ objProyecto.getNombreProyecto() +"', ";
			query += "fechaEstimadaInicio = '"+ objProyecto.getFechaEstimadaInicio() +"', ";
			query += "fechaFin = '"+ objProyecto.getFechaFin() +"' ";
			query += "where numeroProyecto = " + objProyecto.getNumeroProyecto();
			
		    if (st.execute(query)) {
		    	resultado = true;
		    } 
			
			return resultado;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar modificarProyecto." + e.getMessage()); 
			return false;
		}
	}

	public boolean eliminarProyecto(int numeroProyecto) {
		
		try {
			
			boolean resultado = false;
			Statement st = this.db.getConnection().createStatement();
			String query = "delete from proyecto where numeroProyecto = " + numeroProyecto;
			
		    if (st.execute(query)) {
		    	resultado = true;
		    } 
			
			return resultado;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar eliminarProyecto." + e.getMessage()); 
			return false;
		}
	
	}
	
	
	public boolean validar(int numeroPedido) {
		try {
			boolean resultado = false;
			Statement st = this.db.getConnection().createStatement();
			String query = "select numeroProyecto ";
			query += "from pedido where numeroPedido = " + numeroPedido + " and numeroProyecto is null ";
			ResultSet rs = st.executeQuery(query);
			
			if(rs != null) {
			    if(rs.next()) {
			    	resultado = true;
			    }
			    rs.close();
			}
			return resultado;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar validar." + e.getMessage()); 
			return false;
		}
	}
}
