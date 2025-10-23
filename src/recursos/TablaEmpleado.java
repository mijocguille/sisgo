package recursos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import sistema.BaseDatos;

public class TablaEmpleado {
	private BaseDatos db;
	
	public TablaEmpleado (BaseDatos objDb) {
		
		this.db = objDb;
	}

	
	public Empleado obtenerEmpleado(int idEmpleado) {
		
		try {
						
			Empleado objEmpleado = null;
			Statement st = this.db.getConnection().createStatement();
			String query = "select idEmpleado, legajo, nombre, apellido, fechaAlta, fechaBaja, idUsuario ";
			query += "from empleado where idEmpleado = " + idEmpleado;
			ResultSet rs = st.executeQuery(query);
			
			if(rs != null) {
				objEmpleado = new Empleado();
			    if(rs.next()) {
			    	objEmpleado.setIdEmpleado(rs.getInt("idEmpleado"));
			    	objEmpleado.setLegajo(rs.getInt("legajo"));
			    	objEmpleado.setNombre(rs.getString("nombre"));
			    	objEmpleado.setApellido(rs.getString("apellido"));
			    	objEmpleado.setFechaAlta(rs.getDate("fechaAlta"));
			    	objEmpleado.setFechaBaja(rs.getDate("fechaBaja"));
			    	objEmpleado.setIdUsuario(rs.getInt("idUsuario"));
			    }
			    rs.close();
			}
			return objEmpleado;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar obtenerEmpleado." + e.getMessage()); 
			return null;
		}
		
	}
	
	public ArrayList<Empleado> obtenerEmpleados() {
		
		try {
			
			ArrayList<Empleado> colEmpleados = new ArrayList<Empleado>();
			Statement st = this.db.getConnection().createStatement();
			String query = "select idEmpleado, legajo, nombre, apellido, fechaAlta, fechaBaja, idUsuario ";
			query += "from empleado ";
			ResultSet rs = st.executeQuery(query);
			
			if(rs != null) {
			    while(rs.next()) {
			    	Empleado objEmpleado = new Empleado();
			    	objEmpleado.setIdEmpleado(rs.getInt("idEmpleado"));
			    	objEmpleado.setLegajo(rs.getInt("legajo"));
			    	objEmpleado.setNombre(rs.getString("nombre"));
			    	objEmpleado.setApellido(rs.getString("apellido"));
			    	objEmpleado.setFechaAlta(rs.getDate("fechaAlta"));
			    	objEmpleado.setFechaBaja(rs.getDate("fechaBaja"));
			    	objEmpleado.setIdUsuario(rs.getInt("idUsuario"));
			    	colEmpleados.add(objEmpleado);
			    }
			    rs.close();
			}
			return colEmpleados;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar obtenerEmpleados." + e.getMessage()); 
			return null;
		}
		
	}
	
	public int altaEmpleado(Empleado objEmpleado) {
		
		try {
			
			int idEmpleado = 0;
			Statement st = this.db.getConnection().createStatement();
			String query = "insert into empleado (legajo, nombre, apellido, fechaAlta, idUsuario) ";
			query += "values("+ objEmpleado.getLegajo() +",'" + objEmpleado.getNombre() +"','"+ objEmpleado.getApellido()+"',";
			query += "now(),"+ objEmpleado.getIdUsuario() + ")";
			
		    st.execute(query);
	    	query = "select max(idEmpleado) from empleado";
	    	ResultSet rs = st.executeQuery(query);
			
			if(rs != null) {
			    if (rs.next()) {
			    	idEmpleado = rs.getInt(1);
			    }
			} 
			
			return idEmpleado;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar altaEmpleado." + e.getMessage()); 
			return -1;
		}
	}
	
	public boolean modificarEmpleado(Empleado objEmpleado) {
		
		try {
			
			Statement st = this.db.getConnection().createStatement();
			String query = "update Empleado ";
			query += "set legajo = "+ objEmpleado.getLegajo() +", ";
			query += "nombre = '"+ objEmpleado.getNombre() +"', ";
			query += "apellido = '"+ objEmpleado.getApellido() +"' ";
			query += "where idEmpleado = " + objEmpleado.getIdEmpleado();
			
			st.execute(query);
			
			return true;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar modificarEmpleado." + e.getMessage()); 
			return false;
		}
	}

	public boolean bajaEmpleado(Empleado objEmpleado) {
		
		try {
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String fechaBaja = formatter.format(objEmpleado.getFechaBaja());
			
			Statement st = this.db.getConnection().createStatement();
			String query = "update empleado ";
			query += "set fechaBaja = '"+ fechaBaja +"' ";
			query += "where idEmpleado = " + objEmpleado.getIdEmpleado();
			
		    st.execute(query);
			
			return true;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar bajaEmpleado." + e.getMessage()); 
			return false;
		}
	
	}


}
