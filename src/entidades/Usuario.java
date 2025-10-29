package entidades;

import java.util.Date;

public class Usuario {
	
	private int idUsuario;
	private String nombreUsuario;
	private String claveUsuario;
	private String descripcionUsuario;
	private int idRol;
	private Date fechaAlta;
	private Date fechaBaja;
	
	public Usuario() {
	
		this.idUsuario = 0;
		this.nombreUsuario = "";
		this.claveUsuario = "";
		this.descripcionUsuario = "";
		this.idRol = 0;
		this.fechaAlta = new Date();
		this.fechaBaja = null;
		
	}
	

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getClaveUsuario() {
		return claveUsuario;
	}

	public void setClaveUsuario(String claveUsuario) {
		this.claveUsuario = claveUsuario;
	}

	public String getDescripcionUsuario() {
		return descripcionUsuario;
	}

	public void setDescripcionUsuario(String descripcionUsuario) {
		this.descripcionUsuario = descripcionUsuario;
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
