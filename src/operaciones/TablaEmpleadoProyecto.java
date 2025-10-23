package operaciones;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import sistema.BaseDatos;

public class TablaEmpleadoProyecto {
	private BaseDatos db;
	
	public TablaEmpleadoProyecto (BaseDatos objDb) {
		
		this.db = objDb;
	}
	
	public EmpleadoProyecto obtenerEmpleadoProyecto(int numeroProyecto, int idEmpleado) {
		
		try {
			
			EmpleadoProyecto objEP = null;
			Statement st = this.db.getConnection().createStatement();
			String query = "select idEmpleado, numeroProyecto ";
			query += "from empleado_proyecto where numeroProyecto = " + numeroProyecto + " and idEmpleado = " + idEmpleado;
			ResultSet rs = st.executeQuery(query);
			
			if(rs != null) {
				objEP = new EmpleadoProyecto();
			    if(rs.next()) {
			    	objEP.setNumeroProyecto(rs.getInt("numeroProyecto"));
			    	objEP.setIdEmpleado(rs.getInt("idEmpleado"));
			    }
			    rs.close();
			}
			return objEP;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar obtenerEmpleadoProyecto." + e.getMessage()); 
			return null;
		}
		
	}

	public ArrayList<EmpleadoProyecto> obtenerEmpleadosProyecto(int numeroProyecto) {
		
		try {
			
			ArrayList<EmpleadoProyecto> colEmpleados = new ArrayList<EmpleadoProyecto>();
			Statement st = this.db.getConnection().createStatement();
			String query = "select idEmpleado, numeroProyecto ";
			query += "from empleado_proyecto where numeroProyecto = " + numeroProyecto;
			ResultSet rs = st.executeQuery(query);
			
			if(rs != null) {
			    while(rs.next()) {
			    	EmpleadoProyecto objEP = new EmpleadoProyecto();
			    	objEP.setIdEmpleado(rs.getInt("idEmpleado"));
			    	objEP.setNumeroProyecto(rs.getInt("numeroProyecto"));
			    	colEmpleados.add(objEP);
			    }
			    rs.close();
			}
			return colEmpleados;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar obtenerEmpleadosProyecto." + e.getMessage()); 
			return null;
		}
		
	}
	
	public boolean agregarEmpleadoProyecto(EmpleadoProyecto objEP) {
		
		try {
			Statement st = this.db.getConnection().createStatement();
			String query = "insert into empleado_proyecto (idEmpleado, numeroProyecto) ";
			query += "values("+ objEP.getIdEmpleado() +"," + objEP.getNumeroProyecto() +")";
			st.execute(query);
			
			return true;
			
		} catch(SQLException e) {
			System.out.println("Error al ejecutar agregarEmpleadoProyecto." + e.getMessage()); 
			return false;
		}
	}
	
	public boolean quitarEmpleadoProyecto(EmpleadoProyecto objEP) {
		
		try {
			Statement st = this.db.getConnection().createStatement();
			String query = "delete from empleado_proyecto where idEmpleado = " + objEP.getIdEmpleado() + " and numeroProyecto = " + objEP.getNumeroProyecto();
		
		    st.execute(query);
			
			return true;
			
		} catch(SQLException e) {
			System.out.println("Error al ejecutar quitarEmpleadoProyecto." + e.getMessage()); 
			return false;
		}
	}
	

	public boolean verificarDisponible(EmpleadoProyecto objEP) {
		
		try {
			
			boolean resultado = false;
			Statement st = this.db.getConnection().createStatement();
			String query = "select idEmpleado ";
			query += "from empleado e ";
			query += "where e.idEmpleado = " + objEP.getIdEmpleado() +" and not e.idEmpleado in (select distinct ep.idEmpleado ";
			query += "from proyecto p inner join empleado_proyecto ep on p.numeroProyecto = ep.numeroProyecto ";
			query += "where now() between p.fechaEstimadaInicio and p.fechaFin)";
			
			ResultSet rs = st.executeQuery(query);
			
			if(rs != null) {
			    if(rs.next()) {
			    	resultado = true;
			    }
			    rs.close();
			}			
			return resultado;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar verificarDisponible." + e.getMessage()); 
			return false;
		}
	
	}


}
