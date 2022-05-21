package interfaz;

public interface InterfazConexiones {
	public static final int puerto = 2000; // Para la escucha / envio
	public static final String miHost = "localhost"; // Para los inetaddress de TCP y UDP
	public static final String grupoMulticast = "225.0.0.1"; // Para el inetAddress de multicast Emisor (no usa localhost)
	public static final int tamPaquete = 1000; // Para el tamaño en bytes del paquete a usar, siempre que no sea recogido por teclado
	
	public void inicializar();
	public void ejecucionEnBucle();
	public void finalizar();

}
