package tcp;

import java.net.ServerSocket;
import java.net.Socket;

import interfaz.InterfazConexiones;

public class ServidorTCP implements InterfazConexiones{
	// Atributos
	protected ServerSocket serverSocket;
	protected Socket socketCliente;
	protected int clientesConectados = 0;
	protected int clientesMax = 3;
	
	// Constructor
	// El servidor estar? a la escucha de nuevos clientes.
	// Una vez recibido uno, crear? un hilo para comunicarse con el cliente a traves del objeto HiloCliente
	// Una vez reciba 3 clientes, dejar? de estar a la escucha de nuevos clientes, y el programa se cerrara
	// cuando los hilos activos finalizen enviando su mensaje de fin (adios)
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
		this.serverSocket = new ServerSocket(PUERTO);
		System.out.println("Servidor inicializado");
	}

	
	@Override
	public void ejecucionEnBucle() throws Exception {
		this.socketCliente = this.serverSocket.accept();
		//HiloClienteServidor hilo = new HiloClienteServidor(this.socketCliente);
		HiloLeerFicheros hilo = new HiloLeerFicheros(this.socketCliente);
		hilo.start();
		hilo.join(); // Para que no siga el flujo principal y cierre el socket mientras este funcionando el hilo
	}

	
	@Override
	public void finalizar() throws Exception {
		this.socketCliente.close();
		System.out.println("Finalizado servidor");
	}

}
