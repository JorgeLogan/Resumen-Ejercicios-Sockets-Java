package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import interfaz.InterfazConexiones;

// El receptor UDP estara a la escucha de mensajes por UDP. En este caso solo escucha
// Se cerrara el programa cuando reciba un mensaje de finalizacion 'adios'
public class ReceptorUDP implements InterfazConexiones {
	// Atributos
	private DatagramSocket datagrama;
	private DatagramPacket paquete;
	private boolean salir = false;
	
	// Constructor
	public ReceptorUDP() {
		try {
			inicializar();
			
			while(salir == false) {
				this.ejecucionEnBucle();
			}
			
			this.finalizar();
		
		}
		catch(Exception e) {System.out.println("error " + e.getMessage());}
	}
	

	// Metodo principal
	public static void main(String[] args) {
		new ReceptorUDP();
	}
	
	
	@Override
	public void inicializar() throws Exception {
		this.datagrama = new DatagramSocket(this.PUERTO);
		System.out.println("Receptor inicializado. En espera de mensajes");
	}

	
	@Override
	public void ejecucionEnBucle() throws Exception {
		// Preparamos un paquete de escucha, y lo ponemos a esperar en el datagrama
		byte[] mensaje = new byte[InterfazConexiones.TAM_PAQUETE];
		this.paquete = new DatagramPacket(mensaje, mensaje.length);
		this.datagrama.receive(paquete);
		
		// Ya hemos recibido el paquete, y lo mostramos
		String mensajeString = new String(mensaje,0, mensaje.length);
		System.out.println("mensaje recibido: " + mensajeString);
		
		// Comprobamos si es un mensaje de fin de programa
		if(mensajeString.trim().equals(MENSAJE_FIN)) {
			this.salir = true;
		}
	}

	
	@Override
	public void finalizar() throws Exception {
		this.datagrama.close();
		System.out.println("Saliendo del receptor UDP");
	}

}
