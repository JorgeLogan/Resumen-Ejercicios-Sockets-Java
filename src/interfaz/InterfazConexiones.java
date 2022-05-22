package interfaz;

public interface InterfazConexiones {
	public static final int PUERTO = 2000; // Para la escucha / envio
	public static final String MI_HOST = "localhost"; // Para los inetaddress de TCP y UDP y el NetworkInterface de Multicast
	public static final String GRUPO_MUTICAST = "225.0.0.1"; // Para el inetAddress de multicast Emisor (no usa localhost)
	public static final int TAM_PAQUETE = 1000; // Para el tamaño en bytes del paquete a usar, siempre que no sea recogido por teclado
	public static final String MENSAJE_FIN = "adios";
	
	public void inicializar() throws Exception;
	public void ejecucionEnBucle() throws Exception;
	public void finalizar() throws Exception;

}
