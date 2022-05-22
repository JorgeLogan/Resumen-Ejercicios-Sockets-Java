package multicast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

import interfaz.InterfazConexiones;

// Emisor Multicast. Envia mensajes para que los reciban los usuarios receptores Multicast.
// Muy similar al de UDP, solo que la InetAddress apunta al grupo, y no al IP localhost
public class EmisorMulticast implements InterfazConexiones{
	// Atributos
	private MulticastSocket mcSocket; // Inicializado sin puerto. Se le da en el paquete
	private InetAddress inetAddress; // En este caso, es el inetAddress del grupo, una IP 225.x.x.x. p.e.
	private InetSocketAddress inetSocketAddress;
	private NetworkInterface networkInterface; // Esta si llevara la IP del localhost
	private DatagramPacket paquete;
	private BufferedReader lector;
	private boolean salir = false;
	
	
	// Constructor
	public EmisorMulticast() {
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
		new EmisorMulticast();
	}
	
	@Override
	public void inicializar() throws Exception {
		this.mcSocket = new MulticastSocket(); // SIN el puerto.
		this.inetAddress = InetAddress.getByName(GRUPO_MUTICAST); // Apuntando al grupo
		this.lector = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Emisor Multicast inicializado");
	}

	@Override
	public void ejecucionEnBucle() throws Exception {
		while(salir == false) {
			// Leemos los datos a enviar por consola
			System.out.println("Escribe un mensaje para enviar:");
			String mensaje = this.lector.readLine();
			
			// Los transformamos a byte[]
			byte[] mensajeBytes = mensaje.getBytes();
			
			// Los pasamos al datagrma y lo enviamos
			this.paquete = new DatagramPacket(mensajeBytes, mensajeBytes.length, this.inetAddress, PUERTO);
			this.mcSocket.send(paquete);
			
			// Comprobamos si es un mensaje de finalizacion o no, para cerrar el bucle
			if(mensaje.trim().equals(MENSAJE_FIN)) this.salir = true;
		}
	}

	@Override
	public void finalizar() throws Exception {
		this.mcSocket.close();
		System.out.println("Emisor multicast finalizado");
	}
}
