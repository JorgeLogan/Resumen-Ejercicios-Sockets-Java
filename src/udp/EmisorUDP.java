package udp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import interfaz.InterfazConexiones;

// Clase EmisorUDP. Difunde mensajes capturados por consola a un receptor.
// Si el mensaje es 'adios' acabará el emisor despues de enviar el mensaje
public class EmisorUDP implements InterfazConexiones{
	// Atributos
	private DatagramSocket datagrama;
	private DatagramPacket paquete;
	private InetAddress inetAddress;
	private BufferedReader lector;
	private boolean salir = false;
	
	
	// Constructor
	public EmisorUDP() {
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
		new EmisorUDP();
	}
	
	
	@Override
	public void inicializar() throws Exception {
		this.datagrama = new DatagramSocket();
		this.inetAddress = InetAddress.getByName(MI_HOST);
		this.lector = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Inicializado emisor UDP");
	}

	
	@Override
	public void ejecucionEnBucle() throws Exception {
		System.out.println("Escribe un mensaje para difundir ('adios' para salir)");
		// Los mensajes los envio desde consola
		String mensaje = this.lector.readLine();
		
		// Preparo el paquete emisor (tiene mas datos que el receptor, ya que necesita host y puerto)
		byte[] mensajeBytes = mensaje.getBytes();
		this.paquete = new DatagramPacket(mensajeBytes, mensajeBytes.length, this.inetAddress, PUERTO);
		
		// Y envio el paquete
		this.datagrama.send(paquete);
		
		// Comprobamos que no es un mensaje de cierre, y en ese caso, cambiamos la variable del bucle
		if(mensaje.equals(MENSAJE_FIN)) this.salir = true;
	}
	

	@Override
	public void finalizar() throws Exception {
		this.datagrama.close();		
		this.lector.close();
		System.out.println("Saliendo del emisor UDP");	
	}
}
