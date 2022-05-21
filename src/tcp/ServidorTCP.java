package tcp;

import java.net.ServerSocket;
import java.net.Socket;

import interfaz.InterfazConexiones;

public class ServidorTCP implements InterfazConexiones{
	// Atributos
	private ServerSocket serverSocket;
	private Socket socketCliente;
	private int clientesConectados = 0;
	private int clientesMax = 3;
	
	// Constructor
	// El servidor estará a la escucha de nuevos clientes.
	// Una vez recibido uno, creará un hilo para comunicarse con el cliente a traves del objeto HiloCliente
	// Una vez reciba 3 clientes, dejará de estar a la escucha de nuevos clientes, y el programa se acabará
	// cuando los hilos HiloCliente se cierren en el momento en que reciban la cadena de finalizacion
	public ServidorTCP() {
		try {
			this.inicializar();
			
			while(this.clientesConectados < this.clientesMax) {
				this.clientesConectados++;
				this.ejecucionEnBucle();
			}
			this.finalizar();
		}
		catch(Exception e) {}
	}
	
	
	// Metodo principal
	public static void main(String[] args) {
		new ServidorTCP();
	}
	
	@Override
	public void inicializar() throws Exception {
		this.serverSocket = new ServerSocket(InterfazConexiones.PUERTO);
		System.out.println("Servidor inicializado");
	}

	
	@Override
	public void ejecucionEnBucle() throws Exception {
		this.socketCliente = this.serverSocket.accept();
		HiloClienteServidor hilo = new HiloClienteServidor(this.socketCliente);
		hilo.start();
	}

	
	@Override
	public void finalizar() throws Exception {
		this.socketCliente.close();
		System.out.println("Finalizado servidor");
	}

}
