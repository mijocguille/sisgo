package operaciones;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import entidades.Cliente;
import entidades.TablaCliente;
import sistema.BaseDatos;
import sistema.Util;


public class ControladorPedido {
	
	private TablaPedido tblPedido;
	private TablaProyecto tblProyecto;
	private TablaCliente tblCliente;
	

	public TablaPedido getTblPedido() {
		return tblPedido;
	}

	public TablaProyecto getTblProyecto() {
		return tblProyecto;
	}

	public TablaCliente getTblCliente() {
		return tblCliente;
	}
	
	
	public ControladorPedido(BaseDatos db) {
		tblPedido  = new TablaPedido(db);
		tblProyecto = new TablaProyecto(db);
		tblCliente = new TablaCliente(db);
	}
	
	public TableModel listarPedidos(boolean sinProyecto) {
		
		DefaultTableModel model = new DefaultTableModel(){

		    @Override
		    public boolean isCellEditable(int i, int i1) {
		        return false;
		    }

		   };
		String[] encabezados = {"#", "Fecha Pedido", "Cliente", "Detalle de Pedido", "Caracter\u00EDsticas", "Proyecto Asignado"};
		model.setColumnIdentifiers(encabezados);
	
		ArrayList<Pedido> colPedidos = tblPedido.obtenerPedidos(sinProyecto); 

		for(Pedido p : colPedidos) {
			String fechaPedido = Util.obtenerFechaFormateada(p.getFechaPedido());
			String proyectoAsociado = "SIN ASOCIAR";
			if(p.getNumeroProyecto() > 0) {
				Proyecto proy = tblProyecto.obtenerProyecto(p.getNumeroProyecto());
				proyectoAsociado = proy.getNombreProyecto();
			}
			
			Cliente objCli = tblCliente.obtenerCliente(p.getIdCliente());
			
			String[] row = {String.valueOf(p.getNumeroPedido()),fechaPedido,objCli.getRazonSocial(), p.getDetallePedido(),p.getCaracteristicasPedido(),proyectoAsociado};
			model.addRow(row);
		}	
		
		return model;
	}
	
	
	public int crearPedido(Pedido objPed) {
		int numeroPedido = 0;
		if(this.validarInformacion(objPed)) {
			numeroPedido = tblPedido.altaPedido(objPed);
		}		
		return numeroPedido; 
	}
	
    private boolean validarInformacion(Pedido objP) {
    	return true;
    }
    
    
    public boolean anularPedido(int numeroPedido) {
    	return tblPedido.anularPedido(numeroPedido);
    }
    
    public boolean modificarPedido(Pedido objP) {
    	boolean resultado = false;
    	if(this.validarInformacion(objP)) {
    		resultado = tblPedido.modificarPedido(objP);
    	}
    	return resultado;
    }
}
