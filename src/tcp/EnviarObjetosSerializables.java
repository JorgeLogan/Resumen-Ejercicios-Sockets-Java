package tcp;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import objetos_serializables.Persona;

// Para trabajar con objetos serializables, extiendo de cliente
// y me limito a sobreescibir el metodo de ejecucion en bucle para que trabaje con objetos
// Solamente eviare objetos, y el servidor adaptado ServidorSerializable, los recogera y mostrara
// los datos del objeto
public class EnviarObjetosSerializables extends ClienteTCP {
	ObjectOutputStream objSalida;
	
	
	// Constructor
	public EnviarObjetosSerializables() {
		super();
	}
	
	
	// Metodo principal
	public static void main(String[] args) {
		new EnviarObjetosSerializables();
	}
	
	
	// Como ya trabajamos en el resto de areas, nos limitamos a enviar a 3 personas, y cerrar programa
	@Override
	public void ejecucionEnBucle() throws Exception{
		Persona p1 = new Persona("Jorge", "Alvarez", 45);
		Persona p2 = new Persona("Jose", "Rojo", 45);
		Persona p3 = new Persona("Mary", "Cristobal", 38);
		
		this.objSalida = new ObjectOutputStream(this.getSocket().getOutputStream());
		
		this.objSalida.writeObject(p1);
		Thread.sleep(1000);
		this.objSalida.writeObject(p2);
		Thread.sleep(1000);
		this.objSalida.writeObject(p3);
	
		this.objSalida.close();
		this.getSocket().close();
	}
}
