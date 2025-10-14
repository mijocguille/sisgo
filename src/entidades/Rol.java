package entidades;

import java.util.Date;

public class Rol {
	
	private int idRol; 
	private String nombreRol;
	private Date fechaAlta;
	private Date fechaBaja;
	
	public Rol() {
		
	this.idRol = 0;
	this.nombreRol ="";
	this.fechaAlta = new Date();
	this.fechaBaja = null;
	
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public String getNombreRol() {
		return nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
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
	
	
	

}
