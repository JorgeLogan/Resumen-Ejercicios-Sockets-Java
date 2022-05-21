package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import interfaz.InterfazConexiones;

public class HiloClienteServidor extends Thread implements InterfazConexiones{
	// Atributos
	private Socket socketCliente;
	private DataInputStream datosEntrada;
	private DataOutputStream datosSalida;
	private boolean salir = false;
	
	// Constructor
	public HiloClienteServidor(Socket socketCliente) {
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
			this.datosSalida.writeUTF("OK" + mensaje);
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

}
