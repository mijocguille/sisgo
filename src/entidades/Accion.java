package entidades;

public class Accion {
	
	private int idAccion;
	private String nombreAccion;
	
	public Accion() {
		this.idAccion = 0; 
		this.nombreAccion = "";
		
	}

	public int getIdAccion() {
		return idAccion;
	}

	public void setIdAccion(int idAccion) {
		this.idAccion = idAccion;
	}

	public String getNombreAccion() {
		return nombreAccion;
	}

	public void setNombreAccion(String nombreAccion) {
		this.nombreAccion = nombreAccion;
	}

	
}
