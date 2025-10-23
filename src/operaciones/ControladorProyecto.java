package operaciones;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import sistema.BaseDatos;


public class ControladorProyecto {

	private TablaProyecto tblProyecto;
	public TablaProyecto getTblProyecto() {
		return tblProyecto;
	}

	public TablaPedido getTblPedido() {
		return tblPedido;
	}

	private TablaPedido tblPedido;
	
	
	public ControladorProyecto(BaseDatos db) {
		
		tblProyecto = new TablaProyecto(db);
		tblPedido = new TablaPedido(db);
		
	}
	
	public TableModel listarProyectos() {
		
		DefaultTableModel model = new DefaultTableModel();
		String[] encabezados = {"#", "Fecha Creaci\u00F3n", "Nombre Proyecto", "Fecha Est. Inicio", "Fecha Fin", "Pedido Asociado"};
		model.setColumnIdentifiers(encabezados);
	
		ArrayList<Proyecto> colProyectos = tblProyecto.obtenerProyectos(); 

		for(Proyecto p : colProyectos) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
			
			String fechaCreacion = formatter.format(p.getFechaCreacion());
			String fechaInicio = formatter.format(p.getFechaEstimadaInicio());
			String fechaFin = formatter.format(p.getFechaFin());
			
			Pedido objPedido = tblPedido.obtenerPedidoAsociado(p.getNumeroProyecto());
			String pedidoAsociado = "";
			if (objPedido != null) {
				pedidoAsociado = String.valueOf(objPedido.getNumeroPedido());
			}
			
			String[] row = {String.valueOf(p.getNumeroProyecto()),fechaCreacion,p.getNombreProyecto(),fechaInicio,fechaFin, pedidoAsociado};
			model.addRow(row);
		}			
		
		return model;
	}
		
	public int crearProyecto(Proyecto objP) {
		
		int numeroProyecto = 0;
		if(this.validarInformacion(objP)) {
			numeroProyecto = tblProyecto.altaProyecto(objP);
		}		
		return numeroProyecto; 
	
	}
	
	private boolean validarInformacion(Proyecto p) {
    	return true;
    }   
    
    
    public boolean bajaProyecto(int numeroProyecto) {
    	return tblProyecto.eliminarProyecto(numeroProyecto);
    }
    
    public boolean modificaProyecto(Proyecto objP) {
    	boolean resultado = false;
		if(this.validarInformacion(objP)) {
			resultado = tblProyecto.modificarProyecto(objP);
		}		
		return resultado;
    }
}
