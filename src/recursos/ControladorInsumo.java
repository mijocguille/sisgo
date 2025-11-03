package recursos;


import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import sistema.BaseDatos;
import sistema.Util;



public class ControladorInsumo {
	
	TablaInsumo tblInsumo;
	
	public TablaInsumo getTblInsumo() {
		return tblInsumo;
	}
	
	public ControladorInsumo(BaseDatos db) {
		tblInsumo = new TablaInsumo(db);
	}
	
	public TableModel listarInsumos() {
		
		DefaultTableModel model = new DefaultTableModel();
		String[] encabezados = {"#", "Descripci\u00F3n", "Cantidad", "Fecha Alta", "Fecha Baja"};
		model.setColumnIdentifiers(encabezados);
	
		ArrayList<Insumo> colInsumos = tblInsumo.obtenerInsumos(); 

		for(Insumo i : colInsumos) {
			String fechaAlta = Util.obtenerFechaFormateada(i.getFechaAlta());
			String fechaBaja = Util.obtenerFechaFormateada(i.getFechaBaja());
			
			String[] row = {String.valueOf(i.getIdInsumo()),i.getDescripcionInsumo(),String.valueOf(i.getCantidadStock()),fechaAlta,fechaBaja};
			model.addRow(row);
		}	
		
		return model;
	}
	
	
	public int darAltaInsumo(Insumo objIns) {
		int numeroPedido = 0;
		if(this.validarInformacionInsumo(objIns)) {
			numeroPedido = tblInsumo.altaInsumo(objIns);
		}		
		return numeroPedido; 
	}
	
    private boolean validarInformacionInsumo(Insumo objIns) {
    	return true;
    }
    
    
    public boolean darBajaInsumo(int idInsumo) {
    	return tblInsumo.bajaInsumo(idInsumo);
    }
    
    public boolean modificarInsumo(Insumo objIns) {
    	boolean resultado = false;
    	if(this.validarInformacionInsumo(objIns)) {
    		resultado = tblInsumo.modificarInsumo(objIns);
    	}
    	return resultado;
    }

}
