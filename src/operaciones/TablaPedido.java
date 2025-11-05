package operaciones;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import sistema.BaseDatos;

public class TablaPedido {
	
	private BaseDatos db;
	
	public TablaPedido (BaseDatos objDb) {
		
		this.db = objDb;
	}

	
	public Pedido obtenerPedido(int numeroPedido) {
		
		try {
					
			Pedido objPedido = null;
			Statement st = this.db.getConnection().createStatement();
			String query = "select numeroPedido, fechaPedido, numeroProyecto, idCliente, idUsuario, detallePedido, caracteristicasPedido ";
			query += "from pedido where numeroPedido = " + numeroPedido;
			ResultSet rs = st.executeQuery(query);
			
			if(rs != null) {
				objPedido = new Pedido();
			    if(rs.next()) {
			    	objPedido.setNumeroPedido(rs.getInt("numeroPedido"));
			    	objPedido.setFechaPedido(rs.getDate("fechaPedido"));
			    	objPedido.setNumeroProyecto(rs.getInt("numeroProyecto"));
			    	objPedido.setIdCliente(rs.getInt("idCliente"));
			    	objPedido.setIdUsuario(rs.getInt("idUsuario"));
			    	objPedido.setDetallePedido(rs.getString("detallePedido"));
			    	objPedido.setCaracteristicasPedido(rs.getString("caracteristicasPedido"));
			    	
			    }
			    rs.close();
			}
			return objPedido;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar obtenerPedido." + e.getMessage()); 
			return null;
		}
		
	}
	
	public ArrayList<Pedido> obtenerPedidos(boolean sinProyecto) {
		
		try {
			String where = "";
			
			if(sinProyecto) {
				where = "WHERE numeroProyecto is null ";
			}
			
			ArrayList<Pedido> colPedidos = new ArrayList<Pedido>();
			Statement st = this.db.getConnection().createStatement();
			String query = "select numeroPedido, fechaPedido, numeroProyecto, idCliente, idUsuario, detallePedido, caracteristicasPedido ";
			query += "from pedido ";
			query += where;
			
			
			
			
			ResultSet rs = st.executeQuery(query);
			
			if(rs != null) {
			    while(rs.next()) {
			    	Pedido objPedido = new Pedido();
			    	objPedido.setNumeroPedido(rs.getInt("numeroPedido"));
			    	objPedido.setFechaPedido(rs.getDate("fechaPedido"));
			    	objPedido.setNumeroProyecto(rs.getInt("numeroProyecto"));
			    	objPedido.setIdCliente(rs.getInt("idCliente"));
			    	objPedido.setIdUsuario(rs.getInt("idUsuario"));
			    	objPedido.setDetallePedido(rs.getString("detallePedido"));
			    	objPedido.setCaracteristicasPedido(rs.getString("caracteristicasPedido"));
			    	colPedidos.add(objPedido);
			    }
			    rs.close();
			}
			return colPedidos;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar obtenerPedidos." + e.getMessage()); 
			return null;
		}
		
	}
	
	public int altaPedido(Pedido objPedido) {
		
		try {
			
			int numeroPedido = 0;
			Statement st = this.db.getConnection().createStatement();
			String query = "insert into pedido (fechaPedido, idCliente, idUsuario, detallePedido, caracteristicasPedido) ";
			query += "values(now()," + objPedido.getIdCliente() +","+ objPedido.getIdUsuario()+",";
			query += "'"+ objPedido.getDetallePedido()+"','"+ objPedido.getCaracteristicasPedido() + "')";
			
		    st.execute(query);
	    	query = "select max(numeroPedido) from pedido";
	    	ResultSet rs = st.executeQuery(query);
			
			if(rs != null) {
			    if (rs.next()) {
			    	numeroPedido = rs.getInt(1);
			    }
			} 
			
			return numeroPedido;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar altaPedido." + e.getMessage()); 
			return -1;
		}
	}
	
	public boolean modificarPedido(Pedido objPedido) {
		
		try {
			
			Statement st = this.db.getConnection().createStatement();
			String query = "update pedido ";
			query += "set idCliente = "+ objPedido.getIdCliente() +", ";
			if(objPedido.getNumeroProyecto() > 0) {
				query += "numeroProyecto = "+ objPedido.getNumeroProyecto() +", ";
			}
			query += "detallePedido = '"+ objPedido.getDetallePedido() +"', ";
			query += "caracteristicasPedido = '"+ objPedido.getCaracteristicasPedido() +"' ";
			query += "where numeroPedido = " + objPedido.getNumeroPedido();
			
			st.execute(query);
			
			return true;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar modificarPedido." + e.getMessage()); 
			return false;
		}
	}
	
	public Pedido obtenerPedidoAsociado(int numeroProyecto) {
		
		try {
			
			Pedido objPedido = null;
			Statement st = this.db.getConnection().createStatement();
			String query = "select numeroPedido, fechaPedido, numeroProyecto, idCliente, idUsuario, detallePedido, caracteristicasPedido ";
			query += "from pedido where numeroProyecto = " + numeroProyecto;
			ResultSet rs = st.executeQuery(query);
			
			if(rs != null) {
				objPedido = new Pedido();
			    if(rs.next()) {
			    	objPedido.setNumeroPedido(rs.getInt("numeroPedido"));
			    	objPedido.setFechaPedido(rs.getDate("fechaPedido"));
			    	objPedido.setNumeroProyecto(rs.getInt("numeroProyecto"));
			    	objPedido.setIdCliente(rs.getInt("idCliente"));
			    	objPedido.setIdUsuario(rs.getInt("idUsuario"));
			    	objPedido.setDetallePedido(rs.getString("detallePedido"));
			    	objPedido.setCaracteristicasPedido(rs.getString("caracteristicasPedido"));
			    	
			    }
			    rs.close();
			}
			return objPedido;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar obtenerPedido." + e.getMessage()); 
			return null; 
		}
	}

	public boolean anularPedido(int numeroPedido) {
		
		try {
			
			Statement st = this.db.getConnection().createStatement();
			String query = "delete from pedido where numeroPedido = " + numeroPedido;
			
		    st.execute(query);

			return true;
		} catch(SQLException e) {
			System.out.println("Error al ejecutar anularPedido." + e.getMessage()); 
			return false;
		}
	
	}

}
