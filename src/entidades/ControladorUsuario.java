package entidades;


import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import sistema.BaseDatos;

public class ControladorUsuario {
	
	private TablaUsuario tblUsuario;
	
	public TablaUsuario getTblUsuario() {
		return tblUsuario;
	}

	public ControladorUsuario(BaseDatos db) {
		tblUsuario = new TablaUsuario(db);		
	}
	
	public TableModel listarUsuarios() {
		DefaultTableModel model = new DefaultTableModel();
		String[] encabezados = {"#", "Usuario", "Descripci\u00F3n", "Fecha Alta", "Fecha Baja"};
		model.setColumnIdentifiers(encabezados);
	
		ArrayList<Usuario> colUsuarios = tblUsuario.obtenerUsuarios(); 

		for(Usuario u : colUsuarios) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String fechaAlta = formatter.format(u.getFechaAlta());
			String fechaBaja = "";
			if(u.getFechaBaja() != null) {
				fechaBaja = formatter.format(u.getFechaBaja());	
			}
			
			String[] row = {String.valueOf(u.getIdUsuario()),u.getNombreUsuario(), u.getDescripcionUsuario(),fechaAlta,fechaBaja};
			model.addRow(row);
		}	
		
		return model;
		
	}
	
	
	public int darAltaUsuario(Usuario objUsuario) {
		int idUsuario = 0;
		if(this.validarInformacionUsuario(objUsuario)) {
			idUsuario = tblUsuario.altaUsuario(objUsuario);
		}		
		return idUsuario; 
	}
	
    private boolean validarInformacionUsuario(Usuario objUsuario) {
    	return true;
    }
        
    public boolean darBajaUsuario(int idUsuario) {
    	return tblUsuario.bajaUsuario(idUsuario);
    }
    
    public boolean modificarUsuario(Usuario objUsuario) {
    	boolean resultado = false;
    	if(this.validarInformacionUsuario(objUsuario)) {
			resultado = tblUsuario.modificarUsuario(objUsuario);
		}		
    	return resultado;
    }
    
    public boolean login() {
    	return true;
    }
    
    public boolean logout() {
    	return true;
    }
    
    public String cifrar(String contrasenia) {
    	try {
	    	MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
	    	sha256.update(contrasenia.getBytes("UTF-8"));
	    	byte[] digest = sha256.digest();
	    	StringBuffer sb = new StringBuffer();
	    	for(int i=0;i < digest.length;i++){
	    	    sb.append(String.format("%02x", digest[i]));
	    	}
	    	String hash=sb.toString();
	    	return hash;
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    		return null;
    	}
    } 
}
