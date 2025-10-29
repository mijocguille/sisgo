package vista;

public interface UsuarioNuevoListener {
   void onUsuarioCreado(String nombreUsuario, String claveUsuario, String descripcionUsuario, int idRol);
}
