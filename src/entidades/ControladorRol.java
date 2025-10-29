package entidades;


import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import sistema.BaseDatos;


public class ControladorRol {
	
	private TablaRol tblRol;
	
	public TablaRol getTblRol() {
		return tblRol;
	}

	public ControladorRol(BaseDatos db) {
		tblRol = new TablaRol(db);
	}
	
	public TableModel listarRoles() {
		DefaultTableModel model = new DefaultTableModel();
		String[] encabezados = {"#", "Descripci\u00F3n", "Fecha Alta", "Fecha Baja"};
		model.setColumnIdentifiers(encabezados);
	
		ArrayList<Rol> colRoles = tblRol.obtenerRoles(); 

		for(Rol r : colRoles) {	
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String fechaAlta = formatter.format(r.getFechaAlta());
			String fechaBaja = "";
			if(r.getFechaBaja() != null) {
				fechaBaja = formatter.format(r.getFechaBaja());	
			}
			
			String[] row = {String.valueOf(r.getIdRol()),r.getNombreRol(), fechaAlta, fechaBaja};
			model.addRow(row);
		}	
		
		return model;
		
	}
	
	public DefaultComboBoxModel cargarComboRoles() {
		ArrayList<Rol> colRoles = tblRol.obtenerRoles(); 
		String[] items = new String[colRoles.size()];
		int i=0;
		for(Rol r : colRoles) {	
			items[i] = r.getNombreRol();
			i++;	
		}	
		return new DefaultComboBoxModel(items);
		
	}
	
	
	public int darAltaRol(Rol objRol) {
		int idRol = 0;
		if(this.validarInformacion(objRol)) {
			idRol = tblRol.altaRol(objRol);
		}		
		return idRol; 
	}
	
    private boolean validarInformacion(Rol objRol) {
    	return true;
    }
    
    
    public boolean darBajaRol(int idRol) {
    	return tblRol.bajaRol(idRol);
    }
    
    public boolean modificaRol(Rol objRol) {
    	boolean resultado = false;
    	if(this.validarInformacion(objRol)) {
			resultado = tblRol.modificarRol(objRol);
		}		
    	return resultado;
    }
}
