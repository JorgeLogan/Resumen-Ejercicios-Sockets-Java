package multicast;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

import interfaz.InterfazConexiones;

// Clase receptor multicast. Tiene varios cambios respecto al de UDP
// Al pertenecer a un grupo multicast, necesitaremos:
//		* Un inetAddress que apunte a un grupo, no a una IP
//		* Un inetSocketAddress que usará el inetAddress del grupo con el puerto de escucha
//		* Un NetworkInterface que apuntará a la IP localhost
//		* Cuando tengamos todo, nos uniremos al grupo para escuchar, y lo abandoremos al finalizar
public class ReceptorMulticast implements InterfazConexiones{
	// Atributos
	private MulticastSocket mcSocket;
	private InetAddress inetAddress; // Apuntara al grupo
	private InetSocketAddress inetSocketAddress; // Coge el grupo y puerto
	private NetworkInterface networkInterface; // Funciona como el InetAddress en UDP cogiendo el localhost
	private DatagramPacket paquete;
	private boolean salir = false;
	
	// Constructor
	public ReceptorMulticast() {
		try {
			this.inicializar();
			
			while(this.salir == false) {
				
				this.ejecucionEnBucle();
			}
			
			this.finalizar();
		}
		catch(Exception e) {}
	}
	
	
	// Metodo principal
	public static void main(String[] args) {
		new ReceptorMulticast();
	}
	
	
	@Override
	public void inicializar() throws Exception {
		this.mcSocket = new MulticastSocket(PUERTO); // Escuchamos el puerto
		this.inetAddress = InetAddress.getByName(GRUPO_MUTICAST); // Apuntamos el inetAddress al grupo
		this.inetSocketAddress = new InetSocketAddress(this.inetAddress, PUERTO); // Cogemos el inetadress y puerto
		this.networkInterface = NetworkInterface.getByName(MI_HOST); // Escuchamos nuestro host igual que un inetaddress normal
		this.mcSocket.joinGroup(this.inetSocketAddress, this.networkInterface); // Nos unimos al grupo
;		System.out.println("Receptor Multicast inicializado");
	}

	@Override
	public void ejecucionEnBucle() throws Exception {
		// Preparamos el paquete y nos ponemos en escucha para rellenarlo con el mcsocket
		byte[] mensaje = new byte[TAM_PAQUETE];
		this.paquete = new DatagramPacket(mensaje, TAM_PAQUETE);
		this.mcSocket.receive(paquete);
		
		// Transformamos el mensaje en cadena y eliminamos lo que no nos interesa con trim
		String mensajeStr = new String(mensaje).trim();
		
		// Mostramos el mensaje
		System.out.println("Paquete recibido: " + mensajeStr);
		
		// Comprobamos si es un mensaje de final para cerrar o seguir en el bucle
		if(mensajeStr.equals(MENSAJE_FIN)) this.salir = true;
	}

	@Override
	public void finalizar() throws Exception {
		this.mcSocket.leaveGroup(inetAddress); // Abandonamos el grupo
		this.mcSocket.close(); // Cerramos el socket
		System.out.println("Saliendo del receptor Multicast");
		
	}
}
