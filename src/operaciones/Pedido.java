package operaciones;

import java.util.Date;

public class Pedido {
	
	private int numeroPedido;
	private Date fechaPedido;
	private int numeroProyecto;
	private int idCliente;
	private int idUsuario;
	private String detallePedido;
	private String caracteristicasPedido;
	
	public Pedido() {
		this.numeroPedido = 0;
		this.fechaPedido = new Date();
		this.numeroProyecto = 0;
		this.idCliente = 0;
		this.idUsuario = 0;
		this.detallePedido ="";
		this.caracteristicasPedido = "";
		
	}

	public int getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(int numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public Date getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public int getNumeroProyecto() {
		return numeroProyecto;
	}

	public void setNumeroProyecto(int numeroProyecto) {
		this.numeroProyecto = numeroProyecto;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getDetallePedido() {
		return detallePedido;
	}

	public void setDetallePedido(String detallePedido) {
		this.detallePedido = detallePedido;
	}

	public String getCaracteristicasPedido() {
		return caracteristicasPedido;
	}

	public void setCaracteristicasPedido(String caracteristicasPedido) {
		this.caracteristicasPedido = caracteristicasPedido;
	}

}
