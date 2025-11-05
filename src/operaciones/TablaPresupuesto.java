package operaciones;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import sistema.BaseDatos;

public class TablaPresupuesto {

	private BaseDatos db;
	
	public TablaPresupuesto(BaseDatos objDb) {
		
		this.db = objDb;
	}

	
	public Presupuesto obtenerPresupuesto(int numeroProyecto, int numeroPresupuesto) { 
		
		try {
		
			Presupuesto objPresupuesto= null;
			Statement st = this.db.getConnection().createStatement();
			String query = "select numeroProyecto, numeroPresupuesto, fechaPresupuesto, diasValidez, importePresupuestado, idUsuario ";
			query += "from presupuesto where numeroProyecto = " + numeroProyecto + " and numeroPresupuesto= " + numeroPresupuesto;
			ResultSet rs = st.executeQuery(query);
			
			if(rs != null) {
				objPresupuesto = new Presupuesto();
			    if(rs.next()) {
			    	objPresupuesto.setNumeroProyecto(rs.getInt("numeroProyecto"));
			    	objPresupuesto.setNumeroPresupuesto(rs.getInt("numeroPresupuesto"));
			    	objPresupuesto.setFechaPresupuesto(rs.getDate("fechaPresupuesto"));
			    	objPresupuesto.setDiasValidez(rs.getInt("diasValidez"));
			    	objPresupuesto.setImportePresupuestado(rs.getDouble("importePresupuestado"));
			    	objPresupuesto.setIdUsuario(rs.getInt("idUsuario"));
			    }
			    rs.close();
			}
			return objPresupuesto;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar obtenerPresupuesto." + e.getMessage()); 
			return null;
		}
		
	}
	
	public ArrayList<Presupuesto> obtenerPresupuestos(int numeroProyecto) {
		
		try {
			
			ArrayList<Presupuesto> colPresupuestos = new ArrayList<Presupuesto>();
			Statement st = this.db.getConnection().createStatement();
			String query = "select numeroProyecto, numeroPresupuesto, fechaPresupuesto, diasValidez, importePresupuestado, idUsuario ";
			query += "from presupuesto where numeroProyecto = " + numeroProyecto;
			ResultSet rs = st.executeQuery(query);
			
			if(rs != null) {
			    while(rs.next()) {
			    	Presupuesto objPresupuesto= new Presupuesto();
			    	objPresupuesto.setNumeroProyecto(rs.getInt("numeroProyecto"));
			    	objPresupuesto.setNumeroPresupuesto(rs.getInt("numeroPresupuesto"));
			    	objPresupuesto.setFechaPresupuesto(rs.getDate("fechaPresupuesto"));
			    	objPresupuesto.setDiasValidez(rs.getInt("diasValidez"));
			    	objPresupuesto.setImportePresupuestado(rs.getDouble("importePresupuestado"));
			    	objPresupuesto.setIdUsuario(rs.getInt("idUsuario"));
			    	colPresupuestos.add(objPresupuesto);
			    }
			    rs.close();
			}
			return colPresupuestos;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar obtenerPresupuestos." + e.getMessage()); 
			return null;
		}
		
	}
	
	public boolean altaPresupuesto(Presupuesto objPresupuesto) {
		
		try {
			
			Statement st = this.db.getConnection().createStatement();
			String query = "insert into presupuesto(numeroProyecto, numeroPresupuesto, fechaPresupuesto, diasValidez, importePresupuestado, idUsuario) ";
			query += "values("+ objPresupuesto.getNumeroProyecto() +"," + objPresupuesto.getNumeroPresupuesto() +",now(),";
			query += objPresupuesto.getDiasValidez()+","+ objPresupuesto.getImportePresupuestado() + ","+ objPresupuesto.getIdUsuario() + ")";
			
		    st.execute(query);
		    
			
			return true;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar altaPresupuesto." + e.getMessage()); 
			return false;
		}
	}
	
	public boolean modificarPresupuesto(Presupuesto objPresupuesto) {
		
		try {
			
			Statement st = this.db.getConnection().createStatement();
			String query = "update presupuesto";
			query += "set diasValidez= "+ objPresupuesto.getDiasValidez() +", ";
			query += "importePresupuestado = '"+ objPresupuesto.getImportePresupuestado() +" ";
			query += "where numeroProyecto = " + objPresupuesto.getNumeroProyecto() + " and numeroPresupuesto = " + objPresupuesto.getNumeroPresupuesto();
			
			st.execute(query);
			
			return true;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar modificarPresupuesto." + e.getMessage()); 
			return false;
		}
	}

	public boolean eliminarPrespuesto(Presupuesto objPresupuesto) {
		
		try {
			
			Statement st = this.db.getConnection().createStatement();
			String query = "delete from presupuesto where numeroProyecto = "+ objPresupuesto.getNumeroProyecto() + " and numeroPresupuesto = " + objPresupuesto.getNumeroPresupuesto();
			
		    st.execute(query);
			
			return true;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar eliminarPrespuesto." + e.getMessage()); 
			return false;
		}
	
	}
	
	
	public int obtenerProximoPresupuesto(int numeroProyecto) {
		try {
			int numeroPresupuesto = 1;
			Statement st = this.db.getConnection().createStatement();
			String query = "select max(numeroPresupuesto) ";
			query += "from presupuesto where numeroProyecto = " + numeroProyecto;
			ResultSet rs = st.executeQuery(query);
			
			if(rs != null) {
			    if(rs.next()) {
			    	numeroPresupuesto = rs.getInt(1) + 1;
			    }
			    rs.close();
			} 
			return numeroPresupuesto;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar obtenerProximoPresupuesto." + e.getMessage()); 
			return -1;
		}
	}

}
