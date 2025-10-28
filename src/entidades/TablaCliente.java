package entidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import sistema.BaseDatos;

public class TablaCliente {
	
	private BaseDatos db;
	
	public TablaCliente (BaseDatos objDb) {
		
		this.db = objDb;
	}

	
	public Cliente obtenerCliente(int idCliente) {
		
		try {
			
			Cliente objCliente = null;
			Statement st = this.db.getConnection().createStatement();
			String query = "select idCliente, razonSocial, cuit, direccion, telefono, fechaAlta, fechaBaja, idUsuario ";
			query += "from cliente where idCliente = " + idCliente;
			ResultSet rs = st.executeQuery(query);
			
			if(rs != null) {
				objCliente = new Cliente();
			    if(rs.next()) {
			    	objCliente.setIdCliente(rs.getInt("idCliente"));
			    	objCliente.setRazonSocial(rs.getString("razonSocial"));
			    	objCliente.setCuit(rs.getString("cuit"));
			    	objCliente.setDireccion(rs.getString("direccion"));
			    	objCliente.setTelefono(rs.getString("telefono"));
			    	objCliente.setFechaAlta(rs.getDate("fechaAlta"));
			    	objCliente.setFechaBaja(rs.getDate("fechaBaja"));
			    	objCliente.setIdUsuario(rs.getInt("idUsuario"));
			    }
			    rs.close();
			}
			return objCliente;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar obtenerCliente." + e.getMessage()); 
			return null;
		}
		
	}
	
	public ArrayList<Cliente> obtenerClientes() {
		
		try {
			
			ArrayList<Cliente> colClientees = new ArrayList<Cliente>();
			Statement st = this.db.getConnection().createStatement();
			String query = "select idCliente, razonSocial, cuit, direccion, telefono, fechaAlta, fechaBaja, idUsuario ";
			query += "from cliente ";
			ResultSet rs = st.executeQuery(query);
			
			if(rs != null) {
			    while(rs.next()) {
			    	Cliente objCliente = new Cliente();
			    	objCliente.setIdCliente(rs.getInt("idCliente"));
			    	objCliente.setRazonSocial(rs.getString("razonSocial"));
			    	objCliente.setCuit(rs.getString("cuit"));
			    	objCliente.setDireccion(rs.getString("direccion"));
			    	objCliente.setTelefono(rs.getString("telefono"));
			    	objCliente.setFechaAlta(rs.getDate("fechaAlta"));
			    	objCliente.setFechaBaja(rs.getDate("fechaBaja"));
			    	objCliente.setIdUsuario(rs.getInt("idUsuario"));
			    	colClientees.add(objCliente);
			    }
			    rs.close();
			}
			return colClientees;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar obtenerClientes." + e.getMessage()); 
			return null;
		}
		
	}
	
	public int altaCliente(Cliente objCliente) {
		
		try {
			
			int idCliente = 0;
			Statement st = this.db.getConnection().createStatement();
			String query = "insert into cliente (razonSocial, cuit, direccion, telefono, fechaAlta, idUsuario) ";
			query += "values('"+ objCliente.getRazonSocial() +"','" + objCliente.getCuit() +"','"+ objCliente.getDireccion()+"',";
			query += "'"+ objCliente.getTelefono()+"',now(),"+ objCliente.getIdUsuario() + ")";
			
			st.execute(query);
	    	query = "select max(idCliente) from cliente";
	    	ResultSet rs = st.executeQuery(query);
			
			if(rs != null) {
			    if (rs.next()) {
			    	idCliente = rs.getInt(1);
			    }
			} 
			
			return idCliente;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar altaCliente." + e.getMessage()); 
			return -1;
		}
	}
	
	public boolean modificarCliente(Cliente objCliente) {
		
		try {
			
			Statement st = this.db.getConnection().createStatement();
			String query = "update cliente ";
			query += "set razonSocial = '"+ objCliente.getRazonSocial() +"', ";
			query += "cuit = '"+ objCliente.getCuit() +"', ";
			query += "direccion = '"+ objCliente.getDireccion() +"', ";
			query += "telefono = '"+ objCliente.getTelefono() +"' ";
			query += "where idCliente = " + objCliente.getIdCliente();
			
			st.execute(query);
			
			return true;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar modificarCliente." + e.getMessage()); 
			return false;
		}
	}

	public boolean bajaCliente(int idCliente) {
		
		try {
			
			Statement st = this.db.getConnection().createStatement();
			String query = "update cliente ";
			query += "set fechaBaja = now() ";
			query += "where idCliente = " + idCliente;
			
			st.execute(query);
			
			return true;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar bajaCliente." + e.getMessage()); 
			return false;
		}
	
	}

}
