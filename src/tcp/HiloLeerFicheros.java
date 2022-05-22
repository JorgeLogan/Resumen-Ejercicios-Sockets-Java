package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

import interfaz.InterfazConexiones;


// Clase hilo que recibira del cliente la direccion del un fichero, y posteriormente, abrirá la ruta del
// fichero y leera sus datos para despues enviarlos al cliente
public class HiloLeerFicheros extends Thread implements InterfazConexiones{
	// Atributos
	private Socket socketCliente;
	private DataInputStream datosEntrada;
	private DataOutputStream datosSalida;
	private boolean salir = false;
	
	// Constructor
	public HiloLeerFicheros(Socket socketCliente) {
		this.socketCliente = socketCliente;
	}
	
	
	// Metodo del hilo
	@Override
	public void run() {
		try {
			this.inicializar();
			
			while(this.salir == false) {
				this.ejecucionEnBucle();
			}
			
			this.finalizar();
		}
		catch(Exception e) {}
	}
	
	
	@Override
	public void inicializar() throws Exception {
		this.datosEntrada = new DataInputStream(this.socketCliente.getInputStream());
		this.datosSalida = new DataOutputStream(this.socketCliente.getOutputStream());
		System.out.println("Hilo cliente inicializado con exito");
	}

	@Override
	public void ejecucionEnBucle() throws Exception {
		String mensaje = this.datosEntrada.readUTF();
		
		// Comprobamos que no es un mensaje de cierre
		if(mensaje.equals(InterfazConexiones.MENSAJE_FIN) == false) {
			try {
				mensaje = mensaje.trim(); // Por si hay espacios en blanco a ambos lados
				FileInputStream lectorFichero = new FileInputStream(mensaje);
				
				// Leemos y convertimos los datos
				byte[] datosLeidos = lectorFichero.readAllBytes();
				String datosStr = new String(datosLeidos);
				
				// Enviamos los datos
				datosSalida.writeUTF(datosStr.trim());	
			}
			catch(Exception e) {
				String respuesta = "No se pudo leer el fichero: " + mensaje;
				System.out.println(respuesta);
				datosSalida.writeUTF(respuesta);	
			}
		}
		else {
			this.salir = true;
		}
	}

	@Override
	public void finalizar() throws Exception {
		this.datosEntrada.close();
		this.datosSalida.close();
		System.out.println("Hilo cliente cerrado con exito");
	}

public static void main(String[] args) {
	
}
	
}
