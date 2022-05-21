package tcp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import interfaz.InterfazConexiones;

/**
 * El cliente se conectara al servidor, en ese momento el servidor le crea un hilo
 *   que servirá de comunicación exclusiva al cliente con el servidor.
 * El cliente enviara mensajes enviados por consola, y recibira del servidor un OK 
 *   con el mensaje de vuelta
 * Cuando le envie un mensaje de "adios" el servidor cerrará el hilo directamente.
 * El cliente cerrara despues de enviar el mensaje
 *  
 * @author Jorge Alvarez
 *
 */
public class ClienteTCP implements InterfazConexiones {
	private Socket socket; // El socket cliente
	private BufferedReader lector; // Para leer del teclado los mensajes a enviar
	private DataInputStream datosEntrada; // Para leer los datos del servidor
	private DataOutputStream datosSalida; // Para enviar datos al servidor
	private boolean salir = false; //
	
	
	// Constructor
	public ClienteTCP() {
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
		new ClienteTCP();
	}
	
	
	// Metodo para inicializar al cliente
	@Override
	public void inicializar() throws Exception {
		this.socket = new Socket(InterfazConexiones.MI_HOST, InterfazConexiones.PUERTO);
		this.datosEntrada = new DataInputStream(this.socket.getInputStream());
		this.datosSalida = new DataOutputStream(this.socket.getOutputStream());
		this.lector = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Cliente inicializado");
	}

	
	// Metodo que ira en el bucle de ejecucion del cliente
	@Override
	public void ejecucionEnBucle() throws Exception  {
		// Empezamos escribiendo un mensaje que enviar
		System.out.println("Escribe un mensaje para enviar al servidor:");
		String mensaje = this.lector.readLine();
		
		// Seguimos enviando el mensaje al servidor
		this.datosSalida.writeUTF(mensaje);
		
		// Comprobamos que el mensaje no es de finalizacion (salida) antes de escuchar respuesta
		if(mensaje.equals("adios") == false) {		
			// Luego escuchamos del servidor su respuesta
			String respuesta = this.datosEntrada.readUTF();
			System.out.println("Recibimos respuesta del servidor: " + respuesta);			
		}
		else {
			// Cambiamos el control del bucle
			this.salir = true;
		}
	}

	
	// Metodo para finalizar el bucle
	@Override
	public void finalizar() throws Exception  {
		this.datosEntrada.close();
		this.datosSalida.close();
		this.socket.close();
		this.lector.close();
		System.out.println("Cliente cerrado OK");
	}
}
