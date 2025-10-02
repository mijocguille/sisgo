package recursos;

import java.util.Date;

public class Empleado {

	
	private int idEmpleado; 
	private int legajo; 
	private String nombre; 
	private String apellido; 
	private Date fechaAlta; 
	private Date fechaBaja; 
	private int idUsuario;
	
	
	public Empleado () {
		this.idEmpleado = 0;
		this.legajo = 0;
		this.nombre = "";
		this.apellido = "";
		this.fechaAlta = new Date();
		this.fechaBaja = null;
		this.idUsuario = 0;
	}


	public int getIdEmpleado() {
		return idEmpleado;
	}


	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}


	public int getLegajo() {
		return legajo;
	}


	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
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
	
	public String getNombreCompleto() {
		return this.apellido + ", " + this.nombre;
	}
}
