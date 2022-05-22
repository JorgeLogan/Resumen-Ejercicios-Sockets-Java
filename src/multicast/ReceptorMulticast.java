package multicast;

import java.net.DatagramPacket;
import java.net.MulticastSocket;

import interfaz.InterfazConexiones;

// Clase receptor multicast. Es muy similar al de UDP. El diferente es el emisor
public class ReceptorMulticast implements InterfazConexiones{
	// Atributos
	private MulticastSocket mcSocket;
	private DatagramPacket paquete;
	
	
	// Constructor
	public ReceptorMulticast() {
		
	}
	
	
	@Override
	public void inicializar() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ejecucionEnBucle() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finalizar() throws Exception {
		// TODO Auto-generated method stub
		
	}
	// Atributos
	
	// Constructor

}
