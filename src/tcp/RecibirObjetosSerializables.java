package tcp;


import java.io.ObjectInputStream;
import java.net.ServerSocket;

import objetos_serializables.Persona;

// Clase para trabajar con objetos y no modificar mas el Servidor TCP
// Me limito a invocar otro objeto de hilo en la ejecucion del bucle
// Saldra del ejercicio al leer 3 objetos desde el cliente. No necesito crear
// clientes, solo leer de ClienteSerializable los 3 objetos que envia
public class RecibirObjetosSerializables extends ServidorTCP{
	
	// Constructor
	public RecibirObjetosSerializables() {
		super();
	}
	
	@Override
	public void ejecucionEnBucle() throws Exception {
		this.socketCliente = this.serverSocket.accept();
		ObjectInputStream objEntrada = new ObjectInputStream(this.socketCliente.getInputStream());
		
		Persona p1 = (Persona) objEntrada.readObject();
		System.out.println(p1.toString());
		
		Persona p2 = (Persona) objEntrada.readObject();
		System.out.println(p2.toString());
		
		Persona p3 = (Persona) objEntrada.readObject();
		System.out.println(p3.toString());
		
		objEntrada.close();
	
		this.clientesMax = 0; // Para salir del bucle
		
	}

	public static void main(String[] args) {
		new RecibirObjetosSerializables();
	}
}
