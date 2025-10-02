package recursos;

import java.util.Date;

public class Insumo {
	private int idInsumo;
	private String descripcionInsumo; 
	private int cantidadStock;
	private Date fechaAlta; 
	private Date fechaBaja; 
	private int idUsuario;
	
	public Insumo () {
		this.idInsumo = 0;
		this.descripcionInsumo = "";
		this.cantidadStock = 0;
		this.fechaAlta = new Date ();
		this.fechaBaja = null;
		this.idUsuario = 0;
	}

	public int getIdInsumo() {
		return idInsumo;
	}

	public void setIdInsumo(int idInsumo) {
		this.idInsumo = idInsumo;
	}

	public String getDescripcionInsumo() {
		return descripcionInsumo;
	}

	public void setDescripcionInsumo(String descripcionInsumo) {
		this.descripcionInsumo = descripcionInsumo;
	}

	public int getCantidadStock() {
		return cantidadStock;
	}

	public void setCantidadStock(int cantidadStock) {
		this.cantidadStock = cantidadStock;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}


}
