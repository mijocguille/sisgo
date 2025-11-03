package entidades;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import sistema.BaseDatos;
import sistema.Util;

public class ControladorCliente {
	
	private TablaCliente tblCliente;
	
	public TablaCliente getTblCliente() {
		return tblCliente;
	}

	public ControladorCliente(BaseDatos db) {
		tblCliente = new TablaCliente(db);
	}
	
	public TableModel listarClientes() {
		
		DefaultTableModel model = new DefaultTableModel(){

		    @Override
		    public boolean isCellEditable(int i, int i1) {
		        return false;
		    }

		   };
		   
		String[] encabezados = {"#", "Raz\u00F3n Social", "Cuit", "Direcci\u00F3n ", "Tel\u00E9fono", "Fecha Alta", "Fecha Baja"};
		model.setColumnIdentifiers(encabezados);
	
		ArrayList<Cliente> colClientes = tblCliente.obtenerClientes(); 

		for(Cliente c : colClientes) {
			String fechaAlta = Util.obtenerFechaFormateada(c.getFechaAlta());
			String fechaBaja = Util.obtenerFechaFormateada(c.getFechaBaja());
			String[] row = {String.valueOf(c.getIdCliente()),c.getRazonSocial(),c.getCuit(),c.getDireccion(),c.getTelefono(),fechaAlta,fechaBaja};
			model.addRow(row);
		}	
		
		return model;
	}
	
	public int crearCliente(Cliente objCLi) {
		int idCliente = 0;
		if(this.validarInformacion(objCLi)) {
			idCliente = tblCliente.altaCliente(objCLi);
		}		
		return idCliente; 
	}
	
    private boolean validarInformacion(Cliente objCLi) {
    	return true;
    }
    
    public boolean darBajaCliente(int idCliente) {
    	return tblCliente.bajaCliente(idCliente);
    }
    
    public boolean modificarCliente(Cliente objCliente) {
    	boolean resultado = false;
    	if(this.validarInformacion(objCliente)) {
			resultado = tblCliente.modificarCliente(objCliente);
		}		
    	return resultado;
    }
}



