package sistema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDatos {

	
	    private static String url = "jdbc:mysql://localhost:3306/sisgo";    
	    private static String driverName = "com.mysql.cj.jdbc.Driver";   
	    private static String username = "root";   
	    private static String password = "";
	    private static Connection con;
	    private static String urlstring;

	    public Connection getConnection() {
	        try {
	            Class.forName(driverName);
	            try {
	                con = DriverManager.getConnection(url, username, password);
	            } catch (SQLException ex) {
	            	System.out.println("Error al establecer conexión con la base de datos."); 
	            }
	        } catch (ClassNotFoundException ex) {
	           
	            System.out.println("Controlador no encontrado."); 
	        }
	        return con;
	    
	}
	   
}
