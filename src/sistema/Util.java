package sistema;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Util {
	
	public static String obtenerFechaFormateada(Date fecha) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String fechaResultado = "";
		if(fecha != null) {
			fechaResultado = formatter.format(fecha);	
		} 
		return fechaResultado;
	}
	
	public static Date obtenerFechaDate(String fecha) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
			Date fechaDate = formatter.parse(fecha);
			return fechaDate;
		} catch (Exception objE) {
			System.out.println(objE.getMessage());
			return null;
		}
		
	}
	
	public static Date transformarFecha(java.sql.Date fecha ) {
		return new java.util.Date(fecha.getTime());
	}
	
	public static String obtenerFechaSQL(Date fecha) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		String fechaSQL = formatter.format(fecha);
		return fechaSQL;
	}
	
	public static boolean validarCUIT(String cuit) {

	    cuit = cuit.replaceAll("-", "");
	    if (cuit.length() != 11) {
	        return false;
	    }
	    String[] cuitArray = cuit.split("");
	    
	    Integer[] serie = {5, 4, 3, 2, 7, 6, 5, 4, 3, 2};
	    
	    Integer aux = 0;
	    for (int i = 0; i < 10; i++) {
	        aux += Integer.valueOf(cuitArray[i]) * serie[i];
	    }
	    
	    aux = 11 - (aux % 11);
	    
	    if (aux == 11) {
	        aux = 0;
	    } else if (aux == 10) {
	        aux = 9;
	    }
	    
	    return Integer.valueOf(cuitArray[10]) == aux;
	}


}
