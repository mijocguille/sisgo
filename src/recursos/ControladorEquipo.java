package recursos;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import sistema.BaseDatos;
import sistema.Util;

public class ControladorEquipo {
	private TablaEquipo tblEquipos;
	
	public TablaEquipo getTblEquipos() {
		return tblEquipos;
	}

	public ControladorEquipo(BaseDatos db) {
		tblEquipos = new TablaEquipo(db);
	}
	
	public TableModel listarEquipos() {
		
		DefaultTableModel model = new DefaultTableModel();
		String[] encabezados = {"#", "Descripci\u00F3n", "Cantidad", "Fecha Alta", "Fecha Baja"};
		model.setColumnIdentifiers(encabezados);
	
		ArrayList<Equipo> colEquipos = tblEquipos.obtenerEquipos(); 

		for(Equipo eq : colEquipos) {				
			String fechaAlta = Util.obtenerFechaFormateada(eq.getFechaAlta());
			String fechaBaja = Util.obtenerFechaFormateada(eq.getFechaBaja());				
			String[] row = {String.valueOf(eq.getIdEquipo()),eq.getDescripcionEquipo(), String.valueOf(eq.getCantidadEquipos()),fechaAlta, fechaBaja};
			model.addRow(row);
		}	
		
		return model;
		
	}
	
	
	public int darAltaEquipo(Equipo objEquipo) {
		int idEquipo = 0;
		if(this.validarInformacionEquipo(objEquipo)) {
			idEquipo = tblEquipos.altaEquipo(objEquipo);
		}		
		return idEquipo; 
	}
	
    private boolean validarInformacionEquipo(Equipo objEquipo) {
    	return true;
    } 
   
    
    public boolean darBajaEquipo(int idEquipo) {
    	return tblEquipos.bajaEquipo(idEquipo);
    }
    
    public boolean modificarEquipo(Equipo objEquipo) {
    	boolean resultado = false;
    	if(this.validarInformacionEquipo(objEquipo)) {
			resultado = tblEquipos.modificarEquipo(objEquipo);
		}		
    	return resultado;
    }


}
