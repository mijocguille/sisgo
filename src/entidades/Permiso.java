package entidades;

public class Permiso {
	
	private int idAccion;
	private int idRol;
	
	public Permiso () {
		
		this.idAccion = 0;
		this.idRol = 0;
	}

	public int getIdAccion() {
		return idAccion;
	}

	public void setIdAccion(int idAccion) {
		this.idAccion = idAccion;
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

}
