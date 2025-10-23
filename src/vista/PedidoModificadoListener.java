package vista;

public interface PedidoModificadoListener {
	void onPedidoModificado(int numeroPedido, int idCliente, String detallePedido, String caracteristicasPedido);
}
